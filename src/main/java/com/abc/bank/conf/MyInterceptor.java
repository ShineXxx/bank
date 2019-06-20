package com.abc.bank.conf;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getSession().getAttribute("user")!=null){
            return true;
        }
        System.out.println("请求路径：{}"+ request.getRequestURI());
        try {
            response.sendRedirect("/error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
