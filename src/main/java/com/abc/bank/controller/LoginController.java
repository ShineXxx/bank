package com.abc.bank.controller;

import com.abc.bank.pojo.CardInfo;
import com.abc.bank.service.CardInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

//登陆、退出 控制器
@Controller
public class LoginController {
    @Autowired
    CardInfoService cardInfoService;


    @ResponseBody
    @RequestMapping("/login")
    public JSONObject login(@RequestParam("username") @NotNull String cardid, @RequestParam("password") @NotNull String password, HttpServletRequest request) {
        //构建json对象
        JSONObject result = new JSONObject();

        //合法性判断信息赋值
        String userReg = cardidJudge(cardid, result);
        String passReg = passwordJudge(password, result);


        //判断合法
        if (cardid.matches(userReg) && password.matches(passReg)) {
            //数据库判断用户密码
            CardInfo cardInfo = cardInfoService.getCardInfo(cardid);
            if (cardInfo == null) {
                result.put("state", "failed");
                result.put("msg", "用户名不存在");
                return result;
            }
            if (password.equals(cardInfo.getPassword())) {
                //密码正确
                result.put("state", "success");
                result.put("address", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/index");
                //添加session
                HttpSession session = request.getSession();
                session.setAttribute("cardinfo", cardInfo);
            } else {
                //密码错误
                result.put("state", "failed");
                result.put("msg", "用户名或密码错误");
            }
            return result;
        } else {
            //判断不合法
            result.put("state", "failed");
        }
        return result;
    }

    String passwordJudge(@NotNull @RequestParam("password") String password, JSONObject result) {
        //Password合法判断
        String passReg = "[1-9][0-9]{5}$";
        if (!password.matches(passReg)) {
            result.put("passcontent", "密码不合法");
        }
        return passReg;
    }

    String cardidJudge(@NotNull @RequestParam("username") String username, JSONObject result) {
        //username合法判断
        String userReg = "[1-9][0-9]{15}$";
        if (!username.matches(userReg)) {
            result.put("usercontent", "用户名不合法");
        }
        return userReg;
    }

    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request) {
        String page = null;
        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        CardInfo cardinfo = (CardInfo) session.getAttribute("cardinfo");
        if (cardinfo != null) {
            session.removeAttribute("cardinfo");
            session.invalidate();
            page = "/login";
            result.put("state", "success");
        } else {
            page = "404";
            result.put("state", "failed");
        }
        return page;
    }

    @ResponseBody
    @RequestMapping("/signup")
    public JSONObject signUp(CardInfo cardInfo) {
        JSONObject jsonObject = new JSONObject();
        //合法性判断信息赋值
        String userReg = cardidJudge(cardInfo.getId(), jsonObject);
        String passReg = passwordJudge(cardInfo.getPassword(), jsonObject);
        //判断合法
        if (cardInfo.getId().matches(userReg) && cardInfo.getPassword().matches(passReg)) {
            boolean flag = cardInfoService.insertCardinfo(cardInfo);
            if (flag) {
                jsonObject.put("state", "success");
                jsonObject.put("msg", "注册成功");
            } else {
                jsonObject.put("state", "failed");
                jsonObject.put("msg", "数据库出错了");
            }
        }else {
            jsonObject.put("state", "failed");
        }
        return jsonObject;
    }

}
