package com.wangxiaomeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangxiaomeng.dao.AccountInfoDao;
import com.wangxiaomeng.model.AccountInfo;
import com.wangxiaomeng.model.Result;
import com.wangxiaomeng.model.ResultCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/queryAccountUserById")
public class QueryAccountUserById extends HttpServlet{
    private AccountInfoDao accountInfoDao = null;

    @Override
    public void init() throws ServletException {
        accountInfoDao = new AccountInfoDao();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = null;
        if ("GET".equals(request.getMethod())) {
            AccountInfo accountInfo = accountInfoDao.queryUserById(Integer.parseInt(request.getParameter("id")));
            result = Result.success(accountInfo);
        } else {
            result = Result.failure(ResultCode.REQUEST_METHOD_INVALID);
        }

        // handle response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
