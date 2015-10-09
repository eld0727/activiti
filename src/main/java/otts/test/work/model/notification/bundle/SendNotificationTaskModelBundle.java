package otts.test.work.model.notification.bundle;

import activiti.model.ModelBundle;
import activiti.model.converters.json.ExtendedBaseBpmnJsonConverter;
import activiti.model.stencil.Stencil;
import activiti.model.stencil.defaultset.properties.DefaultProperties;
import activiti.model.stencil.properties.Property;
import activiti.model.stencil.rules.StencilRule;
import org.activiti.bpmn.constants.BpmnXMLConstants;
import org.activiti.bpmn.converter.BaseBpmnXMLConverter;
import org.activiti.bpmn.model.BaseElement;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.behavior.TaskActivityBehavior;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.handler.AbstractActivityBpmnParseHandler;
import org.activiti.engine.impl.el.ExpressionManager;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.parse.BpmnParseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import otts.test.work.model.notification.SendNotificationTask;
import otts.test.work.service.NotificationService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 02.10.2015.<br/>
 * TODO comment me
 */
@Component
public class SendNotificationTaskModelBundle implements ModelBundle {

    public final static String SEND_NOTIFICATION_STENCIL_ID = "SendNotification";
    public final static String NOTIFICATION_RECEIVER_PROPERTY_ID = "notification-receiver";
    public final static String NOTIFICATION_TEXT_PROPERTY_ID = "notification-text";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private DefaultProperties defaultProperties;

    private NotificationService notificationService;

    private Stencil sendNotificationStencil;

    @PostConstruct
    private void postConstruct() {
        sendNotificationStencil = Stencil.builder()
                .id(getStencilId())
                .type(Stencil.NODE)
                .group("Мои компоненты")
                .title("Отправить уведомление")
                .description("An automatic task with service logic")
                .view("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<svg\n   xmlns=\"http://www.w3.org/2000/svg\"\n   xmlns:svg=\"http://www.w3.org/2000/svg\"\n   xmlns:oryx=\"http://www.b3mn.org/oryx\"\n   xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n\n   width=\"102\"\n   height=\"82\"\n   version=\"1.0\">\n  <defs></defs>\n  <oryx:magnets>\n  \t<oryx:magnet oryx:cx=\"1\" oryx:cy=\"20\" oryx:anchors=\"left\" />\n  \t<oryx:magnet oryx:cx=\"1\" oryx:cy=\"40\" oryx:anchors=\"left\" />\n  \t<oryx:magnet oryx:cx=\"1\" oryx:cy=\"60\" oryx:anchors=\"left\" />\n  \t\n  \t<oryx:magnet oryx:cx=\"25\" oryx:cy=\"79\" oryx:anchors=\"bottom\" />\n  \t<oryx:magnet oryx:cx=\"50\" oryx:cy=\"79\" oryx:anchors=\"bottom\" />\n  \t<oryx:magnet oryx:cx=\"75\" oryx:cy=\"79\" oryx:anchors=\"bottom\" />\n  \t\n  \t<oryx:magnet oryx:cx=\"99\" oryx:cy=\"20\" oryx:anchors=\"right\" />\n  \t<oryx:magnet oryx:cx=\"99\" oryx:cy=\"40\" oryx:anchors=\"right\" />\n  \t<oryx:magnet oryx:cx=\"99\" oryx:cy=\"60\" oryx:anchors=\"right\" />\n  \t\n  \t<oryx:magnet oryx:cx=\"25\" oryx:cy=\"1\" oryx:anchors=\"top\" />\n  \t<oryx:magnet oryx:cx=\"50\" oryx:cy=\"1\" oryx:anchors=\"top\" />\n  \t<oryx:magnet oryx:cx=\"75\" oryx:cy=\"1\" oryx:anchors=\"top\" />\n  \t\n  \t<oryx:magnet oryx:cx=\"50\" oryx:cy=\"40\" oryx:default=\"yes\" />\n  </oryx:magnets>\n  <g pointer-events=\"fill\" oryx:minimumSize=\"50 40\">\n\t<rect id=\"text_frame\" oryx:anchors=\"bottom top right left\" x=\"1\" y=\"1\" width=\"94\" height=\"79\" rx=\"10\" ry=\"10\" stroke=\"none\" stroke-width=\"0\" fill=\"none\" />\n\t<rect id=\"bg_frame\" oryx:resize=\"vertical horizontal\" x=\"0\" y=\"0\" width=\"100\" height=\"80\" rx=\"10\" ry=\"10\" stroke=\"#bbbbbb\" stroke-width=\"1\" fill=\"#f9f9f9\" />\n\t\t<text \n\t\t\tfont-size=\"12\" \n\t\t\tid=\"text_name\" \n\t\t\tx=\"50\" \n\t\t\ty=\"40\" \n\t\t\toryx:align=\"middle center\"\n\t\t\toryx:fittoelem=\"text_frame\"\n\t\t\tstroke=\"#373e48\">\n\t\t</text>\n    \n\t<g id=\"sendTask\" transform=\"translate(4,3)\">\n\t\n\t<!-- path here -->\n\t\t<path oryx:anchors=\"top left\"\n\t\t\tstyle=\"fill:#72a7d0;stroke:none;\"\n     \t\td=\"M 1 3 L 9 11 L 17 3 L 1 3 z M 1 5 L 1 13 L 5 9 L 1 5 z M 17 5 L 13 9 L 17 13 L 17 5 z M 6 10 L 1 15 L 17 15 L 12 10 L 9 13 L 6 10 z \"\n     \t/>\n\t</g>\n\t\n\t<g id=\"parallel\">\n\t\t<path oryx:anchors=\"bottom\" fill=\"none\" stroke=\"#bbbbbb\" d=\"M46 70 v8 M50 70 v8 M54 70 v8\" stroke-width=\"2\" />\n\t</g>\n\t\n\t<g id=\"sequential\">\n\t\t<path oryx:anchors=\"bottom\" fill=\"none\" stroke=\"#bbbbbb\" stroke-width=\"2\" d=\"M46,76h10M46,72h10 M46,68h10\"/>\n\t</g>\n\n\t<g id=\"compensation\">\n\t\t<path oryx:anchors=\"bottom\" fill=\"none\" stroke=\"#bbbbbb\" d=\"M 62 74 L 66 70 L 66 78 L 62 74 L 62 70 L 58 74 L 62 78 L 62 74\" stroke-width=\"1\" />\n\t</g>\n  </g>\n</svg>")
                .icon("activity/list/type.send.png")
                .property(defaultProperties.overrideidpackage())
                .property(defaultProperties.namepackage())
                .property(
                        Property.builder()
                                .id(NOTIFICATION_RECEIVER_PROPERTY_ID)
                                .title("Получатель")
                                .description("Получатель уведомления")
                                .type(Property.STRING)
                                .value("")
                                .optional(false)
                                .popular(true)
                                .build()
                )
                .property(
                        Property.builder()
                                .id(NOTIFICATION_TEXT_PROPERTY_ID)
                                .title("Текст")
                                .description("Текст уведомлениия")
                                .type(Property.TEXT)
                                .value("")
                                .optional(false)
                                .popular(true)
                                .build()
                )
                .property(defaultProperties.multiinstance_typepackage())
                .property(defaultProperties.multiinstance_cardinalitypackage())
                .property(defaultProperties.multiinstance_collectionpackage())
                .property(defaultProperties.multiinstance_variablepackage())
                .property(defaultProperties.multiinstance_conditionpackage())
                .property(defaultProperties.isforcompensationpackage())
                .roles(Arrays.asList("Activity", "sequence_start", "sequence_end", "ActivitiesMorph", "all"))
                .build();

    }

    @Override
    public String getStencilId() {
        return SEND_NOTIFICATION_STENCIL_ID;
    }

    @Override
    public Stencil getStencil() {
        return sendNotificationStencil;
    }

    @Override
    public BpmnParseHandler getBpmnParseHandler() {
        return new AbstractActivityBpmnParseHandler<SendNotificationTask>(){
            @Override
            protected Class<? extends BaseElement> getHandledType() {
                return SendNotificationTask.class;
            }

            @Override
            protected void executeParse(BpmnParse bpmnParse, SendNotificationTask element) {
                ActivityImpl activity = createActivityOnCurrentScope(bpmnParse, element, BpmnXMLConstants.ELEMENT_TASK_SERVICE);
                ExpressionManager expressionManager = bpmnParse.getExpressionManager();
                final Expression receiver = expressionManager.createExpression(element.getReceiver());
                final Expression text = expressionManager.createExpression(element.getText());
                activity.setActivityBehavior(new TaskActivityBehavior() {
                    @Override
                    public void execute(ActivityExecution execution) throws Exception {
                        if (notificationService == null) {
                            notificationService = applicationContext.getBean(NotificationService.class);
                        }
                        String userId = String.valueOf(receiver.getValue(execution));
                        String notification = String.valueOf(text.getValue(execution));
                        notificationService.sendNotification(userId, notification);
                        leave(execution);
                    }
                });
            }
        };
    }

    @Override
    public ExtendedBaseBpmnJsonConverter getJsonConverter() {
        return null;
    }

    @Override
    public BaseBpmnXMLConverter getXMLConverter() {
        return null;
    }

    @Override
    public List<StencilRule> getRequiredRules() {
        return null;
    }
}
