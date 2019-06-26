package com.abc.bank.pojo;

import java.io.Serializable;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table users
 */
public class Users implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.Identify
     *
     * @mbg.generated
     */
    private String identify;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.Username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.TelNum
     *
     * @mbg.generated
     */
    private String telNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.Address
     *
     * @mbg.generated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table users
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.Identify
     *
     * @return the value of users.Identify
     *
     * @mbg.generated
     */
    public String getIdentify() {
        return identify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.Identify
     *
     * @param identify the value for users.Identify
     *
     * @mbg.generated
     */
    public void setIdentify(String identify) {
        this.identify = identify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.Username
     *
     * @return the value of users.Username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.Username
     *
     * @param username the value for users.Username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.TelNum
     *
     * @return the value of users.TelNum
     *
     * @mbg.generated
     */
    public String getTelNum() {
        return telNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.TelNum
     *
     * @param telNum the value for users.TelNum
     *
     * @mbg.generated
     */
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.Address
     *
     * @return the value of users.Address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.Address
     *
     * @param address the value for users.Address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address;
    }
}