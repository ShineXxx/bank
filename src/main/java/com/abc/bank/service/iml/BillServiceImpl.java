package com.abc.bank.service.iml;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.Repository.BillMapper;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BillMapper billMapper;

    //插入账单
    public boolean insertBill(Bill bill){
        return billMapper.insert(bill)>0;
    }
    //获取所有账单
    public List<Bill> getAllBills(String cardid){
        return billMapper.selectByCardid(cardid);
    }
}
