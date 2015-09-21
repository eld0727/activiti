package otts.test.work.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import otts.test.work.dao.UserDAO;
import otts.test.work.dbo.User;
import otts.test.work.service.UserService;

/**
 * Created by alex on 20.09.2015.<br/>
 * TODO comment me
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal == null) {
            return null;
        } else if(principal instanceof org.springframework.security.core.userdetails.User) {
            principal = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        }
        return userDAO.getOne((String) principal);
    }
}
