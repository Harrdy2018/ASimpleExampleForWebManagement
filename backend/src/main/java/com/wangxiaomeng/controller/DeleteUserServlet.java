package com.wangxiaomeng.controller;


import com.wangxiaomeng.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete")
public class DeleteUserServlet extends HttpServlet{
    private UserDAO userDAO;

    @Override
    public void init(){
        userDAO=new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("execute deleteUser function");
        int id=Integer.parseInt(request.getParameter("id"));

        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
}
