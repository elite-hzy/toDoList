package total.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import total.entity.ResultInfo;
import total.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("=====【前置通知-preHandle】======");
        //得到会话对象
        HttpSession session = request.getSession();
        //判断会话域中有没有用户对象
        User user = (User) session.getAttribute("user");
        System.out.println("session接收到的数据："+user);
        //判断如果为空就没有登录
        if (user == null) {
            System.out.println("拦截非法用户:" + request.getRequestURL());
            //这里是异步,没法用重定向
//            response.sendRedirect(request.getContextPath() + "/log_on.html");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            ResultInfo resultInfo = new ResultInfo(false,"您没有登录账号");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultInfo);
            out.print(json);
            return false;
        } else {
            //如果不为空就说明会话域中有数据,就放行
            return true;

        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("=====【后置通知-postHandle】======");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("=====【最终通知-afterCompletion】======");
    }
}
