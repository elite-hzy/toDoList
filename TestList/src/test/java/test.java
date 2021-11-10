import dao.ContextDao;
import domain.Context;
import domain.ResultInfo;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {

    @Autowired
    private UserService userService;

    @Autowired
    private ContextDao contextDao;

    @Test
    public  void insertTest(){
//        User user = new User(null, "hzy", "123");
//        System.out.println(user);
//        ResultInfo login = userService.login(user);
//        System.out.println(login);
        List<Context> all = contextDao.findAll();
        System.out.println(all);
    }
}
