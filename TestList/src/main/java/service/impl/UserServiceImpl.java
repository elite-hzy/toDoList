package service.impl;

import dao.UserDao;
import domain.ResultInfo;
import domain.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;


    @Autowired(required = false)
    private HttpSession session;

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
//        获取到id,然后把id给存进session里,为之后的展示内容做准备
        Integer id = byUser.getId();
        //这里的userSession1是存进session的
        User userSession = new User();
        userSession.setUser_name(user_name);
        userSession.setId(id);
        System.out.println("存进session的数据"+userSession);
        //在session里放对象
        session.setAttribute("user",userSession);

        return new ResultInfo(true,"正确");
    }
}
