package dao;

import domain.Context;
import domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

//
public interface ContextDao {

    //从数据库扔数据进后端
    @Select("select * from context")
    List<Context> findAll();

    @Select("select * from context where id=#{id}")
    List<Context>findContextById(Integer id);

    //添加内容
    @Insert("insert into context (userName, id, context,createTime) values(#{userName},#{id},#{context},#{createTime})")
    void save(@Param("userName")String userName, @Param("id")Integer id, @Param("context") String context,@Param("createTime") String createTime);
}
