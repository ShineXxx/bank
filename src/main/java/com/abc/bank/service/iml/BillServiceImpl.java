package com.abc.bank.service.iml;

import com.abc.bank.repository.AccountMapper;
import com.abc.bank.repository.BillMapper;
import com.abc.bank.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
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
