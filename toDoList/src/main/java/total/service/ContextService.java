package total.service;


import org.apache.ibatis.annotations.Param;
import total.entity.Context;

import java.util.List;
import java.util.Map;

public interface ContextService {

    //先获取所有数据
    List<Context> findAll();

    //获取部分数据
    List<Context>findContextById(Integer id);

    //添加数据
    void save(Map<String,Object> paramMap);


    //先显示要被修改的内容
    List<Context> showContextEditByID(Integer contextID);

    //修改内容
    void updateContext(String context,Integer contextID );

    //修改状态情况
    void updateSituation(Integer situation,Integer contextID);

    //删除数据
    void deleteTheContext(Integer contextID );

    //测试获取数据,并且依次进行校验
}
