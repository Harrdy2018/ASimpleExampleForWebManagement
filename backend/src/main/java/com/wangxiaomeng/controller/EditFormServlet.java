package com.wangxiaomeng.controller;

import com.wangxiaomeng.dao.UserDAO;
import com.wangxiaomeng.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit")
public class EditFormServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init(){
        userDAO=new UserDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("execute showEditForm function");
        int id=Integer.parseInt(request.getParameter("id"));

        User existingUser=userDAO.selectUser(id);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request,response);
    }
}
