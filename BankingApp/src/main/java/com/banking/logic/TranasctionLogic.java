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

import com.banking.db.DbConnection;
import com.banking.models.MessageModel;
import com.banking.models.TransactionModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aviator
 */
public class TranasctionLogic implements TranasctionLogicI {

    private final DbConnection dbConnection;
    private final String tbName = "transactions";

    private TranasctionLogic(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private static TranasctionLogic cl;

    public static TranasctionLogic getInstance(DbConnection dbConnection) {
        if (cl == null) {
            cl = new TranasctionLogic(dbConnection);
        }
        return cl;
    }

    @Override
    public MessageModel executeTransaction(TransactionModel model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel createTransaction(TransactionModel model) {
        try {
            String sql = "INSERT INTO " + tbName + " (tr_accountnumber,tr_type,tr_amount,tr_charge) VALUES(?,?,?,?)";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, model.getTr_accountnumber());
            ps.setString(2, model.getTr_type());
            ps.setDouble(3, model.getTr_amount());
            ps.setDouble(4, model.getTr_charge());
            if (dbConnection.execute(ps)) {
                return new MessageModel(true, "Done");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TranasctionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new MessageModel(false, "Failed, please try again");
    }

    @Override
    public List<TransactionModel> getTransactionModels(ResultSet rs) {
        List<TransactionModel> list = new ArrayList<>();
        try {
            while (rs.next()) {
                TransactionModel model = new TransactionModel();
                model.setTr_id(rs.getInt("tr_id"));
                model.setTr_accountnumber(rs.getString("tr_accountnumber"));
                model.setTr_type(rs.getString("tr_type"));
                model.setTr_amount(rs.getDouble("tr_amount"));
                model.setTr_charge(rs.getDouble("tr_charge"));
                model.setTr_date(rs.getString("tr_accountnumber"));

                list.add(model);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TranasctionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<TransactionModel> getTransactionModels() {
        return getTransactionModels("SELECT * FROM " + tbName + " ORDER BY tr_id DESC LIMIT 1000;");
    }

    @Override
    public List<TransactionModel> getTransactionModels(String sql) {
        try {
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ResultSet rs = dbConnection.executeQuery(ps);
            return getTransactionModels(rs);
        } catch (SQLException ex) {
            Logger.getLogger(TranasctionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
