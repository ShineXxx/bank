package com.abc.bank.controller.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@Controller
public class LoginController {
    @ResponseBody
    @RequestMapping("/login")
    public JSONObject login(@RequestParam("username") @NotNull String username, @RequestParam("password") @NotNull String password, HttpServletRequest request) {
        //构建json对象
        JSONObject result = new JSONObject();
        //username合法判断
        String userReg = "[0-9]{3}$";
        if (!username.matches(userReg)) {
            result.put("usercontent", "用户名不合法");
        }
        //Password合法判断
        String passReg = "[0-9]{3}$";
        if (!password.matches(passReg)) {
            result.put("passcontent", "密码不合法");
        }
        //判断合法
        if (username.matches(userReg) && password.matches(passReg)) {
            //数据库判断用户密码
            boolean flag = true;
            if (flag) {
                //密码正确
                result.put("state", "success");
                result.put("address", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/index");
            }else {
                //密码错误
                result.put("state", "failed");
                result.put("msg", "用户名或密码错误");

            }
        }else {
            //判断不合法
            result.put("state", "failed");
        }
        return result;
    }

    @RequestMapping("/")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }
}
