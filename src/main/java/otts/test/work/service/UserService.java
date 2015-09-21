package otts.test.work.service;

import otts.test.work.dbo.User;

/**
 * Created by alex on 20.09.2015.<br/>
 * TODO comment me
 */
public interface UserService {

    /**
     * Gets current logged in user
     */
    User getCurrentUser();
}
