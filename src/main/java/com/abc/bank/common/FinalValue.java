package com.abc.bank.common;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
public enum FinalValue {
    /*
    未登录页面名
     */
    NOT_FIND("404"),
    /*
    session中存储的用户key
     */
    KEY_ACCOUNT("account"),
    /*
    账单类型
     */
    BILL_IN_CATCH("存入"),
    BILL_OUT_CATCH("支出"),
    BILL_IN_TRANS("转入"),
    BILL_OUT_TRANS("转出"),
    BILL_IN_EXANGE("换入"),
    BILL_OUT_EXANGE("换出");

    private String value;
    FinalValue(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
