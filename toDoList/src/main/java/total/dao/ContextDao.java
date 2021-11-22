package total.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import total.entity.Context;

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
    void save(@Param("userName") String userName, @Param("id")Integer id, @Param("context") String context,@Param("createTime") String createTime);

    //先显示要被修改的内容
    @Select("select *from context where contextID=#{contextID}")
    List<Context> showContextEditByID(Integer contextID);

    //修改内容
    @Update("update context set context=#{context} where contextID = #{contextID}")
    int updateContext(@Param("context") String context,@Param("contextID")Integer contextID );
}
