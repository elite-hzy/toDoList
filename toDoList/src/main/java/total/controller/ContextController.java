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
import java.text.ParseException;
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
    //把用户名返给网站
    @RequestMapping("/getUserName")
    public ResultInfo getUserName(){
        User user = (User) session.getAttribute("user");
//        System.out.println("session接收到的数据："+user);
        String name = user.getUserName();
        return new ResultInfo(true,"成功",name);
    }

    //正式代码,展示那些指定的数据
    @RequestMapping("test")
    public ResultInfo findContextById(){
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        List<Context> contextById = contextService.findContextById(id);
        System.out.println("contextById = " + contextById);
        return new ResultInfo(true,contextById);
    }

    @RequestMapping("/logout")
    public void logout(){
        //服务器状态销毁  使用会话域过期方法
        session.invalidate();
    }

    //新建内容
    //byUser已经存进了id,等会添加内容的时候直接从这里获取就行
    @RequestMapping("/testCreate")
    public void push(HttpSession session, @RequestBody Map<String,Object>paramMap) throws ParseException {
//        //如果没有传500
        User user = (User) session.getAttribute("user");
        Integer id = user.getId();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        String contact = (String) paramMap.get("context");
//        String Expiration1 = (String) paramMap.get("Expiration");
        //提取出来的新时间戳
        String newExpiration = (String) paramMap.get("newExpiration");
        //System.out.println("返回的时间:"+newExpiration);//返回的时间:2021-12-17T10:01:00.000Z
        String[] ts = newExpiration.split("T");
        //首先要先切割出T 切割之后切割里面的.
        //切割出来的内容2021-12-02
        //切割出来的内容06:36:39.000Z
        //1)当需要用“.”分割字符串,需要用split(“\\.”)或者split(“[.]”)
        String[] split = ts[1].split("[.]");
        String newDataBase = "";
        newDataBase = ts[0]+" "+split[0];
//        System.out.println("newDataBase = " + newDataBase);//newDataBase = 2021-12-02 06:57:06
        //字符串转日期
        Date parse = sdf.parse(newDataBase);//因为这里newDateBase是没加8小时的时间
        //思路就是字符串转成长整型 长整型加8小时的毫秒数  然后转成Date类,通过simpleDateFormat来转成字符串
        long time1 = parse.getTime();//1638407627000
        //加8小时  x1000到秒  x60到分  x60到时 x8
        time1 =time1+8000*60*60;
        Date date1 = new Date(time1);
        String format1 = sdf.format(date1);// 2021-12-02 17:16:28
        //转码成功的函数
//        Integer Expiration = Integer.parseInt(Expiration1);
        Map<String, Object> stringMap = new HashMap<>();
        stringMap.put("id",id);
        stringMap.put("createTime",time);
        stringMap.put("contact",contact);
        stringMap.put("Expiration",format1);
//        Context context = new Context();
//        context.setCreateTime(Timestamp.valueOf("2021-11-20 16:38:02"));
        contextService.save(stringMap);
    }

//    //返回显示的内容
//    @RequestMapping("/EditContextShow")
//    public ResultInfo EditContextShow(@RequestBody Map<String,Object>paramMap){
//        Integer contextID = (Integer) paramMap.get("contextID");
//        List<Context> contexts = contextService.showContextEditByID(contextID);
////        根据指定ID而搜索到的数据:[Context(userName=hzy, id=1, createTime=Sat Oct 30 00:03:28 CST 2021, context=修改内容, situation=1, contextID=1)]
//        System.out.println("根据指定ID而搜索到的数据:"+contexts);
////        //增强for也只是获取到每一个for
////        for (Context context : contexts) {
////            System.out.println(context);
////        }
//        //这里获取到的是按照顺序的context,可以用强转
//        Context context = contexts.get(0);
//        String userName = context.getUserName();
//        System.out.println(userName);
//        return new ResultInfo(true,contexts);
////        return new ResultInfo(true);
//    }

//    //返回显示的内容
    @RequestMapping("/EditContextShow")
    public Context EditContextShow(@RequestBody Map<String,Object>paramMap){
        Integer contextID = (Integer) paramMap.get("contextID");
        List<Context> contexts = contextService.showContextEditByID(contextID);
//        根据指定ID而搜索到的数据:[Context(userName=hzy, id=1, createTime=Sat Oct 30 00:03:28 CST 2021, context=修改内容, situation=1, contextID=1)]
//        System.out.println("根据指定ID而搜索到的数据:"+contexts);
        //这里获取到的是按照顺序的context,可以用强转
        Context context = contexts.get(0);
        Date time = context.getCreateTime();
        long time1 = time.getTime();
//        System.out.println("time获取的毫秒 = " + time1);//= 1637637999000
//        return new ResultInfo(true,co
//        ntexts);
//        return new ResultInfo(true);
        return context;
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

    //修改状态的ID
    @RequestMapping("/EditSituation")
    public ResultInfo EditSituation(@RequestBody Map<String,Object>paramMap){
        //先获取到2个元素
        Integer contextID = (Integer) paramMap.get("contextID");
        Integer situation = (Integer) paramMap.get("situation");
        if(situation!=1){
            return new ResultInfo(false,"只有未完成才能修改");
        }
        contextService.updateSituation(situation,contextID);
        return new ResultInfo(true,"修改成功");
    }

    @RequestMapping("/deleteContext")
    public ResultInfo deleteContext(@RequestBody Map<String,Object>paramMap){
        //先获取到id
        Integer contextID = (Integer) paramMap.get("contextID");
        contextService.deleteTheContext(contextID);
        return new ResultInfo(true,"删除成功");
    }
}
