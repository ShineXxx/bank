package com.abc.bank.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table account
 */
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
public class Account implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.CardID
     *
     * @mbg.generated
     */
    private String cardID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.AccountBalance
     *
     * @mbg.generated
     */
    private String accountBalance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.Identify
     *
     * @mbg.generated
     */
    private String identify;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.Password
     *
     * @mbg.generated
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.Type
     *
     * @mbg.generated
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.CreditLimit
     *
     * @mbg.generated
     */
    private String creditLimit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.effectiveDate
     *
     * @mbg.generated
     */
    private Date effectiveDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.CardID
     *
     * @return the value of account.CardID
     *
     * @mbg.generated
     */
    public String getCardID() {
        return cardID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.CardID
     *
     * @param cardID the value for account.CardID
     *
     * @mbg.generated
     */
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.AccountBalance
     *
     * @return the value of account.AccountBalance
     *
     * @mbg.generated
     */
    public String getAccountBalance() {
        return accountBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.AccountBalance
     *
     * @param accountBalance the value for account.AccountBalance
     *
     * @mbg.generated
     */
    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.Identify
     *
     * @return the value of account.Identify
     *
     * @mbg.generated
     */
    public String getIdentify() {
        return identify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.Identify
     *
     * @param identify the value for account.Identify
     *
     * @mbg.generated
     */
    public void setIdentify(String identify) {
        this.identify = identify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.Password
     *
     * @return the value of account.Password
     *
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.Password
     *
     * @param password the value for account.Password
     *
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.Type
     *
     * @return the value of account.Type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.Type
     *
     * @param type the value for account.Type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.CreditLimit
     *
     * @return the value of account.CreditLimit
     *
     * @mbg.generated
     */
    public String getCreditLimit() {
        return creditLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.CreditLimit
     *
     * @param creditLimit the value for account.CreditLimit
     *
     * @mbg.generated
     */
    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.effectiveDate
     *
     * @return the value of account.effectiveDate
     *
     * @mbg.generated
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.effectiveDate
     *
     * @param effectiveDate the value for account.effectiveDate
     *
     * @mbg.generated
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Account() {
    }

    public Account(String cardID, String accountBalance, String identify, String password, String type, String creditLimit, Date effectiveDate) {
        this.cardID = cardID;
        this.accountBalance = accountBalance;
        this.identify = identify;
        this.password = password;
        this.type = type;
        this.creditLimit = creditLimit;
        this.effectiveDate = effectiveDate;
    }
}