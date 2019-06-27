package com.abc.bank.common;

import org.springframework.stereotype.Component;

import java.util.Random;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Component
public class RandomNumberUtil {
    /*
    随机数生成类
     */
    public static String generationNumber (String prex,int integer){
        Random dom=new Random();
        int random=dom.nextInt(integer);
        int limit=(integer+1)/10;
        if (random<limit){
            random+=limit;
        }
        String newcardid=prex+Integer.valueOf(random).toString();
        return newcardid;
    }
}
