package otts.test.work.model.notification.bundle.xml;

import lombok.SneakyThrows;
import org.activiti.bpmn.converter.BaseBpmnXMLConverter;
import org.activiti.bpmn.converter.ServiceTaskXMLConverter;
import org.activiti.bpmn.converter.util.BpmnXMLUtil;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.ImplementationType;
import org.springframework.util.ReflectionUtils;
import otts.test.work.model.notification.SendNotificationTask;
import otts.test.work.model.notification.bundle.SendNotificationTaskModelBundle;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by alex on 02.10.2015.<br/>
 * Конвертер в XML для {@link SendNotificationTask}
 */
public class SendNotificationTaskXMLConverter extends BaseBpmnXMLConverter {
    @Override
    protected Class<? extends BaseElement> getBpmnElementType() {
        return SendNotificationTask.class;
    }

    protected ServiceTaskXMLConverter originalConverter = new ServiceTaskXMLConverter();

    @Override
    protected String getXMLElementName() {
        return ELEMENT_TASK_SERVICE;
    }

    @Override
    protected BaseElement convertXMLToElement(XMLStreamReader xtr, BpmnModel model) throws Exception {
        String type = xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, ATTRIBUTE_TYPE);
        if(Objects.equals(type, "sendNotification")) {
            SendNotificationTask task = new SendNotificationTask();
            BpmnXMLUtil.addXMLLocation(task, xtr);
            task.setReceiver(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, SendNotificationTaskModelBundle.NOTIFICATION_RECEIVER_PROPERTY_ID));
            task.setText(xtr.getAttributeValue(ACTIVITI_EXTENSIONS_NAMESPACE, SendNotificationTaskModelBundle.NOTIFICATION_TEXT_PROPERTY_ID));

            parseChildElements(getXMLElementName(), task, model, xtr);
            return task;
        } else {
            Method method = ReflectionUtils.findMethod(
                    ServiceTaskXMLConverter.class,
                    "convertXMLToElement",
                    XMLStreamReader.class,
                    BpmnModel.class
            );
            ReflectionUtils.makeAccessible(method);
            return (BaseElement) ReflectionUtils.invokeMethod(method, originalConverter, xtr, model);
        }
    }

    @Override
    protected void writeAdditionalAttributes(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
        if(element instanceof SendNotificationTask) {
            SendNotificationTask task = (SendNotificationTask) element;
            writeQualifiedAttribute(SendNotificationTaskModelBundle.NOTIFICATION_RECEIVER_PROPERTY_ID, task.getReceiver(), xtw);
            writeQualifiedAttribute(SendNotificationTaskModelBundle.NOTIFICATION_TEXT_PROPERTY_ID, task.getText(), xtw);
            writeQualifiedAttribute(ATTRIBUTE_TYPE, "sendNotification", xtw);
        } else {
            Method method = ReflectionUtils.findMethod(
                    ServiceTaskXMLConverter.class,
                    "writeAdditionalAttributes",
                    BaseElement.class,
                    BpmnModel.class,
                    XMLStreamWriter.class
            );
            ReflectionUtils.makeAccessible(method);
            ReflectionUtils.invokeMethod(method, originalConverter, element, model, xtw);
        }
    }

    @Override
    protected void writeAdditionalChildElements(BaseElement element, BpmnModel model, XMLStreamWriter xtw) throws Exception {
        if(!(element instanceof SendNotificationTask)) {
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
}
