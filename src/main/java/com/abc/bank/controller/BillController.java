package com.abc.bank.controller;

import com.abc.bank.common.FinalValue;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.service.iml.BillServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 余额控制器
 */
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Controller
public class BillController {
    @Autowired
    BillServiceImpl billService;

    @RequestMapping("/getbills.html")
    public String getBills(HttpServletRequest request,Map map){
        //session获取用户信息
        Account account = (Account) request.getSession().getAttribute("account");
        if (account==null){
            return FinalValue.NOT_FIND.getValue();
        }
        //根据用户卡号获取所有账单记录
        List<Bill> allBills = billService.getAllBills(account.getCardID());
        map.put("bills",allBills);
        return "/bills";
    }

    @ResponseBody
    @RequestMapping("/getajaxbills")
    public Map getajaxBills(HttpServletRequest request){
        //session获取用户信息
        Account account = (Account) request.getSession().getAttribute("account");
        if (account==null){
            return null;
        }
        //根据用户卡号获取所有账单记录
        List<Bill> allBills = billService.getAllBills(account.getCardID());
        Map map=new HashMap(16);
        map.put("bills",allBills);
        return map;
    }
}
