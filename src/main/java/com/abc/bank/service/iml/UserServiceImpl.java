package com.abc.bank.service.iml;

import com.abc.bank.Repository.AccountMapper;
import com.abc.bank.Repository.BillMapper;
import com.abc.bank.Repository.UsersMapper;
import com.abc.bank.pojo.Account;
import com.abc.bank.pojo.Bill;
import com.abc.bank.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BillMapper billMapper;

    //根据id获取用户信息
    public Users getUserByIdentify(String identify){
        return usersMapper.selectByPrimaryKey(identify);
    }

    //插入用户
    public boolean insertUser(Users users){
        return usersMapper.insert(users)>0;
    }

}
