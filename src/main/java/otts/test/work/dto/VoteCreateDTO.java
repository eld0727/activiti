package otts.test.work.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 19.09.2015.<br/>
 * DTO with data required for creating vote
 */
@Data
public class VoteCreateDTO implements Serializable {

    /**
     * Text of the vote
     */
    private String text;

    /**
     * Owner user id
     */
    private String owner;

    /**
     * Options of the vote
     */
    private List<String> options;

    /**
     * Participants
     */
    private Set<String> participants;
}
