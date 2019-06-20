package com.abc.bank.registry.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT password FROM `user` where username =#{username}")
    String getPassword(String username);
}
