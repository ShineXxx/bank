package com.abc.bank.service.User.impl;

import com.abc.bank.registry.User.UserMapper;
import com.abc.bank.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }
}
