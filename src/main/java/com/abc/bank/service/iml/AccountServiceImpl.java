package com.abc.bank.service.iml;

import com.abc.bank.common.DateconversionUtil;
import com.abc.bank.pojo.Bill;
import com.abc.bank.pojo.Users;
import com.abc.bank.repository.AccountMapper;
import com.abc.bank.repository.BillMapper;
import com.abc.bank.pojo.Account;
import com.abc.bank.repository.UsersMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Service
public class AccountServiceImpl {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    UsersMapper usersMapper;

    /**
     * 更新用户
     */
    public boolean updateAccount(Account account) {
        return accountMapper.updateEntity(account) > 0;
    }

    ;

    public Account getAccountByCardid(String cardid) {
        return accountMapper.selectByPrimaryKey(cardid);
    }

    /**
     * 创建用户
     */
    public boolean createAccount(Account account) {
        boolean b = accountMapper.insert(account) > 0;
        return b;
    }

    /**
     * 转账业务
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject transferMoney(Float inputmoney, JSONObject jsonObject, Account sessacc, Account account) {
        //判断当前用户余额是否充足
        Account dbacc = accountMapper.selectByPrimaryKey(sessacc.getCardID());
        Float balance1 = Float.valueOf(dbacc.getAccountBalance());
        //余额充足逻辑，取款更新数据库
        if (inputmoney > balance1) {
            //余额不足逻辑，返回取款失败信息
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "账户余额不足");
            return jsonObject;

        }
        //数据库减余额
        dbacc.setAccountBalance(Float.toString((balance1 - inputmoney)));
        if (accountMapper.updateEntity(dbacc) > 0) {
            //添加账单记录
            Bill bill = new Bill();
            bill.setAffairType("转出");
            bill.setCardID(dbacc.getCardID());
            bill.setTradeBalance("-" + inputmoney);
            bill.setTradeTime(DateconversionUtil.dateConversion(new Date(), "yyyy-MM-dd HH:mm:ss"));
            //数据库插入记录
            if (billMapper.insert(bill) < 0) {
                return databaseFailmsg(jsonObject);
            }
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        //更新被转账用户余额
        String accountBalance = account.getAccountBalance();
        Float balance = Float.valueOf(accountBalance);
        balance += inputmoney;
        account.setAccountBalance(Float.valueOf(balance).toString());
        if (accountMapper.updateEntity(account) < 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        //添加账单记录
        Bill bill = new Bill();
        bill.setAffairType("转入");
        bill.setCardID(account.getCardID());
        bill.setTradeBalance("+" + inputmoney);
        bill.setTradeTime(DateconversionUtil.dateConversion(new Date(), "yyyy-MM-dd HH:mm:ss"));

        if (billMapper.insert(bill) < 0) {
            return databaseFailmsg(jsonObject);
        }
        jsonObject.put("state", "success");
        jsonObject.put("msg", "转账成功");
        jsonObject.put("address", "/index");
        return jsonObject;
    }

    private JSONObject databaseFailmsg(JSONObject jsonObject) {
        jsonObject.put("state", "failed");
        jsonObject.put("msg", "数据库错误");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return jsonObject;
    }

    /**
     * 更新余额
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateAccbalance(JSONObject jsonObject, Float dbmoney, Account account, Float argmoney, String currencytype) {
        /*
        余额充足逻辑，取款更新数据库
         */
        if (argmoney <= dbmoney) {
            /*
            数据库减余额
             */
            account.setAccountBalance(Float.toString((dbmoney - argmoney)));
            account.setEffectiveDate(new Timestamp(System.currentTimeMillis()));
            if (accountMapper.updateEntity(account) > 0) {
                /*
                添加账单纪录
                 */
                Bill bill = new Bill();
                bill.setCardID(account.getCardID());
                bill.setAffairType(currencytype);
                bill.setTradeBalance("-" + argmoney.toString());
                bill.setTradeTime(DateconversionUtil.dateConversion(new Date(), "yyyy-mm-dd HH:mm:ss"));
                if (billMapper.insert(bill) > 0) {
                    jsonObject.put("state", "success");
                    jsonObject.put("msg", "-" + argmoney);
                    jsonObject.put("address", "/index");
                }
            } else {
                throw new RuntimeException("数据库错误");
            }
        } else {
            /*
            余额不足逻辑，返回取款失败信息
             */
            jsonObject.put("state", "failed");
            jsonObject.put("msg", "账户余额不足");

        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean createUserinfo(JSONObject jsonObject, Account account, Users newusers, Users dbusers) {
        //用户已存在
        if (dbusers != null) {
            //数据库插入用户的新卡
            if (accountMapper.insert(account) > 0) {
                jsonObject.put("state", "success");
                jsonObject.put("msg", "新卡注册成功");
            }
            return true;
        }
        //用户不存在
        //数据库插入新用户
        if (usersMapper.insert(newusers)<0) {
            throw new RuntimeException("创建新用户失败");
        }
        //判断卡号是否已存在
        //？？？
        //数据库插入新用户的新卡
        if (accountMapper.insert(account)<0) {
            throw new RuntimeException("创建用户卡数据库错误");
        }
        jsonObject.put("state","success");
        jsonObject.put("msg","创建新卡、新用户成功");
        return true;
    }
}
