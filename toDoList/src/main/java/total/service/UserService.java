package total.service;

import total.entity.ResultInfo;
import total.entity.User;

import java.util.Map;

public interface UserService {

    //校验用户用户名和密码
    /**
     * 作用：登陆
     * @param user
     * @return
     */
    public ResultInfo login(User user);

    //新建用户
    void createUser(String userName,String password);
}
