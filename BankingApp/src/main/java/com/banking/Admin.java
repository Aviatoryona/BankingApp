/*
 * Copyright (C) 2020 Aviator
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.banking;

import com.banking.entities.Users;
import com.banking.interfaces.AdminLogicI;
import com.banking.interfaces.AppI;
import com.banking.models.MessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Aviator
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    @Inject
    private Users users;

    @EJB
    AppI appI;

    @EJB
    private AdminLogicI adminLogicI;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        users = (Users) request.getSession().getAttribute(AppEnum.LOGGED_IN_ADMIN.getName());
        if (users == null) {
            request.getRequestDispatcher("admin-login.html")
                    .forward(request, response);
            return;
        }

        String action = request.getParameter("action");
        if (!StringUtils.isEmpty(action)) {
            switch (action) {
                case "-1":
                    request.getSession().removeAttribute(AppEnum.LOGGED_IN_ADMIN.getName());
                    response.sendRedirect("admin-login.html");
                    break;

                case "0":
                    printResult(response, adminLogicI.processIndex0());
                    break;

                case "1":  //get all transactions
                    printResult(response, adminLogicI.getTransactions(1000));
                    break;

                case "2":  //get all customers
                    printResult(response, adminLogicI.getRegisteredCustomers(1000));
                    break;

                case "3":  //get all users
                    printResult(response, adminLogicI.getUsers());
                    break;

                case "4":  //manage
                    Map<String, Object> map = new HashMap<>(appI.registerInit());
                    map.put("trtyps", adminLogicI.getTransactiontypes());
                    printResult(response, adminLogicI.getUsers());
                    break;

            }
            return;
        }

        request.getRequestDispatcher("admin.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String q = request.getParameter("q");
        if (!StringUtils.isEmpty(q)) {
            switch (q) {
                case "auth":
                    doLogin(request, response);
                    break;
            }
        }
    }

    /*

     */
    void printResult(HttpServletResponse response, Object object) throws IOException {
        try {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(object)
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*

     */
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usr = request.getParameter("usr");
        String pwd = request.getParameter("pwd");
        users = adminLogicI.getUser(usr, pwd);
        if (users != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(AppEnum.LOGGED_IN_ADMIN.getName(), users);
            printResult(response, new MessageModel(true, "", users));
        } else {
            printResult(response, new MessageModel(false, "Incorrect credentials"));
        }
    }
}
