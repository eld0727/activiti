package otts.test.work.service;

import org.activiti.engine.identity.User;
import otts.test.work.dto.UserDTO;

import java.util.List;

/**
 * Created by alex on 20.09.2015.<br/>
 * TODO comment me
 */
public interface UserService {

    /**
     * Gets current logged in user
     */
    User getCurrentUser();

    /**
     * Gets user by id
     * @param id
     */
    User byId(String id);

    /**
     * Gets user by search term and page
     */
    List<UserDTO> getUsersBy(String term, Integer page);
}
