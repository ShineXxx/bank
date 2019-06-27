package com.abc.bank.controller;

import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.common.MoneyUtil;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.service.iml.AccountServiceImpl;
import com.abc.bank.service.iml.BillServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */

/*
    存款控制器
 */
@RestController
public class DepositMoney {

    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    BillServiceImpl billService;


    @Transactional
    @RequestMapping("/depositmoney")
    public JSONObject depositMoney(@RequestParam @NotEmpty Float money, HttpServletRequest request) {
        //构建json数据
        JSONObject jsonObject=new JSONObject();
        //用户卡信息
        Account account= (Account) request.getSession().getAttribute("account");

        //判断用户是否登陆过
//        if (getCardInfoFromSession(request, jsonObject,cardInfo)) return jsonObject;

        //输入金额合法性
        if (validityOfamount(money, jsonObject)) {
            return jsonObject;
        }

        //数据库更新余额
        return dbOper(money, jsonObject, account,request);
    }

    JSONObject dbOper(@NotEmpty @RequestParam Float money, JSONObject jsonObject, Account account,HttpServletRequest request) {
        Float balance = Float.valueOf(account.getAccountBalance());
        //更新存款逻辑
        if (account==null) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "未登录");
            return jsonObject;
        }
        //更新后金额
        Float newmoney=balance+money;
        account.setAccountBalance(newmoney.toString());
        if (accountService.updateAccount(account)){
            //添加账单纪录
            Bill bill=new Bill();
            bill.setCardID(account.getCardID());
            bill.setAffairType("存入");
            bill.setTradeBalance("+"+money.toString());
            bill.setTradeTime(DateconversionUtil.dateConversion(new Date(),"yyyy-mm-dd HH:mm:ss"));
            if (billService.insertBill(bill)){
                jsonObject.put("state", "success");
                jsonObject.put("msg", "存款成功");
                jsonObject.put("address","/index");
                return jsonObject;
            }
        }else {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "数据库错误");
        }
        return jsonObject;
    }

    boolean getCardInfoFromSession(HttpServletRequest request, JSONObject jsonObject,Account account) {
        if (account==null){
            jsonObject.put("state","error");
            jsonObject.put("address","http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/404");
            return true;
        }
        return false;
    }

    boolean validityOfamount(@NotNull @RequestParam Float money, JSONObject jsonObject) {
        boolean flag= MoneyUtil.ismultipleOfhundred(money,100);
        if (flag==false){
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "金额格式错误(100整数倍)");
            return true;
        }
        return false;
    }

}
