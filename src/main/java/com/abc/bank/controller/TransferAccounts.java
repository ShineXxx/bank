package com.abc.bank.controller;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.pojo.Account;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;

//转账控制器
@Controller
public class TransferAccounts {
    @Autowired
    AccountMapper accountMapper;

    @Transactional
    @ResponseBody
    @RequestMapping("/transferaccounts")
    public JSONObject transferAccounts(@RequestParam("kahao") @NotEmpty String cardid,
                                       @RequestParam("jine") @NotEmpty String money,
                                       HttpServletRequest request) {
        JSONObject jsonObject=new JSONObject();
        Account account1 = (Account) request.getSession().getAttribute("account");
        //money合法性
        if (Long.valueOf(money)<=0){
            jsonObject.put("state","failed");
            jsonObject.put("msg","转账金额格式有误");
            return jsonObject;
        }
        //判断当前用户余额是否充足
        Long balance1=Long.valueOf(accountMapper.selectByPrimaryKey(account1.getCardID()).getAccountBalance());
        //余额充足逻辑，取款更新数据库
        if (Long.valueOf(money)<=balance1){
            //数据库减余额
            account1.setAccountBalance(Long.toString((balance1-Long.valueOf(money))));
            if (accountMapper.updateEntity(account1)<0){
                jsonObject.put("state","failed");
                jsonObject.put("msg","数据库错误");
                return jsonObject;
            }
        }else
        {
            //余额不足逻辑，返回取款失败信息
            jsonObject.put("state","failed");
            jsonObject.put("msg","账户余额不足");
            return jsonObject;
        }

        //获取被转账用户卡信息
        Account account = accountMapper.selectByPrimaryKey(cardid);
        if (account==null){
            jsonObject.put("state","failed");
            jsonObject.put("msg","用户不存在");
            return jsonObject;
        }
        String accountBalance = account.getAccountBalance();
        if (accountBalance.indexOf('.')>=0)
        accountBalance=accountBalance.substring(0,accountBalance.indexOf('.'));
        Long balance = Long.valueOf(accountBalance);
        balance+=Long.valueOf(money);
        //更新被转账用户余额
        account.setAccountBalance(Long.valueOf(balance).toString());
        if (accountMapper.updateEntity(account)<0){
            jsonObject.put("state","failed");
            jsonObject.put("msg","数据库错误");
            return jsonObject;
        }
        jsonObject.put("state","success");
        jsonObject.put("msg","转账成功");
        jsonObject.put("address","/index");
        return jsonObject;
    }
}
