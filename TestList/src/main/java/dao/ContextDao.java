package dao;

import domain.Context;
import domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//
public interface ContextDao {

    //从数据库扔数据进后端
    @Select("select * from context")
    List<Context> findAll();

}
