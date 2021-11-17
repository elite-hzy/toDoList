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


    @Autowired(required = false)
    private ContextDao contextDao;

    @Test
    public  void insertTest(){
        contextDao.save("hzy",1,"hsdas","2021-11-14 21:10:47");
    }
}
