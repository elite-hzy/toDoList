package controller;

import dao.UserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class QuickController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/first")
    //映射文件未找到,具体springmvc如何互动有待商榷
    public User quick(){
        User user = userDao.selectById(1);
        return user;
    }
}
