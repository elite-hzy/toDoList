package service.impl;

import dao.UserDao;
import domain.ResultInfo;
import domain.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    //登录界面
    @Override
    public ResultInfo login(User user) {
        System.out.println("获取到的数据"+user);
        //先获取到用户名和密码
        String user_name = user.getUser_name();
        String password = user.getPassword();
        User byUser = userDao.findByUser(user_name, password);
        if (byUser == null) {
            return new ResultInfo(false,"用户名或密码错误");
        }
        return new ResultInfo(true,"正确");
    }
}
