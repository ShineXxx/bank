package com.abc.bank.common;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

@Component
public class DateconversionUtil {
    public static String dateConversion (Date date,String pattern){//"yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        String string = format.format(date).toString();
        return string;
    }
}
