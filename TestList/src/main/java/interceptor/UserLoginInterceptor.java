package interceptor;

import domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {

    /**
     * 判断会话域中是否有用户的信息
     * 如果有就放行，没有就拦截
     * handler是 HandlerMethod对象
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //得到会话对象
        HttpSession session = request.getSession();
        //判断会话域中有没有用户对象
        User user = (User) session.getAttribute("user");
        System.out.println("session接收到的数据："+user);
        //判断如果为空就没有登录
        if (user == null) {
            System.out.println("拦截非法用户:" + request.getRequestURL());
            //这里是异步,没法用重定向
            response.sendRedirect(request.getContextPath() + "/log_on.html");
            return false;
        } else {
            //如果不为空就说明会话域中有数据,就放行
            return true;

        }

    }
}
