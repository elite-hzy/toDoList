package controller;

import domain.Context;
import domain.ResultInfo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ContextService;

import java.util.List;

@RestController
@RequestMapping(value = "/context",produces = "application/json;charset=utf-8")
public class ContextController {

    @Autowired
    private ContextService contextService;


    @RequestMapping("/push")
    public ResultInfo quick(){
        List<Context> contextList = contextService.findAll();
        System.out.println("控制器里的内容:"+contextList);
//        在控制器和业务层的时候，java的datetime还很正常 createTime=Sat Oct 30 00:03:28 CST 2021,
//        转json的时候就出错了 一个解决就是
        return new ResultInfo(true,contextList);
    }
}
