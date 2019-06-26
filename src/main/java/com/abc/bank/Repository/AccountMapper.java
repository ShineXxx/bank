package com.abc.bank.Repository;

import com.abc.bank.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbg.generated
     */
    int insert(Account record);

    //主键查找卡
    Account selectByPrimaryKey(String cardId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbg.generated
     */
    int insertSelective(Account record);

    //更新账户密码
    int updateEntity(Account account);
}