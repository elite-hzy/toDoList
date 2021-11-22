package total.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import total.dao.ContextDao;
import total.entity.Context;
import total.service.ContextService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class ContextServiceImpl implements ContextService {

    @Autowired(required = false)
    private ContextDao contextDao;

    @Autowired
    private HttpSession session;

    @Override
    public List<Context> findAll() {
        return contextDao.findAll();
    }

    @Override
    public List<Context> findContextById(Integer id) {
//        //首先先从session里获取数据
//        User user = (User) session.getAttribute("user");
//        Integer id = user.getId();
//        //用id来获取用户的内容
        return contextDao.findContextById(id);
    }

    @Override
    public void save(Map<String,Object> paramMap) {
        System.out.println(paramMap);
//        contextDao.save();
        Integer id = (Integer) paramMap.get("id");
        String createTime = (String) paramMap.get("createTime");
        String contact = (String) paramMap.get("contact");
        contextDao.save("test",id,contact,createTime);
    }

    @Override
    public List<Context> showContextEditByID(Integer contextID) {
        List<Context> contexts = contextDao.showContextEditByID(contextID);
        return contexts;
    }

    @Override
    public void updateContext(String context, Integer contextID) {

        contextDao.updateContext(context,contextID);
    }


}
