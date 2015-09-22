package otts.test.work.dbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alex on 20.09.2015.<br/>
 * Notification entity
 */
@Getter
@Setter
@Entity
@Table(name = "activiti_my_notification")
@JsonIgnoreProperties({"user"})
public class Notification extends IdentifiedEntity implements Serializable {

    /**
     * If {@link #module} is null then it is just a message, otherwise it is module function data
     */
    @Column(nullable = true)
    private String data;

    /**
     * AMD module for uncommon drawing
     */
    @Column(nullable = true)
    private String module;

    /**
     * User to notify
     */
    @Column(nullable = false)
    private String user;

    /**
     * Is message delivered to {@link #user}
     */
    @Column(nullable = false)
    private boolean delivered;
}
