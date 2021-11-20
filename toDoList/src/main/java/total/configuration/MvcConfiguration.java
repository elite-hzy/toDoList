package total.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import total.interceptor.LoginInterceptor;

@Configuration// @Configuration这个注解的作用： 1。 表示该类的是一个配置类  2. 创建该类的对象
public class MvcConfiguration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //就log路径的全都会被拦截
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/context/*");
    }
}
