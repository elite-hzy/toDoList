package service;

import domain.Context;

import java.util.List;

public interface ContextService {

    //先获取所有数据
    List<Context> findAll();
}
