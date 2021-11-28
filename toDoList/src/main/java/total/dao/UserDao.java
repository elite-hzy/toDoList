package total.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import total.entity.User;

//使用上了mybatis-plus之后dao如果是单表的CRUD，只需要继承BaseMapper接口，那么这些方法全部都继承下来了
public interface UserDao extends BaseMapper<User> {


    //这里是校验 用户名和密码正不正确
    //select * from table_user where user_name='hzy' and password='123
    //校验用户名存不存在
    @Select("select * from table_user where userName=#{userName} and password=#{password}")
    public User findByUser(String userName,String password);

    //新建一个用户
    @Select("insert into table_user values(#{userName},#{password},null)")
    void createNewUser(@Param("userName")String userName, @Param("password")String password);
}
