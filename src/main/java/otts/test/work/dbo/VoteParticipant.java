package otts.test.work.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by alex on 19.09.2015.<br/>
 * Vote participant
 */
@Getter
@Setter
@Entity
@Table(name = "activiti_my_vote_participant")
public class VoteParticipant extends IdentifiedEntity implements Serializable {

    /**
     * Vote
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    /**
     * Participant
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Participant choice
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "vote_option_id")
    private VoteOption voteOption;

}
