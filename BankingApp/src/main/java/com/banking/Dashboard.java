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

import com.banking.db.DbConnection;
import com.banking.logic.CustomerLogic;
import com.banking.logic.DashboardLogic;
import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aviator
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/dashboard"})
public class Dashboard extends HttpServlet {

    ServletContext ctx;
    DbConnection dbConnection;
    CustomerModel customerModel;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ctx = getServletContext();
        dbConnection = (DbConnection) ctx.getAttribute("dbConnection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        customerModel = (CustomerModel) request.getSession().getAttribute(AppEnum.LOGGED_IN_USER.getName());
        if (customerModel == null) {
            response.sendRedirect("auth");
            return;
        }
        PrintWriter out = response.getWriter();
        String q = request.getParameter("q");
        if (q != null) {
            switch (q) {
                case "0":
                    MessageModel messageModel = DashboardLogic.getInstance(dbConnection).processIndexHome(customerModel);
                    out.print(new ObjectMapper().writeValueAsString(messageModel));
                    return;
                default:
                    out.print(new ObjectMapper().writeValueAsString(
                            new MessageModel(false, "", request.getParameterMap())
                    ));
                    return;
            }
        }

        response.sendRedirect("dashboard.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("q");
        if (action != null) {
            switch (action) {
                case "w":
                    doWithdraw(request, response);
                    return;
                case "d":
                    doDeposit(request, response);
                    return;
            }
        }
        response.getWriter().print(new ObjectMapper().writeValueAsString(
                new MessageModel(false, "", request.getParameterMap())
        ));
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

    private void doWithdraw(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String amt = request.getParameter("amt");
        if (amt != null) {
            double amount = Double.parseDouble(amt);
            response.getWriter().print(new ObjectMapper().writeValueAsString(
                    CustomerLogic.getInstance(dbConnection).withdraw(customerModel, amount)
            ));
            return;
        }
        response.getWriter().print(new ObjectMapper().writeValueAsString(
                new MessageModel(false, "", request.getParameterMap())
        ));

    }

    private void doDeposit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String amt = request.getParameter("amt");
        if (amt != null) {
            double amount = Double.parseDouble(amt);
            response.getWriter().print(new ObjectMapper().writeValueAsString(
                    CustomerLogic.getInstance(dbConnection).deposit(customerModel, amount)
            ));
            return;
        }
        response.getWriter().print(new ObjectMapper().writeValueAsString(
                new MessageModel(false, "", request.getParameterMap())
        ));
    }

}
