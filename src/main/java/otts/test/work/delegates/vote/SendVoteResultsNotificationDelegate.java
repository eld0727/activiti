package otts.test.work.delegates.vote;

import lombok.extern.log4j.Log4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import otts.test.work.dao.NotificationDAO;
import otts.test.work.dao.UserDAO;
import otts.test.work.dbo.Notification;
import otts.test.work.dbo.Vote;

import java.util.List;

/**
 * Created by alex on 20.09.2015.<br/>
 * Delegate for user notification about vote
 */
@Log4j
@Component
public class SendVoteResultsNotificationDelegate implements JavaDelegate {

    @Autowired
    private NotificationDAO notificationDAO;

    @Autowired
    private UserDAO userDAO;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> participants = (List<String>) execution.getVariable("participants");
        Vote vote = (Vote) execution.getVariable("vote");
        for (String participant : participants) {
            Notification notification = new Notification();
            notification.setUser(userDAO.getOne(participant));
            notification.setData("You can watch results of a vote: '" + vote.getText() + "'");
            notificationDAO.save(notification);
        }
    }
}
