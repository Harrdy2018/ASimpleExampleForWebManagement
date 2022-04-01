package com.wangxiaomeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import com.wangxiaomeng.dao.AccountInfoDao;
import com.wangxiaomeng.model.AccountInfo;
import com.wangxiaomeng.model.Result;
import com.wangxiaomeng.model.ResultCode;
import com.wangxiaomeng.model.User;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/register")
public class RegisterServlet extends HttpServlet {
    private AccountInfoDao accountInfoDao;

    @Override
    public void init() throws ServletException {
        accountInfoDao = new AccountInfoDao();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("/user/register");
        Result result = null;
        if ("POST".equals(request.getMethod())) {
            if ("application/json".equals(request.getContentType())) {
                AccountInfo accountInfo = JSONObject.parseObject(IOUtils.toString(request.getInputStream(), "utf-8"), AccountInfo.class);
                String username = accountInfo.getUsername();
                String password = accountInfo.getPassword();
                int sex = accountInfo.getSex();
                String email = accountInfo.getEmail();
                String introduce = accountInfo.getIntroduce();
                accountInfoDao.insertUser(username, password, sex, email, introduce);
                AccountInfo res = accountInfoDao.queryUserByUsernameAndPassword(accountInfo.getUsername(), accountInfo.getPassword());
                result = Result.success(res);
            }
        } else {
            result = Result.failure(ResultCode.REQUEST_METHOD_INVALID);
        }

        // handle response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
