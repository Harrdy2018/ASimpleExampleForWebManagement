package com.wangxiaomeng.dao;

import com.wangxiaomeng.mapper.AccountInfoMapper;
import com.wangxiaomeng.model.AccountInfo;
import com.wangxiaomeng.utils.MybatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountInfoDao {
    public void insertUser(String username, String password, int sex, String email, String introduce){
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        accountInfoMapper.addUser(username, password, sex, email, introduce);
        sqlSession.commit();
        sqlSession.close();
    }

    public AccountInfo queryTest(String username){
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        AccountInfo accountInfo = accountInfoMapper.queryTest(username);
        sqlSession.close();
        return accountInfo;
    }

    public List<AccountInfo> queryTestA(String username){
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        List<AccountInfo> accountInfo = accountInfoMapper.queryTestA(username);
        sqlSession.close();
        return accountInfo;
    }

    public List<AccountInfo> queryTestB(String username){
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        AccountInfo temp = new AccountInfo();
        temp.setUsername(username);
        List<AccountInfo> accountInfo = accountInfoMapper.queryTestB(temp);
        sqlSession.close();
        return accountInfo;
    }

    public List<AccountInfo> queryTestC(String username){
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        List<AccountInfo> accountInfo = accountInfoMapper.queryTestC(map);
        sqlSession.close();
        return accountInfo;
    }

    public List<AccountInfo> queryTestD(String username){
        SqlSession sqlSession = MybatisUtils.getFactory().openSession();
        AccountInfoMapper accountInfoMapper = sqlSession.getMapper(AccountInfoMapper.class);
        List<AccountInfo> accountInfo = accountInfoMapper.queryTestA(username);
        sqlSession.close();
        return accountInfo;
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
