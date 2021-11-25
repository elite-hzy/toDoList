package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import total.Application;
import total.dao.ContextDao;
import total.entity.Context;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
//注意： 如果当前测试类是与启动类在同一级包或者是属于启动类的子包，那么不需要指定启动类的位置
@SpringBootTest(classes = Application.class)//(classes = {MybatisApplication.class}
public class programTest {

    @Autowired(required = false)
    private ContextDao contextDao;

    @Test
    public void test(){
        List<Context> contextById = contextDao.findContextById(1);
        //先全部提取出来数据
        for (Context context : contextById) {
            if (context.getSituation()==1){
                //然后进行调用,对时间进行判断
                Date date = new Date();
                long currentTime = date.getTime();
                long missonTime = context.getCreateTime().getTime();
                if (currentTime-missonTime>86400000){
                    contextDao.updateSituation(context.getSituation(),context.getContextID());
                }
                //毫秒转秒1000 秒转分60  86400 000
                System.out.println(context);

            }
        }
//        System.out.println(contextById);
    }

    @Test
    public void test1(){
        Date date = new Date();
        long currentTime = date.getTime();
        System.out.println("currentTime = " + currentTime);

    }
}
