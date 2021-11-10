package service;


import domain.ResultInfo;
import domain.User;

public interface UserService {

    //校验用户用户名和密码
    /**
     * 作用：登陆
     * @param user
     * @return
     */
    public ResultInfo login(User user);
}
