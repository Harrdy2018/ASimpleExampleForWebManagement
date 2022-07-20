package com.wangxiaomeng.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangxiaomeng.dao.UserDAO;
import com.wangxiaomeng.model.Result;
import com.wangxiaomeng.model.ResultCode;
import com.wangxiaomeng.model.User;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2(topic = "runlog")
@WebServlet(value = "/queryAllUser")
public class QueryAllUserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init(){
        userDAO=new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("start QueryAllUserServlet");
        Result result = null;
        if ("GET".equals(request.getMethod())) {
            List<User> users=userDAO.selectAllUser();
            result = Result.success(users);
        } else {
            result = Result.failure(ResultCode.REQUEST_METHOD_INVALID);
        }

        // handle response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // 使用jackson库进行序列化
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(result));
        log.info("end QueryAllUserServlet");
    }
}
