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

import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.interfaces.CustomerLogicI;
import com.banking.interfaces.TransactionTypeLogicI;
import com.banking.models.MessageModel;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Aviator
 */
@Stateless
@Path("/customers")
public class CustomersFacadeREST extends AbstractFacade<Customers> {

    @PersistenceContext(unitName = "banking-app")
    private EntityManager em;

    public CustomersFacadeREST() {
        super(Customers.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Path(value = "/create")
    public String create(Customers entity) {
        return super.create(entity);
    }

    @PUT
    @Path("/edit/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Customers entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("/remove/{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("/get/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customers find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    @Path(value = "/getAll")
    public List<Customers> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/find/{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Customers> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    private CustomerLogicI customerLogicI;

    @EJB
    private TransactionTypeLogicI transactionTypeLogicI;

    public boolean createCustomer(Customers customers) {
        return customerLogicI.createCustomer(customers);
    }

    @GET
    @Path(value = "/getByEmail/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public Customers getCustomer(@PathParam(value = "email") String email) {
        return customerLogicI.getCustomer(email);
    }

    @GET
    @Path(value = "/checkEmail/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel checkEmail(@PathParam(value = "email") String email) {
        return customerLogicI.checkEmail(email);
    }

    @GET
    @Path(value = "/checkPassword/{email}/{pwd}")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel checkPassword(@PathParam(value = "email") String email, @PathParam(value = "pwd") String pwd) {
        return customerLogicI.checkPassword(email, pwd);
    }

    @POST
    @Path(value = "/getTotalDeposits/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public double getTotalDeposits(@PathParam(value = "email") String email) {
        Customers cm = customerLogicI.getCustomer(email);
        return cm != null ? customerLogicI.getTotalDeposits(cm) : -1;
    }

    @POST
    @Path(value = "/getTotalWithdrawals/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public double getTotalWithdrawals(@PathParam(value = "email") String email) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return -1;
        }
        return customerLogicI.getTotalWithdrawals(cm);
    }

    @POST
    @Path(value = "/getBalance/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public double getBalance(@PathParam(value = "email") String email) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return -1;
        }
        return customerLogicI.getBalance(cm);
    }

    @POST
    @Path(value = "/getDeposits/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getDeposits(@PathParam(value = "email") String email) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return null;
        }
        return customerLogicI.getDeposits(cm);
    }

    @POST
    @Path(value = "/getWithdrawals/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getWithdrawals(@PathParam(value = "email") String email) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return null;
        }
        return customerLogicI.getWithdrawals(cm);
    }

    @POST
    @Path(value = "/getAllTransactions/{email}/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getAllTransactions(@PathParam(value = "email") String email, @PathParam(value = "type") String transactionType) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return null;
        }
        return customerLogicI.getAllTransactions(cm, transactionTypeLogicI.getTransactionType(transactionType));
    }

    @POST
    @Path(value = "/deposit/{email}/{amount}")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel deposit(@PathParam(value = "email") String email, @PathParam(value = "amount") double amount) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return new MessageModel(false, "Account validation failed");
        }
        return customerLogicI.deposit(cm, amount);
    }

    @POST
    @Path(value = "/withdraw/{email}/{amount}")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel withdraw(@PathParam(value = "email") String email, @PathParam(value = "amount") double amount) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return new MessageModel(false, "Account validation failed");
        }
        return customerLogicI.withdraw(cm, amount);
    }

    @POST
    @Path(value = "/checkBalance/{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel checkBalance(@PathParam(value = "email") String email) {
        Customers cm = customerLogicI.getCustomer(email);
        if (cm == null) {
            return new MessageModel(false, "Account validation failed");
        }
        return customerLogicI.checkBalance(cm);
    }

}
