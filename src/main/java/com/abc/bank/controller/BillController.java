package com.abc.bank.controller;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.service.iml.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//余额控制器
@Controller
public class BillController {
    @Autowired
    BillServiceImpl billService;

    @RequestMapping("/getbills.html")
    public String getBills(HttpServletRequest request,Map map){
        //session获取用户信息
        Account account = (Account) request.getSession().getAttribute("account");
        //根据用户卡号获取所有账单记录
        List<Bill> allBills = billService.getAllBills(account.getCardID());
        map.put("bills",allBills);
        return "/bills";
    }
}
