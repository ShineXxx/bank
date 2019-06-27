package com.abc.bank.controller;

import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.common.FinalValue;
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

//取款控制器
@RestController
public class WithdrawMoney {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    BillServiceImpl billService;

    @Transactional
    @RequestMapping("/withdrawmoney")
    public JSONObject withdrawMoney(@RequestParam @NotEmpty Float money, HttpServletRequest request) {
        //构建json数据
        JSONObject jsonObject = jsonObject = new JSONObject();
        //获取服务端session保存的用户卡信息
        Account account = (Account) request.getSession().getAttribute("account");
        //判断用户是否登陆过
//        if (getCardInfoFromSession(request, jsonObject, account)) return jsonObject;
        //判断金额合法性
        if (validityOfamount(money, jsonObject)) {
            return jsonObject;
        }
        //数据库操作余额
        return dbOper(money, jsonObject, account);
    }

    JSONObject dbOper(@NotEmpty @RequestParam Float money, JSONObject jsonObject, Account account) {
        if (account == null) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "未登录");
            return jsonObject;
        }
        //获取余额
        Float balance = Float.valueOf(accountService.getAccountByCardid(account.getCardID()).getAccountBalance());
        //余额充足逻辑，取款更新数据库
        accountService.updateAccbalance(jsonObject,balance,account,money, FinalValue.BILL_OUT_CATCH.getValue());
        return jsonObject;
    }

    private boolean validityOfamount(@NotNull @RequestParam Float money, JSONObject jsonObject) {
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
