package service.impl;

import dao.ContextDao;
import domain.Context;
import domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContextService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ContextServiceImpl implements ContextService {

    @Autowired
    private ContextDao contextDao;

    @Autowired
    private HttpSession session;

    @Override
    public List<Context> findAll() {
        return contextDao.findAll();
    }

    @Override
    public List<Context> findContextById() {
        //首先先从session里获取数据
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        //用id来获取用户的内容
        return contextDao.findContextById(id);

    }

    @Override
    public void save(Map<String,Object> paramMap) {
//        contextDao.save();
        Integer id = (Integer) paramMap.get("id");
        String createTime = (String) paramMap.get("createTime");
        String contact = (String) paramMap.get("contact");
        contextDao.save("test",id,contact,createTime);
    }


}
