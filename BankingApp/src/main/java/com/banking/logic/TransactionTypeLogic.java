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
import com.banking.models.TransactionType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aviator
 */
public class TransactionTypeLogic implements TransactionTypeLogicI {

    private final DbConnection dbConnection;
    private final String tbName = "transactiontypes";

    private TransactionTypeLogic(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private static TransactionTypeLogic cl;

    public static TransactionTypeLogic getInstance(DbConnection dbConnection) {
        if (cl == null) {
            cl = new TransactionTypeLogic(dbConnection);
        }
        return cl;
    }

    @Override
    public TransactionType getTransactionType(String type) {
        try {
            PreparedStatement ps = dbConnection.getPreparedStatement("SELECT * FROM " + tbName + " WHERE tp_type=?");
            ps.setString(1, type);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                TransactionType transactionType = new TransactionType();
                transactionType.setTp_id(rs.getInt("tp_id"));
                transactionType.setTp_type(rs.getString("tp_type"));
                transactionType.setTp_charge(rs.getDouble("tp_charge"));
                return transactionType;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionTypeLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
