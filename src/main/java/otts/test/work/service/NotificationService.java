package otts.test.work.service;

import otts.test.work.dbo.Notification;

import java.util.List;

/**
 * Created by alex on 20.09.2015.<br/>
 * {@link otts.test.work.dbo.Notification} servise
 */
public interface NotificationService {

    /**
     * Finds and deliver all undelivered notifications of current user
     */
    List<Notification> getAllUndeliveredNotifications();

    /**
     * Finds all notifications of current user
     */
    List<Notification> getAllNotifications();

}
