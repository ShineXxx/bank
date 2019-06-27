package com.abc.bank.service.iml;

import com.abc.bank.repository.AccountMapper;
import com.abc.bank.repository.BillMapper;
import com.abc.bank.repository.UsersMapper;
import com.abc.bank.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
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
