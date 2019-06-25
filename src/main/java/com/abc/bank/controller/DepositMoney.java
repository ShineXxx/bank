package com.abc.bank.controller;

import com.abc.bank.common.MoneyUtil;
import com.abc.bank.pojo.CardInfo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//存款控制器
@RestController
public class DepositMoney {

    @RequestMapping("/depositmoney")
    public JSONObject depositMoney(@RequestParam @NotEmpty Long money, HttpServletRequest request) {
        //构建json数据
        JSONObject jsonObject=new JSONObject();
        //用户卡信息
        CardInfo cardInfo= (CardInfo) request.getSession().getAttribute("cardinfo");

        //判断用户是否登陆过
//        if (getCardInfoFromSession(request, jsonObject,cardInfo)) return jsonObject;

        //输入金额合法性
        if (validityOfamount(money, jsonObject)) return jsonObject;

        //数据库更新余额
        return dbOper(money, jsonObject, cardInfo);
    }

    JSONObject dbOper(@NotEmpty @RequestParam Long money, JSONObject jsonObject, CardInfo cardInfo) {
//        String cardid=cardInfo.getId();
        Long balance = 100L;
        //数据库更新余额
        balance += money;
        //更新存款逻辑

        jsonObject.put("state", "success");
        jsonObject.put("msg", "存款成功");
        return jsonObject;
    }

    boolean getCardInfoFromSession(HttpServletRequest request, JSONObject jsonObject,CardInfo cardInfo) {
        if (cardInfo==null){
            jsonObject.put("state","error");
            jsonObject.put("address","http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/404");
            return true;
        }
        return false;
    }

    boolean validityOfamount(@NotNull @RequestParam Long money, JSONObject jsonObject) {
        boolean flag= MoneyUtil.ismultipleOfhundred(money,100);
        if (flag==false){
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "金额格式错误(100整数倍)");
            return true;
        }
        return false;
    }

}
