package controller;

import domain.ResultInfo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResultInfo quick(@RequestBody User user){
        ResultInfo resultInfo = userService.login(user);
        return resultInfo;
    }
}
