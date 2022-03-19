package com.wangxiaomeng.controller;


import com.alibaba.fastjson.JSONObject;
import com.wangxiaomeng.dao.UserDAO;
import com.wangxiaomeng.model.Result;
import com.wangxiaomeng.model.ResultCode;
import com.wangxiaomeng.model.User;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteUserById")
public class DeleteUserByIdServlet extends HttpServlet{
    private static Logger logger = (Logger) LogManager.getLogger(DeleteUserByIdServlet.class.getName());

    private UserDAO userDAO;

    @Override
    public void init(){
        userDAO=new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("start DeleteUserByIdServlet");
        Result result = null;
        if ("POST".equals(request.getMethod())) {
            if ("application/json".equals(request.getContentType())){
                User user= JSONObject.parseObject(IOUtils.toString(request.getInputStream(), "utf-8"), User.class);
                userDAO.deleteUser(user.getId());
                result = Result.success();
            }
        } else {
            result = Result.failure(ResultCode.REQUEST_METHOD_INVALID);
        }

        // handle response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSONObject.toJSONString(result));
        logger.info("end DeleteUserByIdServlet");
    }
}
