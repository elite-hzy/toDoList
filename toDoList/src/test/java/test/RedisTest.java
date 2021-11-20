package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import total.Application;

@RunWith(SpringRunner.class)
//注意： 如果当前测试类是与启动类在同一级包或者是属于启动类的子包，那么不需要指定启动类的位置
@SpringBootTest(classes = Application.class)//(classes = {MybatisApplication.class})//指定springboot的入口在哪里
public class RedisTest {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Test
    public void test01(){
        redisTemplate.opsForValue().set("name","狗娃");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println("取出数据："+name);
    }
}
