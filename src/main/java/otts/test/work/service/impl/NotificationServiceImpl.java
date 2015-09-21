package otts.test.work.service.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.identity.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import otts.test.work.dao.NotificationDAO;
import otts.test.work.dbo.Notification;
import otts.test.work.dbo.User;
import otts.test.work.service.NotificationService;
import otts.test.work.service.UserService;

import java.util.List;

/**
 * Created by alex on 20.09.2015.<br/>
 * Implementation for {@link NotificationService}
 */
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDAO notificationDAO;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public List<Notification> getAllUndeliveredNotifications() {
        User currentUser = userService.getCurrentUser();
        List<Notification> allUndeliveredByUser = notificationDAO.findAllUndeliveredByUser(currentUser);
        if(!CollectionUtils.isEmpty(allUndeliveredByUser)) {
            for (Notification notification : allUndeliveredByUser) {
                notification.setDelivered(true);
            }
        }
        return allUndeliveredByUser;
    }

    @Override
    public List<Notification> getAllNotifications() {
        User currentUser = userService.getCurrentUser();
        return notificationDAO.findAllByUser(currentUser);
    }
}
