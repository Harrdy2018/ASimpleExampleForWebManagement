package com.wangxiaomeng.dao;

import com.wangxiaomeng.mapper.AccountInfoMapper;
import com.wangxiaomeng.model.AccountInfo;
import com.wangxiaomeng.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

public class AccountInfoDao {
    public void insertUser(String username, String password, int sex, String email, String introduce){
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        accountInfoMapper.addUser(username, password, sex, email, introduce);
        sqlSession.commit();
        sqlSession.close();
    }

    public AccountInfo queryUserById(int id) {
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        AccountInfo accountInfo = accountInfoMapper.queryUserById(id);
        sqlSession.close();
        return accountInfo;
    }

    public AccountInfo queryUserByUsernameAndPassword(String username, String password) {
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        AccountInfo accountInfo = accountInfoMapper.queryUserByUsernameAndPassword(username, password);
        sqlSession.close();
        return accountInfo;
    }
}
