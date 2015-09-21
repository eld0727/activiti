package otts.test.work.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by alex on 16.09.2015.<br/>
 * Entity with <b>long</b> id
 */
@Getter
@Setter
@MappedSuperclass
public abstract class IdentifiedEntity implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private long id;
}
