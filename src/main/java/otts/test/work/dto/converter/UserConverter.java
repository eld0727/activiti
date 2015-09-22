package otts.test.work.dto.converter;

import org.activiti.engine.identity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import otts.test.work.dto.UserDTO;

/**
 * Created by alex on 22.09.2015.<br/>
 * User -> UserDTO
 */
@Component
public class UserConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {
        return new UserDTO(source.getId(), source.getFirstName(), source.getLastName());
    }
}
