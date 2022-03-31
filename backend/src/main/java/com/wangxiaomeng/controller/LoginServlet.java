package com.wangxiaomeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangxiaomeng.dao.AccountInfoDao;
import com.wangxiaomeng.model.AccountInfo;
import com.wangxiaomeng.model.Result;
import com.wangxiaomeng.model.ResultCode;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/login")
public class LoginServlet extends HttpServlet {
    private AccountInfoDao accountInfoDao;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = null;
        if ("POST".equals(request.getMethod())) {
            if ("application/json".equals(request.getContentType())){
                AccountInfo accountInfo = JSONObject.parseObject(IOUtils.toString(request.getInputStream(), "utf-8"), AccountInfo.class);
                AccountInfo res = accountInfoDao.queryUserByUsernameAndPassword(accountInfo.getUsername(), accountInfo.getPassword());
                if (res == null) {
                    result = Result.failure(ResultCode.USER_NOT_EXIST);
                } else {
                    result = Result.success(res);
                }
            }
        } else {
            result = Result.failure(ResultCode.REQUEST_METHOD_INVALID);
        }

        // handle response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }

    @Override
    public void init() throws ServletException {
       accountInfoDao = new AccountInfoDao();
    }
}
