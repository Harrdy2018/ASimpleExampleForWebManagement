package com.wangxiaomeng.controller;

import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.wangxiaomeng.dao.AccountInfoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/insertAccountUser")
public class InsertAccountUserServlet extends HttpServlet {
    private AccountInfoDao accountInfoDao;

    @Override
    public void init() throws ServletException {
        accountInfoDao = new AccountInfoDao();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/insertAccountUser");
        accountInfoDao.insertUser("lukang", "qw12134",
                0, "10232323@qq.com", "我是一个老实人");
    }
}
