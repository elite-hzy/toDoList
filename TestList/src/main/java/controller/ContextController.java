package controller;

import domain.Context;
import domain.ResultInfo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ContextService;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import java.util.List;

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


}
