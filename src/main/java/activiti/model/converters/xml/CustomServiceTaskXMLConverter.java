package activiti.model.converters.xml;

import activiti.model.ModelBundle;
import activiti.model.converters.xml.util.XMLReflectionUtils;
import activiti.model.converters.xml.util.extractor.attribute.AttributeValueExtractor;
import activiti.model.reflection.CustomServiceTask;
import activiti.model.reflection.TaskParameter;
import lombok.Data;
import org.activiti.bpmn.converter.BaseBpmnXMLConverter;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.converter.ServiceTaskXMLConverter;
import org.activiti.bpmn.converter.util.BpmnXMLUtil;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ServiceTask;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import otts.test.work.model.notification.SendNotificationTask;
import otts.test.work.model.notification.bundle.SendNotificationTaskModelBundle;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 06.10.2015.<br/>
 * Конвертер для {@link ServiceTask} помеченных аннотацией {@link CustomServiceTask}
 */
@Component
public class CustomServiceTaskXMLConverter extends BaseBpmnXMLConverter {

    /**
     * Ориганальный конвертер для {@link ServiceTask}. <br/>
     * Нужен он, потому что механизм парсинга построен так,
     * что не может быть для одного и того же xml тега больше одного ковертера. И нужно обращаться к его методам.
     */
    protected ServiceTaskXMLConverter originalConverter = new ServiceTaskXMLConverter();
    protected Map<Class<? extends ServiceTask>, ClassConvertInformation> classConvertInformation = new HashMap<>();
    protected Map<String, Class<? extends ServiceTask>> taskTypeToClass = new HashMap<>();
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Добавить конвертер для не стандартного {@link ServiceTask}'a
     */
    public void addCustomTask(Class<? extends ServiceTask> taskClass, String taskType, ModelBundle modelBundle) {
        Class<? extends ServiceTask> previousClass = taskTypeToClass.put(taskType, taskClass);
        if (previousClass != null) {
            taskTypeToClass.put(taskType, previousClass);
            throw new IllegalArgumentException(String.format(
                    "Type '%s' of service task is already reserved by '%s'. But '%s' is trying to reserve it.",
                    taskType,
                    previousClass,
                    taskClass
            ));
        }

        final ClassConvertInformation information = new ClassConvertInformation();
        information.setType(taskType);
        BaseBpmnXMLConverter xmlConverter = modelBundle.getXMLConverter();
        if (xmlConverter != null) {
            information.setAlternativeConverter(xmlConverter);
        } else {
            final Map<Field, String> fieldToXmlAttribute = new HashMap<>();
            final Map<Field, AttributeValueExtractor> fieldToAttributeExtractor = new HashMap<>();
            ReflectionUtils.doWithFields(taskClass, new ReflectionUtils.FieldCallback() {
                @Override
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    TaskParameter parameter = field.getDeclaredAnnotation(TaskParameter.class);
                    if (parameter != null) {
                        String xmlParameter = parameter.xmlParameter();
                        if ("".equals(xmlParameter)) {
                            xmlParameter = field.getName();
                        }
                        fieldToXmlAttribute.put(field, xmlParameter);
                        fieldToAttributeExtractor.put(field, XMLReflectionUtils.getExtractorForField(field));
                    }
                }
            });

            information.setFieldToXmlAttribute(fieldToXmlAttribute);
            information.setFieldToAttributeExtractor(fieldToAttributeExtractor);
        }

        classConvertInformation.put(taskClass, information);

        BpmnXMLConverter.addConverter(this, taskClass);
    }

    @Override
    protected Class<? extends BaseElement> getBpmnElementType() {
        return ServiceTask.class;
    }

    @Override
    protected String getXMLElementName() {
        return ELEMENT_TASK_SERVICE;
    }

    @Override
    protected BaseElement convertXMLToElement(XMLStreamReader xtr, BpmnModel model) throws Exception {
        String type = xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TYPE);
        if (type != null) {
            Class<? extends ServiceTask> taskClass = taskTypeToClass.get(type.trim());
            if (taskClass != null) {
                ClassConvertInformation convertInformation = this.classConvertInformation.get(taskClass);
                BaseBpmnXMLConverter alternativeConverter = convertInformation.getAlternativeConverter();
                if(alternativeConverter != null) {
                    return invokeConvertXMLToElement(xtr, model, alternativeConverter);
                }

                ServiceTask task = instantiateTask(taskClass);
                BpmnXMLUtil.addXMLLocation(task, xtr);

                for (Map.Entry<Field, String> entry : convertInformation.getFieldToXmlAttribute().entrySet()) {
                    Field field = entry.getKey();
                    String rawValue = xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, entry.getValue());
                    if(rawValue != null) {
                        AttributeValueExtractor extractor = convertInformation.getFieldToAttributeExtractor().get(field);
                        Object value = extractor.extract(rawValue);
                        if (value != null) {
                            PropertyUtils.setProperty(task, field.getName(), value);
                        }
                    }
                }

                parseChildElements(getXMLElementName(), task, model, xtr);
                return task;
            }
        }

        return invokeConvertXMLToElement(xtr, model, originalConverter);
    }

    protected ServiceTask instantiateTask(Class<? extends ServiceTask> taskClass) {
        return applicationContext.getBean(taskClass, new Object[]{});
    }

    private BaseElement invokeConvertXMLToElement(XMLStreamReader xtr, BpmnModel model, BaseBpmnXMLConverter converter) {
        Method method = ReflectionUtils.findMethod(
                converter.getClass(),
                "convertXMLToElement",
                XMLStreamReader.class,
                BpmnModel.class
        );
        ReflectionUtils.makeAccessible(method);
        return (BaseElement) ReflectionUtils.invokeMethod(method, converter, xtr, model);
    }

    @Override
    protected void writeAdditionalAttributes(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
        @SuppressWarnings("unchecked")
        Class<? extends ServiceTask> taskClass = (Class<? extends ServiceTask>) element.getClass();
        ClassConvertInformation convertInformation = this.classConvertInformation.get(taskClass);
        if(convertInformation != null) {
            BaseBpmnXMLConverter alternativeConverter = convertInformation.getAlternativeConverter();
            if (alternativeConverter != null) {
                invokeWriteAdditionalAttributes(element, model, xtw, alternativeConverter);
            }

            for (Map.Entry<Field, String> entry : convertInformation.getFieldToXmlAttribute().entrySet()) {
                Field field = entry.getKey();
                Object value = PropertyUtils.getProperty(element, field.getName());
                if(value != null) {
                    //TODO придумать более универсальное преобразование к строке
                    writeQualifiedAttribute(entry.getValue(), String.valueOf(value), xtw);
                }
            }
            writeQualifiedAttribute(ATTRIBUTE_TYPE, convertInformation.getType(), xtw);
        } else {
            invokeWriteAdditionalAttributes(element, model, xtw, originalConverter);
        }
    }

    private void invokeWriteAdditionalAttributes(
            BaseElement element,
            BpmnModel model,
            XMLStreamWriter xtw,
            BaseBpmnXMLConverter converter
    ) {
        Method method = ReflectionUtils.findMethod(
                ServiceTaskXMLConverter.class,
                "writeAdditionalAttributes",
                BaseElement.class,
                BpmnModel.class,
                XMLStreamWriter.class
        );
        ReflectionUtils.makeAccessible(method);
        ReflectionUtils.invokeMethod(method, converter, element, model, xtw);
    }

    @Override
    protected void writeAdditionalChildElements(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
        if (element.getClass().equals(ServiceTask.class)) {
            Method method = ReflectionUtils.findMethod(
                    ServiceTaskXMLConverter.class,
                    "writeAdditionalChildElements",
                    BaseElement.class,
                    BpmnModel.class,
                    XMLStreamWriter.class
            );
            ReflectionUtils.makeAccessible(method);
            ReflectionUtils.invokeMethod(method, originalConverter, element, model, xtw);
        }
    }

    @Data
    protected static class ClassConvertInformation {

        /**
         * Тип, которым отмечается таск в xml
         */
        private String type;

        /**
         * Сопоставление поля класса с названием xml аттрибута
         */
        private Map<Field, String> fieldToXmlAttribute;

        /**
         * Сопоставление поля класса с распаковщиков значение аттрибута
         */
        private Map<Field, AttributeValueExtractor> fieldToAttributeExtractor;

        /**
         * Не стандартный конвертер для типа
         */
        private BaseBpmnXMLConverter alternativeConverter;
    }
}
