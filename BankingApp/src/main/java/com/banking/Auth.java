/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banking;

import com.banking.annotations.SaveToDb;
import com.banking.db.DbConnection;
import com.banking.logic.CustomerLogic;
import com.banking.interfaces.TranasctionLogicI;
import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletContext;
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

    ServletContext ctx;
    DbConnection dbConnection;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ctx = getServletContext();
        dbConnection = (DbConnection) ctx.getAttribute("dbConnection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        if (request.getParameterMap().containsKey("q")) {
//            String action = request.getParameter("q");
//            if (action.equalsIgnoreCase("logout")) {
//                Cookie[] cookies = request.getCookies();
//                for (Cookie cooky : cookies) {
//                    if (cooky.getName().equalsIgnoreCase(AppEnum.LOGGED_IN_USER.getName())) {
//                        cooky.setMaxAge(0);
//                        response.addCookie(cooky);
//                    }
//                }
//            }
//        }
        request.getSession().removeAttribute(AppEnum.LOGGED_IN_USER.getName());
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("checkmail")) {
            String email = request.getParameter("email");
            MessageModel messageModel = CustomerLogic.getInstance(dbConnection).checkEmail(email);
            if (messageModel.isSuccess()) {
                CustomerModel cm = (CustomerModel) messageModel.getObject();
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute(AppEnum.LOGGED_IN_USER.getName(), cm);
//                Cookie cookie = new Cookie(
//                        AppEnum.LOGGED_IN_USER.getName(),
//                        new ObjectMapper().writeValueAsString(cm)
//                );
//                cookie.setSecure(true);
//                cookie.setMaxAge(24 * 60 * 60);
//                response.addCookie(cookie);
            }
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(messageModel)
            );
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
