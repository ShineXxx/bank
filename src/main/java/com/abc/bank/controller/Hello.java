package com.abc.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class Hello {
    @RequestMapping("/hello")
    public String hello(Map map){
        map.put("a",123);
        return "hello";
    }

}
