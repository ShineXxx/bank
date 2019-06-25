package com.abc.bank.controller;

import com.abc.bank.pojo.CardInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//修改密码控制器
@Controller
public class ChangePassword {
    @RequestMapping("/changepassword.html")
    public String changePassword(HttpServletRequest request, Map map) {
        HttpSession session=request.getSession();
        CardInfo cardinfo = (CardInfo) session.getAttribute("cardinfo");
        if (cardinfo==null)return "404";
        map.put("username",cardinfo.getUsername());
        map.put("cardid",cardinfo.getId());
        return "/changepassword";
    }
}