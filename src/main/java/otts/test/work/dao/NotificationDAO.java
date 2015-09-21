package otts.test.work.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import otts.test.work.dbo.Notification;
import otts.test.work.dbo.User;

import java.util.List;

/**
 * Created by alex on 19.09.2015.<br/>
 * {@link otts.test.work.dbo.Notification} DAO
 */
public interface NotificationDAO extends JpaRepository<Notification, Long> {

    @Query("select n from Notification n where n.delivered = false and n.user = :user")
    List<Notification> findAllUndeliveredByUser(@Param("user") User user);

    List<Notification> findAllByUser(User user);

}
