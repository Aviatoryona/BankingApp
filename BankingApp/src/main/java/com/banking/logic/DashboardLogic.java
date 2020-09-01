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

import com.banking.AppEnum;
import com.banking.db.DbConnection;
import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aviator
 */
public class DashboardLogic implements DashboardLogicI {

    private final DbConnection dbConnection;

    private DashboardLogic(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private static DashboardLogic cl;

    public static DashboardLogic getInstance(DbConnection dbConnection) {
        if (cl == null) {
            cl = new DashboardLogic(dbConnection);
        }
        return cl;
    }

    @Override
    public MessageModel processIndex0(CustomerModel cm) {
        Map<String, Object> map = new HashMap<>();
        map.put(AppEnum.DEPOSIT.getName(), CustomerLogic.getInstance(dbConnection).getTotalDeposits(cm));
        map.put(AppEnum.WITHDRAW.getName(), CustomerLogic.getInstance(dbConnection).getTotalWithdrawals(cm));
        map.put(AppEnum.TRANSACTIONS.getName(), CustomerLogic.getInstance(dbConnection).getAllTransactions(cm, null));
        return new MessageModel(true, "", map);
    }

    /*
    Test class
     */
    public static void main(String[] args) {
        try {
            DbConnection dbConnection1 = DbConnection.getInstance();
            CustomerModel cm = CustomerLogic.getInstance(dbConnection1).getCustomer("av@gmail.com");
            MessageModel messageModel = DashboardLogic.getInstance(dbConnection1).processIndex0(cm);
            System.out.println(
                    new ObjectMapper().writeValueAsString(messageModel)
            );
        } catch (SQLException | ClassNotFoundException | JsonProcessingException ex) {
            Logger.getLogger(DashboardLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
