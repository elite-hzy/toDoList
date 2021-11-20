package total.service;


import total.entity.Context;

import java.util.List;
import java.util.Map;

public interface ContextService {

    //先获取所有数据
    List<Context> findAll();

    //获取部分数据
    List<Context>findContextById();

    //添加数据
    void save(Map<String,Object> paramMap);
}
