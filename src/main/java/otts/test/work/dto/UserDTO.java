package otts.test.work.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by alex on 22.09.2015.<br/>
 * User DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**
     * ID
     */
    private String id;

    /**
     * First name
     */
    private String firstName;

    /**
     * last name
     */
    private String lastName;
}
