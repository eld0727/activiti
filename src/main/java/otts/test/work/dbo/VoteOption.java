package otts.test.work.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by alex on 19.09.2015.<br/>
 * Vote option
 */
@Getter
@Setter
@Entity
@Table(name = "activity_my_vote_option")
public class VoteOption extends IdentifiedEntity implements Serializable {

    /**
     * Text of vote option
     */
    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
