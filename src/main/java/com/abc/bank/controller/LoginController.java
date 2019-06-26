package com.abc.bank.controller;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.Repository.UsersMapper;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Users;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;
import java.util.Random;

//登陆、退出、注册 控制器
@Controller
public class LoginController {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    AccountMapper accountMapper;

    @ResponseBody
    @RequestMapping("/login")
    public JSONObject login(@RequestParam("username") @NotNull String cardid, @RequestParam("password") @NotNull String password, HttpServletRequest request) {
        //构建json对象
        JSONObject result = new JSONObject();

        //合法性判断信息赋值
        String userReg = cardidJudge(cardid, result);
        String passReg = passwordJudge(password, result);


        //判断合法
        if (cardid.matches(userReg) && password.matches(passReg)) {
            //数据库判断用户密码
//            CardInfo cardInfo = cardInfoService.getCardInfo(cardid);
            Account account=accountMapper.selectByPrimaryKey(cardid);
            if (account == null) {
                result.put("state", "failed");
                result.put("msg", "用户名不存在");
                return result;
            }
            if (password.equals(account.getPassword())) {
                //密码正确
                result.put("state", "success");
                result.put("address", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/index");
                //添加session
                HttpSession session = request.getSession();
                session.setAttribute("account", account);
            } else {
                //密码错误
                result.put("state", "failed");
                result.put("msg", "用户名或密码错误");
            }
            return result;
        } else {
            //判断不合法
            result.put("state", "failed");
        }
        return result;
    }

    String passwordJudge(@NotNull @RequestParam("password") String password, JSONObject result) {
        //Password合法判断
        String passReg = "[1-9][0-9]{5}$";
        if (!password.matches(passReg)) {
            result.put("passcontent", "密码不合法");
        }
        return passReg;
    }

    String cardidJudge(@NotNull @RequestParam("username") String username, JSONObject result) {
        //username合法判断
        String userReg = "[1-9][0-9]{15}$";
        if (!username.matches(userReg)) {
            result.put("usercontent", "用户名不合法");
        }
        return userReg;
    }

    //注销
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request) {
        String page = null;
        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            session.removeAttribute("account");
            session.invalidate();
            page = "/login";
            result.put("state", "success");
        } else {
            page = "404";
            result.put("state", "failed");
        }
        return page;
    }

    @Transactional
    @ResponseBody
    @RequestMapping("/signup")
    public JSONObject signUp(@NotNull @RequestParam("id") String cardid,
                             @NotNull @RequestParam("pass") String password,
                             @NotNull @RequestParam("pass1") String password1,
                             @NotNull @RequestParam("name") String username,
                             @NotNull @RequestParam("identify") String identify,
                             @NotNull @RequestParam("phone") String phone,
                             @NotNull @RequestParam("address") String address,
                             @NotNull @RequestParam("type") String cardtype) {

        //构建json对象
        JSONObject jsonObject = new JSONObject();
        //判断密码一致性
        if (!password.equals(password1)){
            jsonObject.put("state","failed");
            jsonObject.put("msg","两次密码不一致");
        }
        //卡号、密码 合法性判断
        String userReg = cardidJudge(cardid, jsonObject);
        String passReg = passwordJudge(password, jsonObject);
        //创建新卡
        Account account=new Account();
        account.setIdentify(identify);
        account.setPassword(password);
        account.setType(cardtype);
        account.setAccountBalance("0");
        account.setCardID(cardid);
        account.setCreditLimit("0");
        account.setEffectiveDate(new Date());
        //查看用户是否已经存在
        Users users=usersMapper.selectByPrimaryKey(identify);
        //已存在
        if (users!=null) {
            //插入数据库
           int i= accountMapper.insert(account);
           if (i>0){
               jsonObject.put("state","success");
               jsonObject.put("msg","新卡注册成功");
           }
           return jsonObject;
        }
        //用户不存在
        //创建新用户
        users=new Users();
        users.setAddress(address);
        users.setIdentify(identify);
        users.setTelNum(phone);
        users.setUsername(username);
        //数据库插入新用户
        int i=usersMapper.insert(users);
        if (i<=0){
            jsonObject.put("state","failed");
            jsonObject.put("msg","创建用户数据库错误");
            return jsonObject;
        }
        //判断卡号是否已存在
        //？？？
        //数据库插入新用户新卡
        int j=accountMapper.insert(account);
        if (j<=0){
            jsonObject.put("state","failed");
            jsonObject.put("msg","创建用户卡数据库错误");
            return jsonObject;
        }
        jsonObject.put("state","success");
        jsonObject.put("msg","创建新卡、新用户成功");

//        CardInfo cardInfo=null;
//
//        //判断合法
//        if (cardInfo.getId().matches(userReg) && cardInfo.getPassword().matches(passReg)) {
//            //判断用户名是否已存在
//            CardInfo cardInfo1 = cardInfoService.getCardInfo(cardInfo.getId());
//            if (cardInfo1!=null){
//                jsonObject.put("state","failed");
//                jsonObject.put("msg","卡号已存在");
//                return jsonObject;
//            }
//            //数据库插入卡信息
//            boolean flag = cardInfoService.insertCardinfo(cardInfo);
//            if (flag) {
//                jsonObject.put("state", "success");
//                jsonObject.put("msg", "注册成功");
//            } else {
//                jsonObject.put("state", "failed");
//                jsonObject.put("msg", "数据库出错了");
//            }
//        }else {
//            jsonObject.put("state", "failed");
//        }
        return jsonObject;
    }

    @RequestMapping("/signup.html")
    public String changePassword(HttpServletRequest request, Map map) {
        String prex="9960001011";
        Random dom=new Random();
        int random=dom.nextInt(999999);
        if (random<100000){
            random+=100000;
        }
        String newcardid=prex+Integer.valueOf(random).toString();
        Account account=accountMapper.selectByPrimaryKey(newcardid);
        while(account!=null){
            random=dom.nextInt(999999);
            if (random<100000){
                random+=100000;
            }
            newcardid=prex+Integer.valueOf(random).toString();
            account=accountMapper.selectByPrimaryKey(newcardid);
        }
        map.put("cardid", newcardid);
        return "/signup";
    }

}
