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
import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    ServletContext ctx;
    DbConnection dbConnection;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ctx = getServletContext();
        dbConnection = (DbConnection) ctx.getAttribute("dbConnection");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        App app = App.getInstance(dbConnection);
        Map<String, Object> map = app.registerInit();

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(map));
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
        CustomerModel cm = new CustomerModel();
        cm.setCt_fname(request.getParameter("fname"));
        cm.setCt_lname(request.getParameter("lname"));
        cm.setCt_email(request.getParameter("email"));
        cm.setCt_phone(request.getParameter("phone"));
        cm.setCt_country(request.getParameter("country"));
        cm.setCt_city(request.getParameter("city"));
        cm.setCt_address(request.getParameter("address"));
        cm.setCt_gender(request.getParameter("gender"));
        cm.setCt_accounttype(request.getParameter("acctype"));

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(
                App.getInstance(dbConnection).registerCustomer(cm)
                ? mapper.writeValueAsString(
                        new MessageModel(true, "Registration sucessfull")
                )
                : mapper.writeValueAsString(
                        new MessageModel(false, "Failed, please try again")
                )
        );
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Register";
    }// </editor-fold>

}
