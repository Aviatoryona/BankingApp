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

import com.banking.entities.Accounttypes;
import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.entities.Transactiontypes;
import com.banking.entities.Users;
import com.banking.models.MessageModel;
import com.banking.rest.AdminFacadeRest;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author Aviator
 */
@WebService(serviceName = "AdminFacadeSOAP")
@Stateless()
public class AdminFacadeSOAP {

    @EJB
    private AdminFacadeRest adminFacadeRest;

    @WebMethod(operationName = "totalTransactions")
    public int totalTransactions(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end) {
        return adminFacadeRest.totalTransactions(start, end);
    }

    @WebMethod(operationName = "totalCustomers")
    public int totalCustomers() {
        return adminFacadeRest.totalCustomers();
    }

    @WebMethod(operationName = "totalProfit")
    public double totalProfit(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end) {
        return adminFacadeRest.totalProfit(start, end);
    }

    @WebMethod(operationName = "totalCustomers_1")
    @RequestWrapper(className = "totalCustomers_1")
    @ResponseWrapper(className = "totalCustomers_1Response")
    public int totalCustomers(@WebParam(name = "start") Date start, @WebParam(name = "end") Date end) {
        return adminFacadeRest.totalCustomers(start, end);
    }

    @WebMethod(operationName = "getUser")
    public Users getUser(@WebParam(name = "username") String username, @WebParam(name = "pwd") String pwd) {
        return adminFacadeRest.getUser(username, pwd);
    }

    @WebMethod(operationName = "getUser_1")
    @RequestWrapper(className = "getUser_1")
    @ResponseWrapper(className = "getUser_1Response")
    public Users getUser(@WebParam(name = "id") int id) {
        return adminFacadeRest.getUser(id);
    }

    @WebMethod(operationName = "getCustomer")
    public Customers getCustomer(@WebParam(name = "email") String email) {
        return adminFacadeRest.getCustomer(email);
    }

    @WebMethod(operationName = "getRegisteredCustomers")
    public List<Customers> getRegisteredCustomers(@WebParam(name = "limit") int limit) {
        return adminFacadeRest.getRegisteredCustomers(limit);
    }

    @WebMethod(operationName = "getRegisteredCustomers_1")
    @RequestWrapper(className = "getRegisteredCustomers_1")
    @ResponseWrapper(className = "getRegisteredCustomers_1Response")
    public List<Customers> getRegisteredCustomers(@WebParam(name = "limit") int limit, @WebParam(name = "start") Date start, @WebParam(name = "end") Date end) {
        return adminFacadeRest.getRegisteredCustomers(limit, start, end);
    }

    @WebMethod(operationName = "getTransactions")
    public List<Transactions> getTransactions(@WebParam(name = "limit") int limit) {
        return adminFacadeRest.getTransactions(limit);
    }

    @WebMethod(operationName = "getTransactions_1")
    @RequestWrapper(className = "getTransactions_1")
    @ResponseWrapper(className = "getTransactions_1Response")
    public List<Transactions> getTransactions(@WebParam(name = "email") String email) {
        return adminFacadeRest.getTransactions(email);
    }

    @WebMethod(operationName = "getTransactions_2")
    @RequestWrapper(className = "getTransactions_2")
    @ResponseWrapper(className = "getTransactions_2Response")
    public List<Transactions> getTransactions(@WebParam(name = "limit") int limit, @WebParam(name = "start") Date start, @WebParam(name = "end") Date end) {
        return adminFacadeRest.getTransactions(limit, start, end);
    }

    @WebMethod(operationName = "getUsers")
    public List<Users> getUsers() {
        return adminFacadeRest.getUsers();
    }

    @WebMethod(operationName = "getTransactiontypes")
    public List<Transactiontypes> getTransactiontypes() {
        return adminFacadeRest.getTransactiontypes();
    }

    @WebMethod(operationName = "getAccounttypes")
    public List<Accounttypes> getAccounttypes() {
        return adminFacadeRest.getAccounttypes();
    }

    @WebMethod(operationName = "addAccountType")
    public MessageModel addAccountType(@WebParam(name = "accounttypes") Accounttypes accounttypes) {
        return adminFacadeRest.addAccountType(accounttypes);
    }

    @WebMethod(operationName = "addTransactionType")
    public MessageModel addTransactionType(@WebParam(name = "t") Transactiontypes t) {
        return adminFacadeRest.addTransactionType(t);
    }

    @WebMethod(operationName = "addUser")
    public MessageModel addUser(@WebParam(name = "user") Users user) {
        return adminFacadeRest.addUser(user);
    }

    @WebMethod(operationName = "reverseTransaction")
    public MessageModel reverseTransaction(@WebParam(name = "t") Transactions t) {
        return adminFacadeRest.reverseTransaction(t);
    }

    @WebMethod(operationName = "updateCustomer")
    public MessageModel updateCustomer(@WebParam(name = "c") Customers c) {
        return adminFacadeRest.updateCustomer(c);
    }

    @WebMethod(operationName = "updateUser")
    public MessageModel updateUser(@WebParam(name = "u") Users u) {
        return adminFacadeRest.updateUser(u);
    }

    @WebMethod(operationName = "deleteUser")
    public MessageModel deleteUser(@WebParam(name = "id") int id) {
        return adminFacadeRest.deleteUser(id);
    }

    @WebMethod(operationName = "deleteCustomer")
    public MessageModel deleteCustomer(@WebParam(name = "email") String email) {
        return adminFacadeRest.deleteCustomer(email);
    }

    @WebMethod(operationName = "deleteTransactionType")
    public MessageModel deleteTransactionType(@WebParam(name = "type") String type) {
        return adminFacadeRest.deleteTransactionType(type);
    }

    @WebMethod(operationName = "deleteAccountType")
    public MessageModel deleteAccountType(@WebParam(name = "type") String type) {
        return adminFacadeRest.deleteAccountType(type);
    }

    @WebMethod(operationName = "processIndex0")
    public MessageModel processIndex0() {
        return adminFacadeRest.processIndex0();
    }

}
