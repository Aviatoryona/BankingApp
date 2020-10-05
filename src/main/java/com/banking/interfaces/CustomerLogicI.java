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
package com.banking.interfaces;

import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.entities.Transactiontypes;
import com.banking.models.MessageModel;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Aviator
 */
@Remote
public interface CustomerLogicI {

    boolean createCustomer(Customers customers);

    Customers getCustomer(String email);

    MessageModel checkEmail(String email);

    MessageModel checkPassword(String email, String pwd);

    double getTotalDeposits(Customers cm);

    double getTotalWithdrawals(Customers cm);

    double getBalance(Customers cm);

    List<Transactions> getDeposits(Customers cm);

    List<Transactions> getWithdrawals(Customers cm);

    List<Transactions> getAllTransactions(Customers cm, Transactiontypes transactionType);

    MessageModel deposit(Customers cm, double amount);

    MessageModel withdraw(Customers cm, double amount);

    MessageModel checkBalance(Customers cm);

    MessageModel updateAccountBal(double newBal, Customers cm);
}
