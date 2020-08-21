/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.banking;

import com.banking.db.DbConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

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
        response.setContentType("text/html;charset=UTF-8");
        try ( var out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("Hello  " + ctx.getAttribute("Name"));
            Map<String, String> map = new HashMap<>();
            map.put("email", "admin@admin.com");
            JSONObject jSONObject = new JSONObject(map);
            out.write(jSONObject.toJSONString());
        }
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
//        processRequest(request, response);
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("checkmail")) {
            String email = request.getParameter("email");
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(App.getInstance(dbConnection).checkEmail(email))
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
