package otts.test.work.model.notification;

import activiti.model.reflection.CustomServiceTask;
import activiti.model.reflection.TaskParameter;
import lombok.Getter;
import lombok.Setter;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.ServiceTask;
import otts.test.work.model.notification.bundle.SendNotificationTaskModelBundle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alex on 02.10.2015.<br/>
 * Задание которое отправляет увкдомление пользователю
 */
@Getter
@Setter
@CustomServiceTask(type = "sendNotification", bundle = SendNotificationTaskModelBundle.class)
public class SendNotificationTask extends ServiceTask {

    /**
     * Получатель уведомления
     */
    @TaskParameter(
            stencilParameter = SendNotificationTaskModelBundle.NOTIFICATION_RECEIVER_PROPERTY_ID,
            xmlParameter = SendNotificationTaskModelBundle.NOTIFICATION_RECEIVER_PROPERTY_ID
    )
    private String receiver;

    /**
     * Текст уведомления
     */
    @TaskParameter(
            stencilParameter = SendNotificationTaskModelBundle.NOTIFICATION_TEXT_PROPERTY_ID,
            xmlParameter = SendNotificationTaskModelBundle.NOTIFICATION_TEXT_PROPERTY_ID
    )
    private String text;

    @Override
    public SendNotificationTask clone() {
        SendNotificationTask notificationTask = new SendNotificationTask();
        notificationTask.setValues(this);
        return notificationTask;
    }

    public void setValues(SendNotificationTask task) {
        this.text = task.text;
        this.receiver = task.receiver;
        super.setValues(task);
    }

    @Override
    public String getImplementationType() {
        return ImplementationType.IMPLEMENTATION_TYPE_EXPRESSION;
    }

    @Override
    public String getImplementation() {
        return "${notificationService.sendNotification(" +
                dispatchVariable(this.getReceiver()) + ", " +
                dispatchVariable(this.getText())
                + ")}";
    }

    protected String dispatchVariable(String s) {
        Pattern pattern = Pattern.compile("\\$\\{(.+)\\}");
        Matcher matcher = pattern.matcher(s);
        if(matcher.matches()) {
            return matcher.group(1);
        }
        return "\"" + s.replaceAll("\"", "\\\"").replace("${", "\".concat(").replace("}", ").concat(\"") + "\"" + (s.contains("}") ? ")" : "");
    }
}
