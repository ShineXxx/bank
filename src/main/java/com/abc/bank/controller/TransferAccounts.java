package com.abc.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//转账控制器
@Controller
public class TransferAccounts {
    @RequestMapping("/transferaccounts")
    public String transferAccounts(){
        return null;
    }
}
