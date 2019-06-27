package com.abc.bank.common;

import org.springframework.stereotype.Component;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Component
public class MoneyUtil {
    /*
    验证金额是否为输入参数的倍数
     */
    public static boolean ismultipleOfhundred(Float money,int integer){
        if (money==null||integer==0) {
                return false;
        }
        if (money%integer==0&&money>0){
            return true;
        }else {
            return false;
        }
    }
}
