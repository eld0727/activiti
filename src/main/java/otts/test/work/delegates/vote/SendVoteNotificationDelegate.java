package otts.test.work.delegates.vote;

import lombok.extern.log4j.Log4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import otts.test.work.dao.NotificationDAO;
import otts.test.work.dao.VoteDAO;
import otts.test.work.dbo.Notification;
import otts.test.work.dbo.Vote;

/**
 * Created by alex on 20.09.2015.<br/>
 * Delegate for user notification about vote
 */
@Log4j
@Component
public class SendVoteNotificationDelegate implements JavaDelegate {

    @Autowired
    private NotificationDAO notificationDAO;

    @Autowired
    private VoteDAO voteDAO;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String participant = (String) execution.getVariable("participant");
        Vote vote = voteDAO.getOne((Long) execution.getVariable("vote"));
        Notification notification = new Notification();
        notification.setUser(participant);
        notification.setData("You was invited into a vote: '" + vote.getText() + "'");
        notificationDAO.save(notification);
    }
}
