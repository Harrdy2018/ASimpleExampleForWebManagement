package com.wangxiaomeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangxiaomeng.dao.UserDAO;
import com.wangxiaomeng.model.Result;
import com.wangxiaomeng.model.ResultCode;
import com.wangxiaomeng.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/queryAllUser")
public class QueryAllUserServlet extends HttpServlet {
    private static Logger logger = (Logger) LogManager.getLogger(QueryAllUserServlet.class.getName());

    private UserDAO userDAO;

    @Override
    public void init(){
        userDAO=new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("start QueryAllUserServlet");
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
        response.getWriter().write(JSONObject.toJSONString(result));
        logger.info("end QueryAllUserServlet");
    }
}
