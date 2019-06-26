package com.abc.bank.controller;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

//余额控制器
@Controller
public class BalanceController {
    @Autowired
    AccountMapper accountMapper;

    @RequestMapping("/balance.html")
    public String balance(HttpServletRequest request,Map map){
        //获取session中的账户卡id
        Account account = (Account) request.getSession().getAttribute("account");
        if (account==null) return "404";
        String cardid=account.getCardID();
        //数据库查询对应的卡信息
        Account account1=accountMapper.selectByPrimaryKey(cardid);
        //账户不为空逻辑
        if (account!=null){
            String yue=account1.getAccountBalance();
            map.put("type",account1.getType());
            map.put("yue",yue);
        }else {
            return "404";
        }
        //返回视图进行渲染
        return "/balance";
    }
}
