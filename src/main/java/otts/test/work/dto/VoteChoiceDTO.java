package otts.test.work.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by alex on 20.09.2015.<br/>
 * Vote choice DTO
 */
@Data
public class VoteChoiceDTO implements Serializable {

    /**
     * {@link otts.test.work.dbo.VoteOption#id}
     */
    private Long option;

    /**
     * Participant id
     */
    private String participant;
}
