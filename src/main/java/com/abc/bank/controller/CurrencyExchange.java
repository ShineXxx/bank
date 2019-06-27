package com.abc.bank.controller;

import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.common.FinalValue;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.service.iml.AccountServiceImpl;
import com.abc.bank.service.iml.BillServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */

/*
货币兑换控制器
 */
@Controller
public class CurrencyExchange {

    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    BillServiceImpl billService;

    @ResponseBody
    @RequestMapping("/currencyexchange")
    public JSONObject currencyExchange(@RequestParam("money")@NotEmpty @NotNull String argmoney,
                                       @RequestParam("currencytype")@NotEmpty @NotNull String currencytype,
            HttpServletRequest request){
        /*
        构建json对象
         */
        JSONObject jsonObject=new JSONObject();
       /*
       转换取款金额
        */
        Float currmoney=Float.valueOf(argmoney);
        /*
        获取用户信息
         */
        Account account= (Account) request.getSession().getAttribute(FinalValue.KEY_ACCOUNT.getValue());
        /*
        查询并转换实时余额
         */
        Account dbacc=accountService.getAccountByCardid(account.getCardID());
        Float money = Float.valueOf(dbacc.getAccountBalance());
        /*
        更新余额
         */
        accountService.updateAccbalance(jsonObject, money, account, currmoney,FinalValue.BILL_OUT_EXANGE.getValue());

        return jsonObject;
    }

}
