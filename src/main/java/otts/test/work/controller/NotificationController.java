package otts.test.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.dbo.Notification;
import otts.test.work.service.NotificationService;

import java.util.List;

/**
 * Created by alex on 20.09.2015.<br/>
 * Notification controller
 */
@Controller
@ResponseBody
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/undelivered", method = RequestMethod.GET)
    public List<Notification> getAllUndelivered() {
        return notificationService.getAllUndeliveredNotifications();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Notification> getAll() {
        return notificationService.getAllNotifications();
    }

}
