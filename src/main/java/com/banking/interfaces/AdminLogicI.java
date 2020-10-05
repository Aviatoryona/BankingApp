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

import com.banking.entities.Accounttypes;
import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.entities.Transactiontypes;
import com.banking.entities.Users;
import com.banking.models.MessageModel;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Aviator
 */
@Remote
public interface AdminLogicI {

    int totalTransactions(Date start, Date end);

    int totalCustomers();

    double totalProfit(Date start, Date end);

    int totalCustomers(Date start, Date end);

    Users getUser(String username, String pwd);

    Users getUser(int id);

    Customers getCustomer(String email);

    List<Customers> getRegisteredCustomers(int limit);

    List<Customers> getRegisteredCustomers(int limit, Date start, Date end);

    List<Transactions> getTransactions(int limit);

    List<Transactions> getTransactions(Customers cm);

    List<Transactions> getTransactions(int limit, Date start, Date end);

    List<Users> getUsers();

    List<Transactiontypes> getTransactiontypes();

    List<Accounttypes> getAccounttypes();

    MessageModel addAccountType(Accounttypes accounttypes);

    MessageModel addTransactionType(Transactiontypes t);

    MessageModel addUser(Users user);

    MessageModel reverseTransaction(Transactions t);

    MessageModel updateCustomer(Customers c);

    MessageModel updateUser(Users u);

    MessageModel deleteUser(Users u);

    MessageModel deleteCustomer(Customers c);

    MessageModel deleteTransactionType(Transactiontypes t);

    MessageModel deleteAccountType(Accounttypes a);

    /*

     */
    MessageModel processIndex0();

}
