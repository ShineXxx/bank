package com.abc.bank.pojo;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table account_currency
 */
public class AccountCurrency implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_currency.CardID
     *
     * @mbg.generated
     */
    private String cardID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_currency.AccountBalance
     *
     * @mbg.generated
     */
    private String accountBalance;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_currency.Type
     *
     * @mbg.generated
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_currency
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_currency.CardID
     *
     * @return the value of account_currency.CardID
     *
     * @mbg.generated
     */
    public String getCardID() {
        return cardID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_currency.CardID
     *
     * @param cardID the value for account_currency.CardID
     *
     * @mbg.generated
     */
    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_currency.AccountBalance
     *
     * @return the value of account_currency.AccountBalance
     *
     * @mbg.generated
     */
    public String getAccountBalance() {
        return accountBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_currency.AccountBalance
     *
     * @param accountBalance the value for account_currency.AccountBalance
     *
     * @mbg.generated
     */
    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_currency.Type
     *
     * @return the value of account_currency.Type
     *
     * @mbg.generated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_currency.Type
     *
     * @param type the value for account_currency.Type
     *
     * @mbg.generated
     */
    public void setType(String type) {
        this.type = type;
    }
}