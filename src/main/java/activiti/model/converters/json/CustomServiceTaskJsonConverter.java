package activiti.model.converters.json;

import activiti.model.converters.json.util.JsonReflectionUtils;
import activiti.model.converters.json.util.extractor.ValueExtractor;
import activiti.model.reflection.TaskParameter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.editor.language.json.converter.BaseBpmnJsonConverter;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 04.10.2015.<br/>
 * Конвертер не стандартных экземпляров {@link org.activiti.bpmn.model.ServiceTask}
 */
@Component
@Scope("prototype")
public class CustomServiceTaskJsonConverter extends ExtendedBaseBpmnJsonConverter{

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Id графического компонента
     */
    protected final String stencilId;

    /**
     * Класс сервисного таска
     */
    protected final Class<? extends ServiceTask> taskClass;

    /**
     * Поле таска -> поле json компонента
     */
    protected final Map<Field, String> taskFieldToStencilParam = new HashMap<>();

    /**
     * Поле таска -> extractor
     */
    protected final Map<Field, ValueExtractor> taskFieldToExtractor = new HashMap<>();

    /** */
    public CustomServiceTaskJsonConverter(String stencilId, Class<? extends ServiceTask> taskClass) {
        this.stencilId = stencilId;
        this.taskClass = taskClass;

        ReflectionUtils.doWithFields(taskClass, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                TaskParameter parameter = field.getDeclaredAnnotation(TaskParameter.class);
                if(parameter != null) {
                    String stencilParameter = parameter.stencilParameter();
                    if("".equals(stencilParameter)) {
                        stencilParameter = field.getName();
                    }
                    taskFieldToStencilParam.put(field, stencilParameter);
                    taskFieldToExtractor.put(field, JsonReflectionUtils.getExtractorForField(field));
                }
            }
        });
    }


    @Override
    public void fillTypes(
            Map<String, BaseBpmnJsonConverter> convertersToBpmnMap,
            Map<Class<? extends BaseElement>, BaseBpmnJsonConverter> convertersToJsonMap
    ) {
        convertersToBpmnMap.put(stencilId, this);
        convertersToJsonMap.put(taskClass, this);
    }

    @Override
    public void fillRectanglesTypes(List<String> rectangles) {
        rectangles.add(stencilId);
    }

    @Override
    protected void convertElementToJson(ObjectNode propertiesNode, BaseElement baseElement) {
        ObjectWriter writer = new ObjectMapper().writer();
        for (Map.Entry<Field, String> entry : taskFieldToStencilParam.entrySet()) {
            try {
                Object value = PropertyUtils.getProperty(baseElement, entry.getKey().getName());
                String stringValue;
                if(value instanceof String) {
                    stringValue = (String) value;
                } else {
                    stringValue = writer.writeValueAsString(value);
                }
                setPropertyValue(entry.getValue(), stringValue, propertiesNode);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new IllegalStateException("Error during getting property " + entry.getKey() + " of " + baseElement.getClass());
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Error during value converting " + entry.getKey() + " of " + baseElement.getClass());
            }
        }
    }

    @Override
    protected BaseElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
        ServiceTask task = instantiateTask();
        for (Map.Entry<Field, String> entry : taskFieldToStencilParam.entrySet()) {
            Field field = entry.getKey();
            JsonNode property = getProperty(entry.getValue(), elementNode);
            ValueExtractor extractor = taskFieldToExtractor.get(field);
            try {
                PropertyUtils.setProperty(task, field.getName(), extractor.extractValue(property));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new IllegalStateException("Error during setting property " + field.getName() + "of " + taskClass);
            }
        }
        return task;
    }

    @Override
    protected String getStencilId(BaseElement baseElement) {
        return stencilId;
    }

    @SneakyThrows
    protected ServiceTask instantiateTask() {
        return applicationContext.getBean(taskClass, new Object[] {});
    }
}
