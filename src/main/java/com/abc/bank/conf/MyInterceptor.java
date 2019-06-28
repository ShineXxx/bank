package com.abc.bank.conf;

import com.abc.bank.common.FinalValue;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
public class MyInterceptor implements HandlerInterceptor {
/**
session拦截器
 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getSession().getAttribute(FinalValue.KEY_ACCOUNT.getValue())!=null){
            System.out.println("通过拦截"+request.getRequestURI());
            return true;
        }
        System.out.println("被拦截路径：{}"+ request.getRequestURI());
        try {
            response.sendRedirect("/404.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
