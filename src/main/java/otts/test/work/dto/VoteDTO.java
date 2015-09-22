package otts.test.work.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by alex on 22.09.2015.<br/>
 * Vote DTO
 */
@Data
public class VoteDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * Vote text
     */
    private String text;

    /**
     * Show result or not
     */
    private boolean showResults;

    /**
     * Is vote finished
     */
    private boolean finished;

    /**
     * Owner of vote
     */
    private UserDTO owner;

    /**
     * Vote options
     */
    private List<OptionDTO> options;
}
