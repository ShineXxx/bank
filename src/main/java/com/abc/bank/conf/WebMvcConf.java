package com.abc.bank.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

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
        list.add("/*.html");//其他错误页面

        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(list);

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/login");//返回login.jsp
        registry.addViewController("/index").setViewName("/index");//用户主界面index.jsp
        registry.addViewController("/404.html").setViewName("404");//错误提示页面error.jsp
    }


    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // templateEngine
        return templateEngine;
    }

    /**
     * 对thymeleaf的视图解析器，解析到webapp下的html目录下查找对应的页面
     * @return
     */
    @Bean
    public ThymeleafViewResolver viewResolverThymeLeaf() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setOrder(2);
        viewResolver.setViewNames(new String[]{"*"});
        return viewResolver;
    }

    //jsp页面的视图解析器，解析到webapp下的jsp/目录下查找对应的jsp页面
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("/*");
        resolver.setOrder(1);
        return resolver;
    }

/*    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    *//**
     * 配置资源路径
     * @param registry
     *//*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/" + "/static/");
    }*/
}
