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
        return contextDao.findAll();
    }
}
