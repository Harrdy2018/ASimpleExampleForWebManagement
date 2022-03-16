package com.wangxiaomeng.controller;

import com.wangxiaomeng.dao.UserDAO;
import com.wangxiaomeng.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/insert")
public class InsertUserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init(){
        userDAO=new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("execute insertUser function");
        request.setCharacterEncoding("UTF-8");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String country=request.getParameter("country");

        userDAO.insertUser(new User(name,email,country));
        response.sendRedirect("list");
    }
}
