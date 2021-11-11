package service.impl;

import dao.ContextDao;
import domain.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContextService;

import java.util.List;

@Service
public class ContextServiceImpl implements ContextService {

    @Autowired
    private ContextDao contextDao;

    @Override
    public List<Context> findAll() {
//        先测试在业务层里代码怎么样
        List<Context> all = contextDao.findAll();
        System.out.println(all);
        return all;
    }
}
