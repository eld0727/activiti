package activiti.model.converters;

import activiti.model.ModelBundle;
import activiti.model.converters.json.BpmnJsonConverter;
import activiti.model.converters.json.CustomServiceTaskJsonConverter;
import activiti.model.converters.json.ExtendedBaseBpmnJsonConverter;
import activiti.model.converters.xml.CustomServiceTaskXMLConverter;
import activiti.model.reflection.CustomServiceTask;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ServiceTask;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by alex on 02.10.2015.<br/>
 * TODO comment me
 */
@Log4j
@Component("bpmnConverter")
public class BpmnConverter {

    private BpmnJsonConverter jsonConverter = new BpmnJsonConverter();

    private BpmnXMLConverter xmlConverter = new BpmnXMLConverter();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CustomServiceTaskXMLConverter customServiceTaskXMLConverter;

    @PostConstruct
    private void postConstruct() throws IllegalAccessException, InstantiationException {
        Map<String, Object> customTasks = applicationContext.getBeansWithAnnotation(CustomServiceTask.class);
        for (Map.Entry<String, Object> entry : customTasks.entrySet()) {
            ServiceTask task = (ServiceTask) entry.getValue();
            Class<? extends ServiceTask> taskClass = task.getClass();
            CustomServiceTask annotation = taskClass.getAnnotation(CustomServiceTask.class);
            Class<? extends ModelBundle> bundleClass = annotation.bundle();
            ModelBundle modelBundle;

            try {
                modelBundle = applicationContext.getBean(bundleClass);
            } catch (NoSuchBeanDefinitionException e) {
                log.warn("No bean definition for " + bundleClass);
                //если не нашли бина, то пытаемся создать экземпляр через конструктор
                modelBundle = bundleClass.newInstance();
            }

            ExtendedBaseBpmnJsonConverter bpmnJSONConverter = modelBundle.getJsonConverter();
            if (bpmnJSONConverter == null) {
                bpmnJSONConverter = applicationContext.getBean(
                        CustomServiceTaskJsonConverter.class,
                        modelBundle.getStencilId(),
                        taskClass
                );
            }
            jsonConverter.addConverter(bpmnJSONConverter);

            String taskType = annotation.type();
            if(!StringUtils.hasText(taskType)) {
                taskType = entry.getKey();
            }
            customServiceTaskXMLConverter.addCustomTask(taskClass, taskType, modelBundle);
        }
    }


    /**
     * Конвертировать модель бизнесс процесса в json
     */
    public ObjectNode convertToJson(BpmnModel model) {
        return jsonConverter.convertToJson(model);
    }

    /**
     * Конвертировать модель бизнесс процесса в XML
     */
    public byte[] convertToXML(BpmnModel model) {
        return xmlConverter.convertToXML(model);
    }

    /**
     * Конвертировать json в модель бизнесс процесса
     */
    public BpmnModel convertToBpmnModel(JsonNode modelNode) {
        return jsonConverter.convertToBpmnModel(modelNode);
    }

}
