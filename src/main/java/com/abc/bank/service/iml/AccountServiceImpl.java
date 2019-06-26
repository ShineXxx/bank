package com.abc.bank.service.iml;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.Repository.BillMapper;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BillMapper billMapper;

    public boolean updateAccount(Account account){
        return accountMapper.updateEntity(account)>0;
    };

    public Account getAccountByCardid(Account account){
        return accountMapper.selectByPrimaryKey(account.getCardID());
    }

    //生成账单
    public boolean createBill(Bill bill){
        return billMapper.insert(bill)>0;
    }

    //获取账单集合
    public List getBills(Bill bill){
        return billMapper.selectByCardid(bill.getCardID());
    }
}
