package com.wangxiaomeng.mapper;

import com.wangxiaomeng.model.AccountInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AccountInfoMapper {
    public AccountInfo queryUserById(int id);

    public AccountInfo queryUserByUsernameAndPassword(@Param("username") String username,
                                                      @Param("password") String password);

    public void addUser(@Param("username") String username,
                        @Param("password") String password,
                        @Param("sex") int sex,
                        @Param("email") String email,
                        @Param("introduce") String introduce);

    public List<AccountInfo> queryTestA(@Param("uname") String username);
    public List<AccountInfo> queryTestB(AccountInfo accountInfo);
    public List<AccountInfo> queryTestC(Map map);
    // 结果映射
    public List<AccountInfo> queryTestD(@Param("uname") String username);


    public AccountInfo queryTest(@Param("username_t") String username);
}
