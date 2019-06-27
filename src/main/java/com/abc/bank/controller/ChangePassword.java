package com.abc.bank.controller;

import com.abc.bank.repository.AccountMapper;
import com.abc.bank.common.FinalValue;
import com.abc.bank.pojo.Account;
import com.abc.bank.service.iml.AccountServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */

/*
修改密码控制器
 */
@Controller
public class ChangePassword {
    @Autowired
    AccountServiceImpl accountService;
    @RequestMapping("/changepassword.html")
    public String changePassword(HttpServletRequest request, Map map) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return FinalValue.NOT_FIND.getValue();
        }
        map.put("cardid", account.getCardID());
        return "/changepassword";
    }

    @ResponseBody
    @RequestMapping("/passwordchange")
    public JSONObject passwordChange(@RequestParam("password1") @NotEmpty String pass1,
                                     @RequestParam("password2") @NotEmpty String pass2, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        if (!pass1.equals(pass2)) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "两次输入密码不同");
            return jsonObject;
        }
        //密码合法性验证
        if (passValid(pass1, jsonObject)) {
            return jsonObject;
        }

      Account account = (Account) request.getSession().getAttribute("account");
        account.setPassword(pass1);
        if (!accountService.updateAccount(account)) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "数据库出错");
        } else {
            jsonObject.put("state", "success");
            jsonObject.put("msg", "修改密码成功");
            jsonObject.put("address", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/");
        }
        return jsonObject;
    }

    boolean passValid(@NotEmpty @RequestParam("password1") String pass1, JSONObject jsonObject) {
        String passReg = "[1-9][0-9]{5}$";
        if (!pass1.matches(passReg)) {
            jsonObject.put("msg", "密码不合法");
            jsonObject.put("state", "failed");
            return true;
        }
        return false;
    }

}