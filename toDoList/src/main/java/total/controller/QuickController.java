package total.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import total.dao.UserDao;
import total.entity.User;

@RestController
@RequestMapping("/test")
public class QuickController {

    @Autowired(required = false)
    private UserDao userDao;

    @RequestMapping("/first")
    public User quick(User user){
        user = userDao.selectById(1);
        return user;
    }
}
