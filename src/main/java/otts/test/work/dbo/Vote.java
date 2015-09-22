package otts.test.work.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alex on 19.09.2015.<br/>
 * Vote entity
 */
@Getter
@Setter
@Entity
@Table(name = "activiti_my_vote")
public class Vote extends IdentifiedEntity implements Serializable {

    /**
     * Text of vote
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * Is vote finished
     */
    @Column(name = "finished", nullable = false)
    private boolean finished;

    /**
     * Activiti process id
     */
    @Column(name = "process_id")
    private String processId;

    /**
     * Owner of vote
     */
    @Column(nullable = false)
    private String owner;

    /**
     * Vote options
     */
    @OneToMany(mappedBy = "vote", fetch = FetchType.EAGER)
    private List<VoteOption> options;

    /**
     * Vote participants
     */
    @OneToMany(mappedBy = "vote", fetch = FetchType.EAGER)
    private List<VoteParticipant> participants;

}
