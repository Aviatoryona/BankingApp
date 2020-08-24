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
package com.banking.logic;

import com.banking.App;
import com.banking.db.DbConnection;
import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aviator
 */
public class CustomerLogic implements CustomerLogicI {

    private DbConnection dbConnection;

    private CustomerLogic(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private static CustomerLogic cl;

    public static CustomerLogic getInstance(DbConnection dbConnection) {
        if (cl == null) {
            cl = new CustomerLogic(dbConnection);
        }
        return cl;
    }

    @Override
    public boolean createCustomer(CustomerModel customerModel) {
        try {
            String sql = "INSERT INTO customers(`ct_fname`, `ct_lname`, `ct_email`, `ct_phone`,"
                    + "`ct_address`, `ct_city`, `ct_country`, `ct_gender`, `ct_accounttype`, `ct_accountnumber`,"
                    + " `ct_accesscode`) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,md5(?))";

            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, customerModel.getCt_fname());
            ps.setString(2, customerModel.getCt_lname());
            ps.setString(3, customerModel.getCt_email());
            ps.setString(4, customerModel.getCt_phone());
            ps.setString(5, customerModel.getCt_address());
            ps.setString(6, customerModel.getCt_city());
            ps.setString(7, customerModel.getCt_country());
            ps.setString(8, customerModel.getCt_gender());
            ps.setString(9, customerModel.getCt_accounttype());
            ps.setString(10, App.getAccountNumber());
            ps.setString(11, App.getAccessCode(""));
            return dbConnection.execute(ps);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public CustomerModel getCustomer(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel checkEmail(String email) {
        try {
            String sql = "SELECT * FROM customers WHERE ct_email=?";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, email);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                return new MessageModel(true, rs.getString("ct_fname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new MessageModel(false, "Failed");
    }

}
