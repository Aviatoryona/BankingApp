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
import com.banking.models.AccountTypes;
import com.banking.models.CountryModel;
import com.banking.models.CustomerModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aviator
 */
public class App {

    private final DbConnection connection;
    private static App app;

    private App(DbConnection dbConnection) {
        this.connection = dbConnection;
    }

    public static App getInstance(DbConnection dbConnection) {
        if (app == null) {
            app = new App(dbConnection);
        }
        return app;
    }

    /*
    Register init fields
    Loads data to Populate  registration input values like countries and account type
     */
    Map<String, Object> registerInit() {
        Map<String, Object> map = new HashMap<>();
        map.put("acctypes", getAccountTypes());
        map.put("countries", getCountries());
        return map;
    }

    //get countries
    private List<CountryModel> getCountries() {
        try {
            List<CountryModel> countryModels = new ArrayList<>();
            ResultSet rs = connection.executeQuery("SELECT * FROM countries");
            while (rs.next()) {
                countryModels.add(
                        new CountryModel(rs.getInt("ctry_id"), rs.getString("ctry_name"))
                );
            }
            return countryModels;
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //get account types
    private List<AccountTypes> getAccountTypes() {
        try {
            List<AccountTypes> accountTypes = new ArrayList<>();
            ResultSet rs = connection.executeQuery("SELECT * FROM accounttypes");
            while (rs.next()) {
                accountTypes.add(
                        new AccountTypes(rs.getInt("accid"), rs.getString("acctype"))
                );
            }
            return accountTypes;
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //register customer
    boolean registerCustomer(CustomerModel customerModel) {
        try {
            String sql = "INSERT INTO customers(`ct_fname`, `ct_lname`, `ct_email`, `ct_phone`,"
                    + "`ct_address`, `ct_city`, `ct_country`, `ct_gender`, `ct_accounttype`, `ct_accountnumber`,"
                    + " `ct_accesscode`) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = connection.getPreparedStatement(sql);
            ps.setString(1, customerModel.getCt_fname());
            ps.setString(2, customerModel.getCt_lname());
            ps.setString(3, customerModel.getCt_email());
            ps.setString(4, customerModel.getCt_phone());
            ps.setString(5, customerModel.getCt_address());
            ps.setString(6, customerModel.getCt_city());
            ps.setString(7, customerModel.getCt_country());
            ps.setString(8, customerModel.getCt_gender());
            ps.setString(9, customerModel.getCt_accounttype());
            ps.setString(10, getAccountNumber());
            ps.setString(11, getAccessCode(""));
            return connection.execute(ps);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Generate account number
    private String getAccountNumber() {
        Date date = new Date();
        long l = date.getTime();
        String cvv = getCvv();
        return String.valueOf(l).concat(cvv);
    }

    private String getCvv() {
        int x = new Random().nextInt(10000);
        String s = String.valueOf(x);
        if (s.length() < 3) {
            getCvv();
        }
        return s;
    }

    //get access code. A Random generate key like a password
    private final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$*";

    private String getAccessCode(String code) {
        char[] cs = characters.toCharArray();
        code += String.valueOf(cs[new Random().nextInt(cs.length)]);
        return code.length() == 8 ? code : getAccessCode(code);
    }

}
