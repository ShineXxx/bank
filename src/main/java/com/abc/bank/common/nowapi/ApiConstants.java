package com.abc.bank.common.nowapi;

/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
public enum  ApiConstants {
/*
appkey
 */
    APP_KEY("appkey","43463"),
    /*
    sign md5
     */

    SIGN("sign","1b46e2eaf7a635a21d30b35cca46b2e1");

    private String name;
    private String value;

    ApiConstants(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
