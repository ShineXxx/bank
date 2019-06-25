package com.abc.bank.common;

import org.springframework.stereotype.Component;

@Component
public class MoneyUtil {
    public static boolean ismultipleOfhundred(Long money,int integer){
        if (money==null||integer==0)return false;
        if (money%integer==0&&money>0){
            return true;
        }else {
            return false;
        }
    }
}
