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

import com.banking.App;
import com.banking.AppEnum;
import com.banking.db.DbConnection;
import com.banking.models.CustomerModel;
import com.banking.models.MessageModel;
import com.banking.models.TransactionModel;
import com.banking.models.TransactionType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aviator
 */
public class CustomerLogic implements CustomerLogicI {

    private DbConnection dbConnection;
    private final String tbName = "customers";

    public CustomerLogic() {
    }

    private CustomerLogic(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    private static CustomerLogic cl;

    public static CustomerLogic getInstance(DbConnection dbConnection) {
        if (cl == null) {
            cl = new CustomerLogic(dbConnection);
        }
        return cl;
    }

    @Override
    public boolean createCustomer(CustomerModel customerModel) {
        return createCustomer(dbConnection, customerModel);
    }

    @Override
    public boolean createCustomer(DbConnection dbConnection1, CustomerModel customerModel) {
        try {
            String sql = "INSERT INTO customers(`ct_fname`, `ct_lname`, `ct_email`, `ct_phone`,"
                    + "`ct_address`, `ct_city`, `ct_country`, `ct_gender`, `ct_accounttype`, `ct_accountnumber`,"
                    + " `ct_accesscode`) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = dbConnection1.getPreparedStatement(sql);
            ps.setString(1, customerModel.getCt_fname());
            ps.setString(2, customerModel.getCt_lname());
            ps.setString(3, customerModel.getCt_email());
            ps.setString(4, customerModel.getCt_phone());
            ps.setString(5, customerModel.getCt_address());
            ps.setString(6, customerModel.getCt_city());
            ps.setString(7, customerModel.getCt_country());
            ps.setString(8, customerModel.getCt_gender());
            ps.setString(9, customerModel.getCt_accounttype());
            ps.setString(10, App.getAccountNumber());
            ps.setString(11, App.getAccessCode(""));
            return dbConnection1.execute(ps);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public CustomerModel getCustomer(String email) {
        try {
            String sql = "SELECT * FROM customers WHERE ct_email=?";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, email);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                CustomerModel cm = new CustomerModel();
                cm.setCt_id(rs.getInt("ct_id"));
                cm.setCt_fname(rs.getString("ct_fname"));
                cm.setCt_lname(rs.getString("ct_lname"));
                cm.setCt_email(rs.getString("ct_email"));
                cm.setCt_phone(rs.getString("ct_phone"));
                cm.setCt_nextkin(rs.getString("ct_nextkin"));
                cm.setCt_country(rs.getString("ct_country"));
                cm.setCt_city(rs.getString("ct_city"));
                cm.setCt_address(rs.getString("ct_address"));
                cm.setCt_gender(rs.getString("ct_gender"));
                cm.setCt_accounttype(rs.getString("ct_accounttype"));
                cm.setCt_accountnumber(rs.getString("ct_accountnumber"));
                cm.setCt_accbalance(rs.getDouble("ct_accbalance"));
                cm.setCt_accesscode(rs.getString("ct_accesscode"));
                cm.setCt_pic(rs.getString("ct_pic"));
                cm.setCt_date(rs.getString("ct_date"));

                return cm;
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerLogic.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public CustomerModel getCustomer(ResultSet rs) {
        try {
            CustomerModel cm = new CustomerModel();
            cm.setCt_id(rs.getInt("ct_id"));
            cm.setCt_fname(rs.getString("ct_fname"));
            cm.setCt_lname(rs.getString("ct_lname"));
            cm.setCt_email(rs.getString("ct_email"));
            cm.setCt_phone(rs.getString("ct_phone"));
            cm.setCt_nextkin(rs.getString("ct_nextkin"));
            cm.setCt_country(rs.getString("ct_country"));
            cm.setCt_city(rs.getString("ct_city"));
            cm.setCt_address(rs.getString("ct_address"));
            cm.setCt_gender(rs.getString("ct_gender"));
            cm.setCt_accounttype(rs.getString("ct_accounttype"));
            cm.setCt_accountnumber(rs.getString("ct_accountnumber"));
            cm.setCt_accbalance(rs.getDouble("ct_accbalance"));
            cm.setCt_accesscode(rs.getString("ct_accesscode"));
            cm.setCt_pic(rs.getString("ct_pic"));
            cm.setCt_date(rs.getString("ct_date"));

            return cm;
        } catch (SQLException e) {
            Logger.getLogger(CustomerLogic.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public MessageModel checkEmail(String email) {
        try {
            String accessCode = App.getAccessCode("");

            String sql = "SELECT * FROM ".concat(tbName) + " WHERE ct_email=?";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, email);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                CustomerModel cm = getCustomer(rs);
                sql = "UPDATE ".concat(tbName) + " SET ct_accesscode=? WHERE ct_email=?";
                ps = dbConnection.getPreparedStatement(sql);
                ps.setString(1, accessCode);
                ps.setString(2, email);
                if (dbConnection.execute(ps)) {
                    //send code to email here
                    if (cm != null) {
                        cm.setCt_accesscode(accessCode);
                    }
                }
                return new MessageModel(true, rs.getString("ct_fname"), cm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new MessageModel(false, "Failed");
    }

    @Override
    public MessageModel checkPassword(String email, String pwd) {
        try {
            String sql = "SELECT * FROM ".concat(tbName) + " WHERE ct_email=? && ct_accesscode=?";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pwd);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                CustomerModel cm = getCustomer(rs);
                return new MessageModel(true, rs.getString("ct_fname"), cm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new MessageModel(false, "Failed");
    }

    @Override
    public double getTotalDeposits(CustomerModel cm) {
        List<TransactionModel> models = getDeposits(cm);
        if (models != null) {
            double sum = 0;
            sum = models.stream().map(model -> model.getTr_amount()).reduce(sum, (accumulator, _item) -> accumulator + _item);
            return sum;
        }
        return 0;
    }

    @Override
    public double getTotalWithdrawals(CustomerModel cm) {
        List<TransactionModel> models = getWithdrawals(cm);
        if (models != null) {
            double sum = 0;
            sum = models.stream().map(model -> model.getTr_amount()).reduce(sum, (accumulator, _item) -> accumulator + _item);
            return sum;
        }
        return 0;
    }

    @Override
    public double getBalance(CustomerModel cm) {
        CustomerModel cm1 = getCustomer(cm.getCt_email());
        if (cm1 != null) {
            return cm1.getCt_accbalance();
        }
        return 0;
    }

    @Override
    public List<TransactionModel> getDeposits(CustomerModel cm) {
        TransactionType transactionType = TransactionTypeLogic.getInstance(dbConnection).getTransactionType(AppEnum.DEPOSIT.getName());
        if (transactionType != null) {
            return getAllTransactions(cm, transactionType);
        }
        return null;
    }

    @Override
    public List<TransactionModel> getWithdrawals(CustomerModel cm) {
        TransactionType transactionType = TransactionTypeLogic.getInstance(dbConnection).getTransactionType(AppEnum.WITHDRAW.getName());
        if (transactionType != null) {
            return getAllTransactions(cm, transactionType);
        }
        return null;
    }

    @Override
    public List<TransactionModel> getAllTransactions(CustomerModel cm, TransactionType transactionType) {
        try {
            String sql = transactionType != null
                    ? "SELECT * FROM transactions WHERE (tr_accountnumber=?) AND (tr_type=?) ORDER BY tr_id DESC LIMIT 300"
                    : "SELECT * FROM transactions WHERE (tr_accountnumber=?) ORDER BY tr_id DESC LIMIT 300 ";

            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, cm.getCt_accountnumber());
            if (transactionType != null) {
                ps.setString(2, transactionType.getTp_type());
            }

            ResultSet rs = dbConnection.executeQuery(ps);
            return TranasctionLogic.getInstance(dbConnection).getTransactionModels(rs);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public MessageModel deposit(CustomerModel cm, double amount) {
        if (Math.abs(amount) > 300000) {
            return new MessageModel(false, "Invalid amount");
        }
        CustomerModel cm1 = getCustomer(cm.getCt_email());
        if (cm1 == null) {
            return new MessageModel(false, "Account verification failed");
        }
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setTr_accountnumber(cm.getCt_accountnumber());
        transactionModel.setTr_amount(amount);
        TransactionType transactionType = TransactionTypeLogic.getInstance(dbConnection).getTransactionType(AppEnum.DEPOSIT.getName());
        if (transactionType != null) {
            double newAmount = (cm1.getCt_accbalance() + Math.abs(amount)) - transactionType.getTp_charge();
            MessageModel messageModel = updateAccountBal(newAmount, cm);
            if (messageModel.isSuccess()) {
                transactionModel.setTr_charge(transactionType.getTp_charge());
                transactionModel.setTr_type(transactionType.getTp_type());
                MessageModel messageModel1 = TranasctionLogic.getInstance(dbConnection).createTransaction(transactionModel);
                Map<String, Object> map = new HashMap<>();
                map.put("account", messageModel);
                map.put("transaction", messageModel1);
                return new MessageModel(true, "Successfully deposited " + amount, map);
            }
            return new MessageModel(false, "Failed to complete transaction");
        }
        return new MessageModel(false, "Undefined transaction type");
    }

    @Override
    public MessageModel withdraw(CustomerModel cm, double amount) {
        if (Math.abs(amount) > 300000) {
            return new MessageModel(false, "Invalid amount");
        }
        CustomerModel cm1 = getCustomer(cm.getCt_email());
        if (cm1 == null) {
            return new MessageModel(false, "Account verification failed");
        }
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setTr_accountnumber(cm.getCt_accountnumber());
        transactionModel.setTr_amount(amount);
        TransactionType transactionType = TransactionTypeLogic.getInstance(dbConnection).getTransactionType(AppEnum.WITHDRAW.getName());
        if (transactionType != null) {
            if (cm1.getCt_accbalance() < (amount + transactionModel.getTr_charge())) {
                return new MessageModel(false, "Insufficient balance");
            }

            double newAmount = cm1.getCt_accbalance() - (Math.abs(amount) + transactionType.getTp_charge());
            MessageModel messageModel = updateAccountBal(newAmount, cm);
            if (messageModel.isSuccess()) {
                transactionModel.setTr_charge(transactionType.getTp_charge());
                transactionModel.setTr_type(transactionType.getTp_type());
                MessageModel messageModel1 = TranasctionLogic.getInstance(dbConnection).createTransaction(transactionModel);
                Map<String, Object> map = new HashMap<>();
                map.put("account", messageModel);
                map.put("transaction", messageModel1);
                return new MessageModel(true, "Successfully withdrawn " + amount, map);
            }
            return new MessageModel(false, "Failed to complete transaction");
        }
        return new MessageModel(false, "Undefined transaction type");
    }

    @Override
    public MessageModel checkBalance(CustomerModel cm) {
        CustomerModel cm1 = getCustomer(cm.getCt_email());
        if (cm1 == null) {
            return new MessageModel(false, "Account verification failed");
        }
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setTr_accountnumber(cm.getCt_accountnumber());
        transactionModel.setTr_amount(0);
        TransactionType transactionType = TransactionTypeLogic.getInstance(dbConnection).getTransactionType(AppEnum.BALANCE.getName());
        if (transactionType != null) {
            transactionModel.setTr_charge(transactionType.getTp_charge());
            transactionModel.setTr_type(transactionType.getTp_type());
            MessageModel messageModel = TranasctionLogic.getInstance(dbConnection).createTransaction(transactionModel);
            messageModel.setObject(cm1);
            return messageModel;
        }
        return new MessageModel(false, "Undefined transaction type");
    }

    @Override
    public MessageModel updateAccountBal(double newBal, CustomerModel cm) {
        try {
            String sql = "UPDATE ".concat(tbName) + " SET ct_accbalance=? WHERE ct_accountnumber=?";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setDouble(1, newBal);
            ps.setString(2, cm.getCt_accountnumber());
            if (dbConnection.execute(ps)) {
                return new MessageModel(false, "success", getCustomer(cm.getCt_email()));
            }
            return new MessageModel(false, "Unable to perform opertion");
        } catch (SQLException ex) {
            Logger.getLogger(CustomerLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new MessageModel(false, "Failed,please try again");
    }
}
