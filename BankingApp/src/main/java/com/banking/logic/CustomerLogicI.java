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

import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import com.banking.models.TransactionModel;
import com.banking.models.TransactionType;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Aviator
 */
public interface CustomerLogicI {

    boolean createCustomer(CustomerModel customerModel);

    CustomerModel getCustomer(String email);

    CustomerModel getCustomer(ResultSet rs);

    MessageModel checkEmail(String email);

    MessageModel checkPassword(String email, String pwd);

    double getTotalDeposits(CustomerModel cm);

    double getTotalWithdrawals(CustomerModel cm);

    double getBalance(CustomerModel cm);

    List<TransactionModel> getDeposits(CustomerModel cm);

    List<TransactionModel> getWithdrawals(CustomerModel cm);

    List<TransactionModel> getAllTransactions(CustomerModel cm, TransactionType transactionType);

    MessageModel deposit(CustomerModel cm, double amount);

    MessageModel withdraw(CustomerModel cm, double amount);

    MessageModel checkBalance(CustomerModel cm);
}
