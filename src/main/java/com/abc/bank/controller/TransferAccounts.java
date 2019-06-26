package com.abc.bank.controller;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.service.iml.AccountServiceImpl;
import com.abc.bank.service.iml.BillServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

//转账控制器
@Controller
public class TransferAccounts {
    @Autowired
    AccountServiceImpl accountService;
    @Autowired
    BillServiceImpl billService;

    @Transactional
    @ResponseBody
    @RequestMapping("/transferaccounts")
    public JSONObject transferAccounts(@RequestParam("kahao") @NotEmpty String cardid,
                                       @RequestParam("jine") @NotEmpty String money,
                                       HttpServletRequest request) {
        Long inputmoney = Long.valueOf(money);
        JSONObject jsonObject = new JSONObject();
        Account sessacc = (Account) request.getSession().getAttribute("account");
        //money合法性
        if (inputmoney <= 0||cardid.equals(sessacc.getCardID())) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "转账金额格式有误||卡号有误");
            return jsonObject;
        }
        //判断当前用户余额是否充足
        Account dbacc = accountService.getAccountByCardid(sessacc.getCardID());
        Long balance1 = Long.valueOf(dbacc.getAccountBalance());
        //余额充足逻辑，取款更新数据库
        if (inputmoney <= balance1) {
            //数据库减余额
            dbacc.setAccountBalance(Long.toString((balance1 - inputmoney)));
            if (accountService.updateAccount(dbacc)) {
                //添加账单记录
                Bill bill = new Bill();
                bill.setAffairType("转出");
                bill.setCardID(dbacc.getCardID());
                bill.setTradeBalance("-" + money);
                bill.setTradeTime(DateconversionUtil.dateConversion(new Date(), "yyyy-MM-dd HH:mm:ss"));
                //数据库插入记录
                if (!billService.insertBill(bill)) {
                    jsonObject.put("state", "failed");
                    jsonObject.put("msg", "数据库错误");
                    return jsonObject;
                }
            }
        } else {
            //余额不足逻辑，返回取款失败信息
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "账户余额不足");
            return jsonObject;
        }

        //获取被转账用户卡信息
        Account account = accountService.getAccountByCardid(cardid);
        if (account == null) {
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "用户不存在");
            return jsonObject;
        }
        String accountBalance = account.getAccountBalance();
        if (accountBalance.indexOf('.') >= 0)
            accountBalance = accountBalance.substring(0, accountBalance.indexOf('.'));
        Long balance = Long.valueOf(accountBalance);
        balance += inputmoney;
        //更新被转账用户余额
        account.setAccountBalance(Long.valueOf(balance).toString());
        if (accountService.updateAccount(account)) {
            //添加账单记录
            Bill bill = new Bill();
            bill.setAffairType("转入");
            bill.setCardID(account.getCardID());
            bill.setTradeBalance("+" + money);
            bill.setTradeTime(DateconversionUtil.dateConversion(new Date(), "yyyy-MM-dd HH:mm:ss"));

            if (!billService.insertBill(bill)) {
                jsonObject.put("state", "failed");
                jsonObject.put("msg", "数据库错误");
                return jsonObject;
            }
        }
        jsonObject.put("state", "success");
        jsonObject.put("msg", "转账成功");
        jsonObject.put("address", "/index");
        return jsonObject;
    }
}
