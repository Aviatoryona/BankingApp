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
package com.banking.rest;

import com.banking.entities.Accounttypes;
import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.entities.Transactiontypes;
import com.banking.entities.Users;
import com.banking.interfaces.AccounttypesI;
import com.banking.interfaces.AdminLogicI;
import com.banking.interfaces.TransactionTypeLogicI;
import com.banking.models.MessageModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Aviator
 */
@Stateless
@Path(value = "/admin")
public class AdminFacadeRest {

    @EJB
    private AdminLogicI adminLogicI;

    @POST
    @Path(value = "/totalTransactions/{start}/{end}")
    @Produces({MediaType.TEXT_PLAIN})
    public int totalTransactions(@PathParam(value = "start") Date start, @PathParam(value = "end") Date end) {
        return adminLogicI.totalTransactions(start, end);
    }

    @GET
    @Path(value = "/totalCustomers")
    @Produces({MediaType.TEXT_PLAIN})
    public int totalCustomers() {
        return adminLogicI.totalCustomers();
    }

    @POST
    @Path(value = "/totalProfit/{start}/{end}")
    @Produces({MediaType.TEXT_PLAIN})
    public double totalProfit(@PathParam(value = "start") Date start, @PathParam(value = "end") Date end) {
        return adminLogicI.totalProfit(start, end);
    }

    @POST
    @Path(value = "/totalCustomers/{start}/{end}")
    @Produces({MediaType.TEXT_PLAIN})
    public int totalCustomers(@PathParam(value = "start") Date start, @PathParam(value = "end") Date end) {
        return adminLogicI.totalCustomers(start, end);
    }

    @POST
    @Path(value = "/getUser/{username}/{pwd}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users getUser(@PathParam(value = "username") String username, @PathParam(value = "pwd") String pwd) {
        return adminLogicI.getUser(username, pwd);
    }

    @GET
    @Path(value = "/getUser/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users getUser(@PathParam(value = "id") int id) {
        return adminLogicI.getUser(id);
    }

    @GET
    @Path(value = "/getCustomer/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customers getCustomer(@PathParam(value = "email") String email) {
        return adminLogicI.getCustomer(email);
    }

    @GET
    @Path(value = "/getRegisteredCustomers/{limit}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customers> getRegisteredCustomers(@PathParam(value = "limit") int limit) {
        return adminLogicI.getRegisteredCustomers(limit);
    }

    @GET
    @Path(value = "/getRegisteredCustomers/{limit}/{start}/{end}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customers> getRegisteredCustomers(@PathParam(value = "limit") int limit, @PathParam(value = "start") Date start, @PathParam(value = "end") Date end) {
        return adminLogicI.getRegisteredCustomers(limit, start, end);
    }

    @GET
    @Path(value = "/getTransactions/{limit}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getTransactions(@PathParam(value = "limit") int limit) {
        return adminLogicI.getTransactions(limit);
    }

    @GET
    @Path(value = "/getTransactions/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getTransactions(@PathParam(value = "email") String email) {
        Customers c = adminLogicI.getCustomer(email);
        return c != null ? adminLogicI.getTransactions(c) : new ArrayList<>();
    }

    @GET
    @Path(value = "/getTransactions/{limit}/{start}/{end}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getTransactions(@PathParam(value = "limit") int limit, @PathParam(value = "start") Date start, @PathParam(value = "end") Date end) {
        return adminLogicI.getTransactions(limit, start, end);
    }

    @GET
    @Path(value = "/getUsers")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Users> getUsers() {
        return adminLogicI.getUsers();
    }

    @GET
    @Path(value = "/getTransactiontypes")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactiontypes> getTransactiontypes() {
        return adminLogicI.getTransactiontypes();
    }

    @GET
    @Path(value = "/getTransactiontypes")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Accounttypes> getAccounttypes() {
        return adminLogicI.getAccounttypes();
    }

    @POST
    @Path(value = "/addAccountType")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel addAccountType(Accounttypes accounttypes) {
        return adminLogicI.addAccountType(accounttypes);
    }

    @POST
    @Path(value = "/addTransactionType")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel addTransactionType(Transactiontypes t) {
        return adminLogicI.addTransactionType(t);
    }

    @POST
    @Path(value = "/addUser")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel addUser(Users user) {
        return adminLogicI.addUser(user);
    }

    public MessageModel reverseTransaction(Transactions t) {
        return adminLogicI.reverseTransaction(t);
    }

    public MessageModel updateCustomer(Customers c) {
        return adminLogicI.updateCustomer(c);
    }

    @POST
    @Path(value = "/updateUser")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel updateUser(Users u) {
        return adminLogicI.updateUser(u);
    }

    @POST
    @Path(value = "/deleteUser/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel deleteUser(@PathParam(value = "id") int id) {
        Users u = adminLogicI.getUser(id);
        return adminLogicI.deleteUser(u);
    }

    @POST
    @Path(value = "/deleteCustomer/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel deleteCustomer(@PathParam(value = "email") String email) {
        Customers c = adminLogicI.getCustomer(email);
        return adminLogicI.deleteCustomer(c);
    }

    @EJB
    TransactionTypeLogicI transactionTypeLogicI;

    @POST
    @Path(value = "/deleteTransactionType/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel deleteTransactionType(@PathParam(value = "type") String type) {
        Transactiontypes t = transactionTypeLogicI.getTransactionType(type);
        return adminLogicI.deleteTransactionType(t);
    }

    @EJB
    AccounttypesI accounttypesI;

    @POST
    @Path(value = "/deleteAccountType/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public MessageModel deleteAccountType(@PathParam(value = "type") String type) {
        Accounttypes a = accounttypesI.getAccounttypes(type);
        return adminLogicI.deleteAccountType(a);
    }

    @GET
    @Path(value = "/index")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel processIndex0() {
        return adminLogicI.processIndex0();
    }

}
