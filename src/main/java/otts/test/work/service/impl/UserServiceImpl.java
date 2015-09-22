package otts.test.work.service.impl;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import otts.test.work.dto.UserDTO;
import otts.test.work.dto.converter.UserConverter;
import otts.test.work.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 20.09.2015.<br/>
 * User service
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private UserConverter userConverter;

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal == null) {
            return null;
        } else if(principal instanceof org.springframework.security.core.userdetails.User) {
            principal = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        }
        return byId((String) principal);
    }

    @Override
    public User byId(String id) {
        return identityService.createUserQuery().userId(id).singleResult();
    }

    @Override
    public List<UserDTO> getUsersBy(String term, Integer page) {

        UserQuery userQuery = identityService.createUserQuery();
        if (term != null) {
            userQuery = userQuery.userFirstNameLike("%" + term + "%");
        }
        List<User> users;
        if (page == null) {
            users = userQuery.list();
        } else {
            final int count = 20;
            int first = (page - 1) * count;
            users = userQuery.listPage(first, count);
        }

        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userConverter.convert(user));
        }
        return userDTOs;
    }
}
