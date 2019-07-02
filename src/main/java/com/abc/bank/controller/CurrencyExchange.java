package com.abc.bank.controller;

import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.common.FinalValue;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.AccountCurrency;
import com.abc.bank.pojo.Bill;
import com.abc.bank.repository.AccountCurrencyMapper;
import com.abc.bank.repository.AccountMapper;
import com.abc.bank.service.iml.AccountCurrencyServiceImpl;
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
 * 货币兑换控制器
 */

/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Controller
public class CurrencyExchange {

    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    BillServiceImpl billService;
    @Autowired
    AccountCurrencyServiceImpl accountCurrencyService;

    @ResponseBody
    @RequestMapping("/currencyexchange")
    public JSONObject currencyExchange(@RequestParam("money") @NotEmpty @NotNull String argmoney,
                                       @RequestParam("currencytype") @NotEmpty @NotNull String currencytype,
                                       HttpServletRequest request) {
        /*
        构建json对象
         */
        JSONObject jsonObject = new JSONObject();
        /*
        获取用户信息
         */
        Account account = (Account) request.getSession().getAttribute(FinalValue.KEY_ACCOUNT.getValue());
        if (account == null) {
            throw new RuntimeException("account为空,获取用户信息失败");
        }
       /*
       转换取款 ?币金额
        */
        Float currmoney = Float.valueOf(argmoney);
        if (currmoney <= 0) {
            throw new RuntimeException("输入有误");
        }

        /*
        调用汇率接口获取要兑换的金额
         */
        Float ratemoney = accountCurrencyService.getMoneyByrate(currmoney, currencytype);
//        Float ratemoney = 100F;
        if (ratemoney == null) {
            throw new RuntimeException("获取汇率失败");
        }
        /*
        查询并转换实时余额
         */
        Account dbacc = accountService.getAccountByCardid(account.getCardID());
        Float money = Float.valueOf(dbacc.getAccountBalance());
        /*
        存储兑换成功的货币
         */
        /*
        获取、更新 外币余额
         */
        /*
        包装参数外币
         */
        AccountCurrency currency = new AccountCurrency();
        currency.setCardID(account.getCardID());
        currency.setType(currencytype);
        /*
        获取
         */
        AccountCurrency decadency = accountCurrencyService.getAccCurrency(currency);
        AccountCurrency accountCurrency = new AccountCurrency();
        accountCurrency.setType(currencytype);
        accountCurrency.setCardID(account.getCardID());
        /*
        数据库没有改外币信息 就创建一个新的
         */
        if (decadency == null) {
            accountCurrency.setAccountBalance(String.valueOf(ratemoney));
            accountCurrencyService.createAccCurrency(accountCurrency);
        } else {
            /*
            数据库有该外币信息 就更新余额
             */
            accountCurrency.setAccountBalance(String.valueOf(Float.valueOf(decadency.getAccountBalance()) + ratemoney));
            accountCurrencyService.updateAccCurrency(accountCurrency);
        }

        /*
        更新余额
         */
        accountService.updateAccbalance(jsonObject, money, account, ratemoney, FinalValue.BILL_OUT_EXANGE.getValue());

        return jsonObject;
    }

}
