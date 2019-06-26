package com.abc.bank.common;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberUtil {
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
