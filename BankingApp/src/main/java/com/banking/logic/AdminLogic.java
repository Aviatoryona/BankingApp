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
    private EntityManager em;

    @Inject
    private CustomerLogic customerLogic;

    @Inject
    private TranasctionLogic tranasctionLogic;

    @Inject
    private TransactionTypeLogic transactionTypeLogic;

    @Inject
    private UsersLogic usersLogic;

    @Inject
    AccountTypeLogic accountTypeLogic;

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
        return getRegisteredCustomers(-1).size();
    }

    @Override
    public Users getUser(String username, String pwd) {
        return usersLogic.getUser(username, pwd);
    }

    @Override
    public Users getUser(int id) {
        return usersLogic.getUser(id);
    }

    @Override
    public Customers getCustomer(String email) {
        return new CustomerLogic().getCustomer(email);
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
    public List<Customers> getRegisteredCustomers(int limit, Date start, Date end) {
        if (start == null) {
            return getRegisteredCustomers(limit);
        }
        String sql = end == null
                ? "SELECT c FROM Customers c WHERE c.ctDate >= :ctDate"
                : "SELECT c FROM Customers c WHERE c.ctDate >= ?1 AND c.ctDate <= ?2";

        Query q = em.createQuery(sql);
        if (end == null) {
            q.setParameter("ctDate", start);
        } else {
            q.setParameter(1, start);
            q.setParameter(2, end);
        }
        q.setMaxResults(limit);
        return q.getResultList();
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
        return usersLogic.getUsers(-1);
    }

    @Override
    public List<Transactiontypes> getTransactiontypes() {
        return transactionTypeLogic.getTransactiontypeses();
    }

    @Override
    public List<Accounttypes> getAccounttypes() {
        return accountTypeLogic.getAccounttypeses();
    }

    @Override
    public MessageModel addAccountType(Accounttypes accounttypes) {
        return accountTypeLogic.addAccountType(accounttypes);
    }

    @Override
    public MessageModel addTransactionType(Transactiontypes t) {
        return transactionTypeLogic.addTransactiontypes(t);
    }

    @Override
    public MessageModel addUser(Users user) {
        return usersLogic.addUser(user);
    }

    @Override
    public MessageModel reverseTransaction(Transactions t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel updateCustomer(Customers c) {
        return new MessageModel(new CustomerLogic().createCustomer(c), "Done", c);
    }

    @Override
    public MessageModel updateUser(Users u) {
        return addUser(u);
    }

    @Override
    public MessageModel deleteUser(Users u) {
        return usersLogic.removeUser(u);
    }

    @Override
    public MessageModel deleteCustomer(Customers c) {
        em.remove(em.find(Customers.class, c));
        return new MessageModel(true, "Done", c);
    }

    @Override
    public MessageModel deleteTransactionType(Transactiontypes t) {
        return transactionTypeLogic.removeTransactiontypes(t);
    }

    @Override
    public MessageModel deleteAccountType(Accounttypes a) {
        return accountTypeLogic.removeAccountType(a);
    }

}
