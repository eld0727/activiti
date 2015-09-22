package otts.test.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.dto.UserDTO;
import otts.test.work.service.UserService;

import java.util.List;

/**
 * Created by alex on 23.09.2015.<br/>
 * TODO comment me
 */
@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<UserDTO> getUsers(
            @RequestParam(required = false) String term,
            @RequestParam(required = false) Integer page
    ) {
        return userService.getUsersBy(term, page);
    }
}
