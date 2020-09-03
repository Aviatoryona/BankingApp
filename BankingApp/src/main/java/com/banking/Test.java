package com.banking;

import com.banking.db.DbConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

@WebServlet(name = "Test", urlPatterns = {"/test"})
class Test extends HttpServlet {

    ServletContext ctx;

    @Resource(lookup = "java:/MySqlDS")
    private DataSource ds;

    DbConnection dbConnection;

    Connection con;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ctx = getServletContext();
        try ( Connection con1 = ds.getConnection()) {
            con = con1;
            dbConnection = DbConnection.getInstance(con);
        } catch (Exception e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try ( PrintWriter out = resp.getWriter()) {
            if (con == null) {
                out.println("<h1>Connection Error</h1>");
                return;
            }
            out.println("<h1>Hello there</h1>");
            String sql = "SELECT * FROM customers";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                out.write(rs.getString(2));
                out.write("<br/>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        resp.getWriter().println("Hello " + user);
    }
}
