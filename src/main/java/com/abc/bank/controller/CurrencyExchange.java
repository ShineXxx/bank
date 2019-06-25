package com.abc.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//货币兑换控制器
@Controller
public class CurrencyExchange {
    @RequestMapping("/currencyexchange")
    public String currencyExchange(){
        return null;
    }
}
