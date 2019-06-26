package com.abc.bank.controller;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.common.MoneyUtil;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.pojo.CardInfo;
import com.abc.bank.service.iml.AccountServiceImpl;
import com.abc.bank.service.iml.BillServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

//取款控制器
@RestController
public class WithdrawMoney {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    BillServiceImpl billService;

    @Transactional
    @RequestMapping("/withdrawmoney")
    public JSONObject withdrawMoney(@RequestParam @NotEmpty Long money, HttpServletRequest request) {
        //构建json数据
        JSONObject jsonObject = jsonObject = new JSONObject();
        //获取服务端session保存的用户卡信息
        Account account = (Account) request.getSession().getAttribute("account");
        //判断用户是否登陆过
//        if (getCardInfoFromSession(request, jsonObject, account)) return jsonObject;
        //判断金额合法性
        if (validityOfamount(money, jsonObject)) return jsonObject;
        //数据库操作余额
        return dbOper(money, jsonObject, account);
    }

    JSONObject dbOper(@NotEmpty @RequestParam Long money, JSONObject jsonObject, Account account) {
        if (account == null) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "未登录");
            return jsonObject;
        }
        //获取余额
        Long balance = Long.valueOf(accountService.getAccountByCardid(account.getCardID()).getAccountBalance());
        //余额充足逻辑，取款更新数据库
        if (money <= balance) {
            //数据库减余额
            account.setAccountBalance(Long.toString((balance - money)));
            if (accountService.updateAccount(account)) {
                //添加账单纪录
                Bill bill = new Bill();
                bill.setCardID(account.getCardID());
                bill.setAffairType("支出");
                bill.setTradeBalance("-" + money.toString());
                bill.setTradeTime(DateconversionUtil.dateConversion(new Date(), "yyyy-mm-dd HH:mm:ss"));
                if (billService.insertBill(bill)) {
                    jsonObject.put("state", "success");
                    jsonObject.put("msg", "操作成功");
                    jsonObject.put("address", "/index");
                }
            } else {
                jsonObject.put("state", "failed");
                jsonObject.put("msg", "数据库错误");
            }
        } else {
            //余额不足逻辑，返回取款失败信息
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "账户余额不足");

        }
        return jsonObject;
    }

    private boolean validityOfamount(@NotNull @RequestParam Long money, JSONObject jsonObject) {
        boolean flag = MoneyUtil.ismultipleOfhundred(money, 50);
        if (flag == false) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "金额格式错误(50整数倍)");
            return true;
        }
        return false;
    }

    boolean getCardInfoFromSession(HttpServletRequest request, JSONObject jsonObject, Account account) {
        if (account == null) {
            jsonObject.put("state", "failed");
            jsonObject.put("address", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/404");
            return true;
        }
        return false;
    }
}
