package total.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import total.entity.Context;
import total.entity.ResultInfo;
import total.entity.User;
import total.service.ContextService;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
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

    //正式代码,展示那些指定的数据
    @RequestMapping("test")
    public ResultInfo findContextById(){
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        List<Context> contextById = contextService.findContextById(id);
        return new ResultInfo(true,contextById);
    }

    @RequestMapping("/logout")
    public void logout(){
        //服务器状态销毁  使用会话域过期方法
        session.invalidate();
    }

    //前端后端传值失败
    //byUser已经存进了id,等会添加内容的时候直接从这里获取就行
    @RequestMapping("/testCreate")
    public void push(HttpSession session, @RequestBody Map<String,Object>paramMap){
//        //如果没有传500
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        String contact = (String) paramMap.get("context");
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("id",id);
        stringMap.put("createTime",time);
        stringMap.put("contact",contact);
//        Context context = new Context();
//        context.setCreateTime(Timestamp.valueOf("2021-11-20 16:38:02"));
        contextService.save(stringMap);
    }

    //返回显示的内容
    @RequestMapping("/EditContextShow")
    public ResultInfo EditContextShow(@RequestBody Map<String,Object>paramMap){
        Integer contextID = (Integer) paramMap.get("contextID");
        List<Context> contexts = contextService.showContextEditByID(contextID);
        System.out.println("根据指定ID而搜索到的数据:"+contexts);
        return new ResultInfo(true,contexts);
//        System.out.println(contextID);
//        return new ResultInfo(true);
    }

    //修改内容
    @RequestMapping("/EditUpdate")
    public ResultInfo EditUpdate(@RequestBody Map<String,Object>paramMap){
        //先获取到2个元素
        String context = (String) paramMap.get("context");
        Integer contextID = (Integer) paramMap.get("contextID");

        contextService.updateContext(context,contextID);
        return new ResultInfo(true,"修改成功");
    }

}
