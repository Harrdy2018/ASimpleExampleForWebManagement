package com.wangxiaomeng.mapper;

import com.wangxiaomeng.model.AccountInfo;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoMapper {
    public AccountInfo queryUserById(int id);
    public void addUser(@Param("username") String username,
                        @Param("password") String password,
                        @Param("sex") int sex,
                        @Param("email") String email,
                        @Param("introduce") String introduce);
}
