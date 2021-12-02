package total.dao;


import org.apache.ibatis.annotations.*;
import total.entity.Context;

import java.util.List;

//
public interface ContextDao {

    //从数据库扔数据进后端
    @Select("select * from context")
    List<Context> findAll();

    @Select("select * from context where id=#{id}")
    List<Context>findContextById(Integer id);

    //添加内容  这里是旧代码,这里存的过期时间是
//    @Insert("insert into context (userName, id, context,createTime,situation,Expiration) values(#{userName},#{id},#{context},#{createTime},1,#{Expiration})")
//    void save(@Param("userName") String userName, @Param("id")Integer id, @Param("context") String context,@Param("createTime") String createTime,@Param("Expiration")Integer Expiration);

    //存新内容 这里的过期时间存的是datetime
  @Insert("insert into context (userName, id, context,createTime,situation,Expiration) values(#{userName},#{id},#{context},#{createTime},1,#{Expiration})")
    void save(@Param("userName") String userName, @Param("id")Integer id, @Param("context") String context,@Param("createTime") String createTime,@Param("Expiration")String Expiration);


    //先显示要被修改的内容
    @Select("select *from context where contextID=#{contextID}")
    List<Context> showContextEditByID(Integer contextID);

    //修改内容
    @Update("update context set context=#{context} where contextID = #{contextID}")
    int updateContext(@Param("context") String context,@Param("contextID")Integer contextID );

    //修改状态情况
    @Update("update context set situation=2 where contextID = #{contextID}")
    int updateSituation(@Param("situation") Integer situation,@Param("contextID")Integer contextID );

    //删除数据
    @Delete("delete from context where contextID = #{contextID}")
    void deleteTheContext(@Param("contextID")Integer contextID );
}
