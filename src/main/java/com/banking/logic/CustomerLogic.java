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
import com.banking.annotations.SaveToDb;
import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.entities.Transactiontypes;
import com.banking.interfaces.AppI;
import com.banking.interfaces.CustomerLogicI;
import com.banking.interfaces.TranasctionLogicI;
import com.banking.interfaces.TransactionTypeLogicI;
import com.banking.models.MessageModel;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aviator
 */
@Stateless
@Remote(CustomerLogicI.class)
public class CustomerLogic implements CustomerLogicI {

    @PersistenceContext
    private EntityManager em;

    @EJB
    @SaveToDb(description = "call AppI to get AccountNumber and accesscode")
    private AppI appI;

    @EJB
    private TransactionTypeLogicI transactionTypeLogicI;

    @EJB
    private TranasctionLogicI tranasctionLogicI;

    @Override
    public boolean createCustomer(Customers customerModel) {
        customerModel.setCtAccountnumber(appI.getAccountNumber());
        customerModel.setCtAccesscode(appI.getAccessCode(""));
        customerModel.getClientUserSd().setCtDate(Calendar.getInstance().getTime());
        em.merge(customerModel);
        return true;
    }

    @Override
    public Customers getCustomer(String email) {
//        String sql = "SELECT * FROM customers WHERE ct_email=?";
//        return em.find(Customers.class, email);
        try {
            return (Customers) em.createNamedQuery("Customers.findByCtEmail")
                    .setParameter("ctEmail", email)
                    .getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public MessageModel checkEmail(String email) {
        String accessCode = appI.getAccessCode("");
        Customers customers = getCustomer(email);
        if (customers != null) {
            customers.setCtAccesscode(accessCode);
            em.merge(customers);
            return new MessageModel(true, customers.getClientUserSd().getCtFname(), customers);
        }

        return new MessageModel(false, "Account not registered");
    }

    @Override
    public MessageModel checkPassword(String email, String pwd) {
        Customers cm = (Customers) em.createQuery("SELECT c FROM Customers c WHERE c.clientUserSd.ctEmail = :ctEmail AND c.ctAccesscode = :ctAccesscode")
                .setParameter("ctEmail", email)
                .setParameter("ctAccesscode", pwd)
                .getSingleResult();
        if (cm != null) {
            return new MessageModel(true, cm.getClientUserSd().getCtFname(), cm);
        }

        return new MessageModel(false, "Failed");
    }

    @Override
    public double getTotalDeposits(Customers cm) {
        try {
            List<Transactions> models = getWithdrawals(cm);
            if (models != null) {
                Query q = em.createQuery("SELECT SUM(t.trAmount) FROM Transactions t WHERE t.trAccountnumber=:trAccountnumber AND t.trType=:trType");
                q.setParameter("trAccountnumber", cm.getCtAccountnumber());
                q.setParameter("trType", AppEnum.DEPOSIT.getName());

                return (Double) q.getSingleResult();
//            double sum = 9000;
//            sum = models.stream().map(model -> Double.longBitsToDouble(model.getTrAmount())).reduce(sum, (accumulator, _item) -> accumulator + _item);
//            return sum;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public double getTotalWithdrawals(Customers cm) {
        try {
            List<Transactions> models = getWithdrawals(cm);
            if (models != null) {
                Query q = em.createQuery("SELECT SUM(t.trAmount) FROM Transactions t WHERE t.trAccountnumber=:trAccountnumber AND t.trType=:trType");
                q.setParameter("trAccountnumber", cm.getCtAccountnumber());
                q.setParameter("trType", AppEnum.WITHDRAW.getName());

                return (Double) q.getSingleResult();
//            double sum = 9000;
//            sum = models.stream().map(model -> Double.longBitsToDouble(model.getTrAmount())).reduce(sum, (accumulator, _item) -> accumulator + _item);
//            return sum;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public double getBalance(Customers cm) {
        Customers cm1 = getCustomer(cm.getClientUserSd().getCtEmail());
        if (cm1 != null) {
            return cm1.getCtAccbalance();
        }
        return 0;
    }

    @Override
    public List<Transactions> getDeposits(Customers cm) {
        Transactiontypes transactionType = transactionTypeLogicI.getTransactionType(AppEnum.DEPOSIT.getName());
        if (transactionType != null) {
            return getAllTransactions(cm, transactionType);
        }
        return null;
    }

    @Override
    public List<Transactions> getWithdrawals(Customers cm) {
        Transactiontypes transactionType = transactionTypeLogicI.getTransactionType(AppEnum.WITHDRAW.getName());
        if (transactionType != null) {
            return getAllTransactions(cm, transactionType);
        }
        return null;
    }

    @Override
    public List<Transactions> getAllTransactions(Customers cm, Transactiontypes transactionType) {
        String sql = transactionType != null
                ? "SELECT t FROM Transactions t WHERE t.trAccountnumber = :trAccountnumber AND t.trType= :trType ORDER BY t.trId DESC"
                : "SELECT t FROM Transactions t WHERE t.trAccountnumber = :trAccountnumber ORDER BY t.trId DESC";
        Query q = em.createQuery(sql);
        q.setParameter("trAccountnumber", cm.getCtAccountnumber());
        if (transactionType != null) {
            q.setParameter("trType", transactionType.getTpType());
        }
        q.setMaxResults(300);
        return q.getResultList();//tranasctionLogicI.getTransactions(q);
    }

    @Override
    public MessageModel deposit(Customers cm, double amount) {
        if (Math.abs(amount) > 300000 || Math.abs(amount) < 1000) {
            return new MessageModel(false, "Invalid amount");
        }
        Customers cm1 = getCustomer(cm.getClientUserSd().getCtEmail());
        if (cm1 == null) {
            return new MessageModel(false, "Account verification failed");
        }
        Transactions transactions = new Transactions();
        transactions.setTrAccountnumber(cm.getCtAccountnumber());
        transactions.setTrAmount(amount);
        Transactiontypes transactionType = transactionTypeLogicI.getTransactionType(AppEnum.DEPOSIT.getName());
        if (transactionType != null) {
            double newAmount = (cm1.getCtAccbalance() + Math.abs(amount)) - transactionType.getTpCharge();
            MessageModel messageModel = updateAccountBal(newAmount, cm);
            if (messageModel.isSuccess()) {
                transactions.setTrCharge(transactionType.getTpCharge());
                transactions.setTrType(transactionType.getTpType());
                MessageModel messageModel1 = tranasctionLogicI.createTransaction(transactions);
                Map<String, Object> map = new HashMap<>();
                map.put("account", messageModel.getObject());
                map.put("transaction", messageModel1.getObject());
                return new MessageModel(true, "Successfully deposited " + amount, map);
            }
            return new MessageModel(false, "Failed to complete transaction");
        }
        return new MessageModel(false, "Undefined transaction type");
    }

    @Override
    public MessageModel withdraw(Customers cm, double amount) {
        if (Math.abs(amount) > 300000 || Math.abs(amount) < 1000) {
            return new MessageModel(false, "Invalid amount");
        }
        Customers cm1 = getCustomer(cm.getClientUserSd().getCtEmail());
        if (cm1 == null) {
            return new MessageModel(false, "Account verification failed");
        }
        Transactions transactions = new Transactions();
        transactions.setTrAccountnumber(cm.getCtAccountnumber());
        transactions.setTrAmount(amount);
        Transactiontypes transactionType = transactionTypeLogicI.getTransactionType(AppEnum.WITHDRAW.getName());
        if (transactionType != null) {
            if (cm1.getCtAccbalance() < (amount + transactions.getTrCharge())) {
                return new MessageModel(false, "Insufficient balance");
            }

            double newAmount = cm1.getCtAccbalance() - (Math.abs(amount) + transactionType.getTpCharge());
            MessageModel messageModel = updateAccountBal(newAmount, cm);
            if (messageModel.isSuccess()) {
                transactions.setTrCharge(transactionType.getTpCharge());
                transactions.setTrType(transactionType.getTpType());
                MessageModel messageModel1 = tranasctionLogicI.createTransaction(transactions);
                Map<String, Object> map = new HashMap<>();
                map.put("account", messageModel.getObject());
                map.put("transaction", messageModel1.getObject());
                return new MessageModel(true, "Successfully withdrawn " + amount, map);
            }
            return new MessageModel(false, "Failed to complete transaction");
        }
        return new MessageModel(false, "Undefined transaction type");
    }

    @Override
    public MessageModel checkBalance(Customers cm) {
        Customers cm1 = getCustomer(cm.getClientUserSd().getCtEmail());
        if (cm1 == null) {
            return new MessageModel(false, "Account verification failed");
        }
        Transactions transactions = new Transactions();
        transactions.setTrAccountnumber(cm.getCtAccountnumber());
        transactions.setTrAmount(0);
        Transactiontypes transactionType = transactionTypeLogicI.getTransactionType(AppEnum.BALANCE.getName());
        if (transactionType != null) {
            transactions.setTrCharge(transactionType.getTpCharge());
            transactions.setTrType(transactionType.getTpType());
            MessageModel messageModel = tranasctionLogicI.createTransaction(transactions);
            messageModel.setObject(cm1);
            return messageModel;
        }
        return new MessageModel(false, "Undefined transaction type");
    }

    @Override
    public MessageModel updateAccountBal(double newBal, Customers cm) {
        //"UPDATE ".concat(tbName) + " SET ct_accbalance=? WHERE ct_accountnumber=?";
        cm.setCtAccbalance(newBal);
        em.merge(cm);
        return new MessageModel(true, "success", getCustomer(cm.getClientUserSd().getCtEmail()));
    }

    @Override
    public List<Customers> search(String q) {
//        Query query = em.createQuery("SELECT c FROM Customers c WHERE (c.clientUserSd.ctEmail LIKE '%" + q + "%') OR (c.clientUserSd.ctFname LIKE '%" + q + "%') OR (c.clientUserSd.ctLname LIKE '%" + q + "%') OR (c.ctAccountnumber LIKE '%" + q + "%') OR (c.ctCountry LIKE '%" + q + "%') OR (c.ctCity LIKE %" + q + "%) OR (c.ctAccounttype LIKE '%" + q + "%')  ORDER BY c.ctId DESC");
        Query query = em.createQuery("SELECT c FROM Customers c WHERE (c.clientUserSd.ctEmail LIKE '%:q%') OR (c.clientUserSd.ctFname LIKE '%:q%') OR (c.clientUserSd.ctLname LIKE '%:q%') OR (c.ctAccountnumber LIKE '%:q%') OR (c.ctCountry LIKE '%:q%') OR (c.ctCity LIKE '%:q%') OR (c.ctAccounttype LIKE '%:q%')  ORDER BY c.ctId DESC");
//        Query query = em.createQuery("SELECT c FROM Customers c  ORDER BY c.ctId DESC");
        query.setParameter("q", q);
        return query.getResultList();
    }

}
