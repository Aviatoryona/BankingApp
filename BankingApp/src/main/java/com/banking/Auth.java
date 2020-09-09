/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banking;

import com.banking.entities.Customers;
import com.banking.logic.CustomerLogic;
import com.banking.models.MessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aviator
 */
@WebServlet(name = "Auth", urlPatterns = {"/auth"})
public class Auth extends HttpServlet {

    @Inject
    CustomerLogic cl;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().removeAttribute(AppEnum.LOGGED_IN_USER.getName());
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("checkmail")) {
            String email = request.getParameter("email");
            MessageModel messageModel = cl.checkEmail(email);
            if (messageModel.isSuccess()) {
                Customers cm = (Customers) messageModel.getObject();
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute(AppEnum.LOGGED_IN_USER.getName(), cm);
            }
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(messageModel)
            );
        }
    }

}
