package com.abc.bank.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class in implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/abc/*").excludePathPatterns("/");
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/login/*").excludePathPatterns("/");

    }
}
