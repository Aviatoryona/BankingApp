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
import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        App app = App.getInstance(dbConnection);
        Map<String, Object> map = app.registerInit();

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CustomerModel cm = new CustomerModel();
//        cm.setCt_fname(request.getParameter("fname"));
//        cm.setCt_lname(request.getParameter("lname"));
//        cm.setCt_email(request.getParameter("email"));
//        cm.setCt_phone(request.getParameter("phone"));
//        cm.setCt_country(request.getParameter("country"));
//        cm.setCt_city(request.getParameter("city"));
//        cm.setCt_address(request.getParameter("address"));
//        cm.setCt_gender(request.getParameter("gender"));
//        cm.setCt_accounttype(request.getParameter("acctype"));

            BeanUtils.populate(cm, request.getParameterMap());

            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(
                    CustomerLogic.getInstance(dbConnection).createCustomer(cm)
                    ? mapper.writeValueAsString(
                            new MessageModel(true, "Registration sucessfull")
                    )
                    : mapper.writeValueAsString(
                            new MessageModel(false, "Failed, please try again")
                    )
            );
            return;
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.getWriter().write(
                new ObjectMapper()
                        .writeValueAsString(
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
