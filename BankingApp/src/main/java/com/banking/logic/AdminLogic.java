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

import com.banking.entities.Accounttypes;
import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.entities.Transactiontypes;
import com.banking.entities.Users;
import com.banking.interfaces.AdminLogicI;
import com.banking.models.MessageModel;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aviator
 */
@Stateless
@Remote
public class AdminLogic implements AdminLogicI {

    @PersistenceContext
    EntityManager em;

    @Inject
    CustomerLogic customerLogic;

    @Inject
    TranasctionLogic tranasctionLogic;

    @Inject
    TransactionTypeLogic transactionTypeLogic;

    @Override
    public int totalTransactions(Date start, Date end) {
        return getTransactions(-1, start, end).size();
    }

    @Override
    public int totalCustomers() {
        return getRegisteredCustomers(-1).size();
    }

    @Override
    public long totalProfit(Date start, Date end) {
        List<Transactions> list = getTransactions(-1);
        double sum = 0;
        for (Transactions transactions : list) {
            sum += transactions.getTrCharge();
        }
        return Double.doubleToLongBits(sum);
    }

    @Override
    public int totalCustomers(Date start, Date end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Users getUser(String username, String pwd) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Users getUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customers getCustomer(String email) {
        return customerLogic.getCustomer(email);
    }

    @Override
    public List<Customers> getRegisteredCustomers(int limit) {
        Query q = em.createNamedQuery("Customers.findAllOrderById");
        if (limit != -1) {
            q.setMaxResults(limit);
        }
        return q.getResultList();
    }

    @Override
    public List<Customers> getRegisteredCustomers(int limit, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transactions> getTransactions(int limit) {
        return limit == -1
                ? tranasctionLogic.getTransactions()
                : tranasctionLogic.getTransactions(limit);
    }

    @Override
    public List<Transactions> getTransactions(Customers cm) {
        return tranasctionLogic.getTransactions(cm);
    }

    @Override
    public List<Transactions> getTransactions(int limit, Date start, Date end) {
        String sql = start == null
                ? "SELECT t FROM Transactions t"
                : end == null
                        ? "SELECT t FROM Transactions t WHERE t.trDate >= :trDate"
                        : "SELECT t FROM Transactions t WHERE t.trDate >= ?1 AND t.trDate <= ?2";

        Query q = em.createQuery(sql);
        if (end == null) {
            q.setParameter("trDate", start);
        } else {
            q.setParameter(1, start);
            q.setParameter(2, end);
        }
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    public List<Users> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transactiontypes> getTransactiontypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Accounttypes> getAccounttypes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel addAccountType(Accounttypes accounttypes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel addTransactionType(Transactiontypes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel addUser(Users user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel reverseTransaction(Transactions t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel updateCustomer(Customers c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel updateUser(Users u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel deleteUser(Users u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel deleteCustomer(Customers c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel deleteTransactionType(Transactiontypes t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel deleteAccountType(Accounttypes a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
