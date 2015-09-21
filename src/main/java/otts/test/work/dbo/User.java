package otts.test.work.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alex on 19.09.2015.<br/>
 * User Entity
 */
@Getter
@Setter
@Entity
@Table(name = "ACT_ID_USER")
public class User implements Serializable{

    /**
     * ID
     */
    @Id
    @Column(name = "ID_", nullable = false)
    private String id;

    @Column(name = "FIRST_")
    private String firstName;

    @Column(name = "LAST_")
    private String lastName;
}
