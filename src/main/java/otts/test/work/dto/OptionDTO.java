package otts.test.work.dto;

import lombok.Data;

/**
 * Created by alex on 22.09.2015.<br/>
 * Vote option DTO
 */
@Data
public class OptionDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * Text of the option
     */
    private String text;

    /**
     * Count of participants that choose this option
     */
    private Integer count;

    /**
     * Count of participants that choose this option
     */
    private Double percent;

}
