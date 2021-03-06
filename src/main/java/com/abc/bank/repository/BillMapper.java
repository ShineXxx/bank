package com.abc.bank.repository;

import com.abc.bank.pojo.Bill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @Author 982933616
 * @create 2019/6/27 9:02
 */
@Mapper
public interface BillMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated
     */
    /**
     * 插入新账单
     *
     * @param record
     * @return
     */
    int insert(Bill record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bill
     *
     * @mbg.generated
     */
    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(Bill record);

    /**
     * 获取账单集合
     * @param cardid
     * @return
     */
    List selectByCardid(String cardid);
}