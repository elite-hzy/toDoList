package total.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import total.dao.ContextDao;
import total.entity.Context;
import total.service.ContextService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ContextServiceImpl implements ContextService {

    @Autowired(required = false)
    private ContextDao contextDao;

//    @Autowired
//    private HttpSession session;

    @Override
    public List<Context> findAll() {
        return contextDao.findAll();
    }

//    @Override
//    public List<Context> findContextById(Integer id) {
////        //首先先从session里获取数据
////        User user = (User) session.getAttribute("user");
////        Integer id = user.getId();
////        //用id来获取用户的内容
//        return contextDao.findContextById(id);
//    }
    @Override
    public List<Context> findContextById(Integer id) {
//        //首先先从session里获取数据
//        User user = (User) session.getAttribute("user");
//        Integer id = user.getId();
//        //用id来获取用户的内容
        List<Context> contextById = contextDao.findContextById(id);
        //先全部提取出来数据
        for (Context context : contextById) {
            if (context.getSituation()==1){
                //先把每个函数的过期时间拿出来(单位 时)
//                Integer expiration = context.getExpiration();
                Date expiration = context.getExpiration();
//                System.out.println(expiration);
                //然后进行调用,对时间进行判断
                Date date = new Date();
                long currentTime = date.getTime();
//                long missonTime = context.getCreateTime().getTime();
                long missontime = expiration.getTime();
                //如果当前时间  减去  （生成任务的时间和设置过期时间）大于0证明已经过期
                if (currentTime>missontime){
                    contextDao.updateSituation(context.getSituation(),context.getContextID());
                }
                //毫秒转秒1000 秒转分60  86400 000'
                //小时转秒 就先毫秒转秒 1000  秒转分 60 分转小时60 =3600000
            }
        }
        List<Context> contextById1 = contextDao.findContextById(id);
//        System.out.println("contextById1 = " + contextById1);        //contextById1 = [Context(userName=test, id=1, createTime=Thu Dec 02 21:36:17 CST 2021, context=1312, situation=2, contextID=37, Expiration=Thu Dec 02 21:36:17 CST 2021),
        return contextById1;
    }

//    @Override 备用代码
//    public List<Context> findContextById(Integer id) {
////        //首先先从session里获取数据
////        User user = (User) session.getAttribute("user");
////        Integer id = user.getId();
////        //用id来获取用户的内容
//        List<Context> contextById = contextDao.findContextById(id);
//        //先全部提取出来数据
//        for (Context context : contextById) {
//            if (context.getSituation()==1){
//                //然后进行调用,对时间进行判断
//                Date date = new Date();
//                long currentTime = date.getTime();
//                long missonTime = context.getCreateTime().getTime();
//                if (currentTime-missonTime>86400000){
//                    contextDao.updateSituation(context.getSituation(),context.getContextID());
//                }
//                //毫秒转秒1000 秒转分60  86400 000
//            }
//        }
//        return contextById;
//    }
    @Override
    public void save(Map<String,Object> paramMap) {
//        System.out.println(paramMap);
//        contextDao.save();
        Integer id = (Integer) paramMap.get("id");
        String createTime = (String) paramMap.get("createTime");
        String contact = (String) paramMap.get("contact");
        String  Expiration = (String) paramMap.get("Expiration");
        System.out.println("Expiration = " + Expiration);
        contextDao.save("test",id,contact,createTime,Expiration);
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

    //修改状态的ID
    @Override
    public void updateSituation(Integer situation, Integer contextID) {
        contextDao.updateSituation(situation,contextID);

    }
    //删除数据
    @Override
    public void deleteTheContext(Integer contextID) {
        contextDao.deleteTheContext(contextID);
    }


}
