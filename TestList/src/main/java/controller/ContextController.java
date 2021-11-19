package controller;

import domain.Context;
import domain.NoDateContext;
import domain.ResultInfo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ContextService;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/context",produces = "application/json;charset=utf-8")
public class ContextController {

    @Autowired
    private ContextService contextService;

    @Autowired
    private HttpSession session;

    @RequestMapping("/push")
    public ResultInfo quick(){
        List<Context> contextList = contextService.findAll();
        System.out.println(contextList);
        return new ResultInfo(true,contextList);
    }

    @RequestMapping("test")
    public ResultInfo findContextById(){
        List<Context> contextById = contextService.findContextById();
        return new ResultInfo(true,contextById);
    }

    @RequestMapping("/logout")
    public void logout(){
        //服务器状态销毁  使用会话域过期方法
        session.invalidate();
    }
    //前端后端传值失败
    @RequestMapping("/testCreate")
    public void push(HttpSession session, @RequestBody Map<String,Object>paramMap){
        //如果没有传500
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        String contact = (String) paramMap.get("contact");
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("id",id);
        stringMap.put("createTime",time);
        stringMap.put("contact",contact);

        contextService.save(stringMap);
    }
    //前端后端传值失败
//    @RequestMapping("/testCreate")
//    public void push(@RequestBody NoDateContext context){
//        User user = (User) session.getAttribute("user");
//        Integer id = user.getId();
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = format.format(date);
////        String contact = (String) paramMap.get("contact");
//        String contact = context.getContext();
//        Map<String, Object> stringMap = new HashMap<>();
//        stringMap.put("id",id);
//        stringMap.put("createTime",time);
//        stringMap.put("contact",contact);
//        System.out.println("要传入的值"+stringMap);
//        contextService.save(stringMap);
//    }


}
