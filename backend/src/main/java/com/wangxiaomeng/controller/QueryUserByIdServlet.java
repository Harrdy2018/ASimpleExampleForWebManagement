package com.wangxiaomeng.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangxiaomeng.dao.UserDAO;
import com.wangxiaomeng.model.Result;
import com.wangxiaomeng.model.User;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/queryUserById")
public class QueryUserByIdServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init(){
        userDAO=new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("execute ListUserServlet");
        User user = null;
        if ("application/json".equals(request.getContentType())){
            User temp= JSONObject.parseObject(IOUtils.toString(request.getInputStream(), "utf-8"), User.class);
            user = userDAO.selectUser(temp.getId());
        }

        // handle response
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Result result = Result.success(user);
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
