package com.abc.bank.common;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Component
public class DateconversionUtil {
    /*
    日期转换工具类
     */
    public static String dateConversion (Date date,String pattern){//"yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        String string = format.format(date).toString();
        return string;
    }
}
