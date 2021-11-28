package total.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import total.dao.UserDao;
import total.entity.ResultInfo;
import total.entity.User;
import total.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/login")
    public ResultInfo quick(@RequestBody User user){
        ResultInfo resultInfo = userService.login(user);
        if (resultInfo.getSuccess()==true){
            User userSession = (User) resultInfo.getData();
//            System.out.println("要存进session的值:"+userSession);
            //在session里放对象
            session.setAttribute("user",userSession);
        }
        return resultInfo;
    }

    @RequestMapping("/createUser")
    public ResultInfo createUser(@RequestBody Map<String,Object> paramMap){
        //先获取到2个元素
        String userName = (String) paramMap.get("userName");
        String password = (String) paramMap.get("password");
        userService.createUser(userName,password);
        return new ResultInfo(true,"添加成功");
    }
}
