package com.abc.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//修改密码控制器
@RestController
public class ChangePassword {
    @RequestMapping("/changepassword")
    public String changePassword(){
        return null;
    }
}