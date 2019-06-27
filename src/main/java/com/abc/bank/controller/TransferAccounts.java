package com.abc.bank.controller;

import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.service.iml.AccountServiceImpl;
import com.abc.bank.service.iml.BillServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */

//转账控制器
@Controller
public class TransferAccounts {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    BillServiceImpl billService;

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping("/transferaccounts")
    public JSONObject transferAccounts(@RequestParam("kahao") @NotEmpty String cardid,
                                       @RequestParam("jine") @NotEmpty String money,
                                       HttpServletRequest request) {
        Float inputmoney = Float.valueOf(money);
        JSONObject jsonObject = new JSONObject();
        Account sessacc = (Account) request.getSession().getAttribute("account");
        //获取被转账用户卡信息,判断该用户是否存在
        Account account = accountService.getAccountByCardid(cardid);
        if (account == null) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "用户不存在");
            return jsonObject;
        }
        //money合法性
        if (inputmoney <= 0 || cardid.equals(sessacc.getCardID())) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "转账金额格式有误||卡号有误");
            return jsonObject;
        }
        /*
        service层进行转账业务
         */
        return accountService.transferMoney(inputmoney, jsonObject, sessacc, account);

    }

}
