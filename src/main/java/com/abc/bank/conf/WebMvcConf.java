package com.abc.bank.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List list=new ArrayList();
        list.add("/");//返回login.jsp
        list.add("/login");//ajax登录请求路径
        list.add("/error");//错误页面
        list.add("/dist/**");//js，css静态资源
        list.add("/imges/**");//图片资源
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/login","/error","/dist/**","/imges/**");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");//返回login.jsp
        registry.addViewController("/index").setViewName("index");//用户主界面index.jsp
        registry.addViewController("/error").setViewName("error");//错误提示页面error.jsp
    }
}
