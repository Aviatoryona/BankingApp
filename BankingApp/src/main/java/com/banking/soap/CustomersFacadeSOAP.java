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
package com.banking.soap;

import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.models.MessageModel;
import com.banking.rest.CustomersFacadeREST;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Aviator
 */
@WebService(serviceName = "CustomersFacadeSOAP")
@Stateless()
public class CustomersFacadeSOAP {

    @EJB
    private CustomersFacadeREST customersFacadeREST;

    @WebMethod(operationName = "create")
    public String create(@WebParam(name = "entity") Customers entity) {
        return customersFacadeREST.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "id") Integer id, @WebParam(name = "entity") Customers entity) {
        customersFacadeREST.edit(id, entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "id") Integer id) {
        customersFacadeREST.remove(id);
    }

    @WebMethod(operationName = "find")
    public Customers find(@WebParam(name = "id") Integer id) {
        return customersFacadeREST.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Customers> findAll() {
        return customersFacadeREST.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Customers> findRange(@WebParam(name = "from") Integer from, @WebParam(name = "to") Integer to) {
        return customersFacadeREST.findRange(from, to);
    }

    @WebMethod(operationName = "countREST")
    public String countREST() {
        return customersFacadeREST.countREST();
    }

    @WebMethod(operationName = "createCustomer")
    public boolean createCustomer(@WebParam(name = "customers") Customers customers) {
        return customersFacadeREST.createCustomer(customers);
    }

    @WebMethod(operationName = "getCustomer")
    public Customers getCustomer(@WebParam(name = "email") String email) {
        return customersFacadeREST.getCustomer(email);
    }

    @WebMethod(operationName = "checkEmail")
    public MessageModel checkEmail(@WebParam(name = "email") String email) {
        return customersFacadeREST.checkEmail(email);
    }

    @WebMethod(operationName = "checkPassword")
    public MessageModel checkPassword(@WebParam(name = "email") String email, @WebParam(name = "pwd") String pwd) {
        return customersFacadeREST.checkPassword(email, pwd);
    }

    @WebMethod(operationName = "getTotalDeposits")
    public double getTotalDeposits(@WebParam(name = "email") String email) {
        return customersFacadeREST.getTotalDeposits(email);
    }

    @WebMethod(operationName = "getTotalWithdrawals")
    public double getTotalWithdrawals(@WebParam(name = "email") String email) {
        return customersFacadeREST.getTotalWithdrawals(email);
    }

    @WebMethod(operationName = "getBalance")
    public double getBalance(@WebParam(name = "email") String email) {
        return customersFacadeREST.getBalance(email);
    }

    @WebMethod(operationName = "getDeposits")
    public List<Transactions> getDeposits(@WebParam(name = "email") String email) {
        return customersFacadeREST.getDeposits(email);
    }

    @WebMethod(operationName = "getWithdrawals")
    public List<Transactions> getWithdrawals(@WebParam(name = "email") String email) {
        return customersFacadeREST.getWithdrawals(email);
    }

    @WebMethod(operationName = "getAllTransactions")
    public List<Transactions> getAllTransactions(@WebParam(name = "email") String email, @WebParam(name = "transactionType") String transactionType) {
        return customersFacadeREST.getAllTransactions(email, transactionType);
    }

    @WebMethod(operationName = "deposit")
    public MessageModel deposit(@WebParam(name = "email") String email, @WebParam(name = "amount") double amount) {
        return customersFacadeREST.deposit(email, amount);
    }

    @WebMethod(operationName = "withdraw")
    public MessageModel withdraw(@WebParam(name = "email") String email, @WebParam(name = "amount") double amount) {
        return customersFacadeREST.withdraw(email, amount);
    }

    @WebMethod(operationName = "checkBalance")
    public MessageModel checkBalance(@WebParam(name = "email") String email) {
        return customersFacadeREST.checkBalance(email);
    }

}
