
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
import com.banking.entities.Customers;
import com.banking.interfaces.DashboardLogicI;
import com.banking.models.MessageModel;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Aviator
 */
public class DashboardLogic implements DashboardLogicI {

    CustomerLogic cl = lookupCustomerLogicBean();

    @Override
    public MessageModel processIndexHome(Customers cm) {
        Map<String, Object> map = new HashMap<>();
        map.put(AppEnum.DEPOSIT.getName(), cl.getTotalDeposits(cm));
        map.put(AppEnum.WITHDRAW.getName(), cl.getTotalWithdrawals(cm));
        map.put(AppEnum.TRANSACTIONS.getName(), cl.getAllTransactions(cm, null));
        return new MessageModel(true, "", map);
    }

    private CustomerLogic lookupCustomerLogicBean() {
        try {
            Context c = new InitialContext();
            return (CustomerLogic) c.lookup("java:global/dev.yonathaniel_BankingApp_war_1.0-SNAPSHOT/CustomerLogic!com.banking.logic.CustomerLogic");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
