package com.wangxiaomeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangxiaomeng.dao.AccountInfoDao;
import com.wangxiaomeng.model.AccountInfo;
import com.wangxiaomeng.model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/test")
public class TestServlet extends HttpServlet {
    private AccountInfoDao accountInfoDao;
    @Override
    public void init() throws ServletException {
        accountInfoDao = new AccountInfoDao();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uname");
        AccountInfo accountInfo = accountInfoDao.queryTest(username);
        Result result = Result.success(accountInfo);
        // handle response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(JSONObject.toJSONString(result));
    }
}
