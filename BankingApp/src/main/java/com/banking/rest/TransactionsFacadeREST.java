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
import com.banking.interfaces.TranasctionLogicI;
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
@Path("/transactions")
public class TransactionsFacadeREST extends AbstractFacade<Transactions> {

    @PersistenceContext(unitName = "banking-app")
    private EntityManager em;

    public TransactionsFacadeREST() {
        super(Transactions.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(Transactions entity) {
        return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Transactions entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Transactions find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @GET
    @Path(value = "/getTransactions")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getTransactions() {
        return tranasctionLogicI.getTransactions();
    }

    @GET
    @Path(value = "/getLimitedTransactions/{limit}")
    public List<Transactions> getTransactions(@PathParam(value = "email") int limit) {
        return tranasctionLogicI.getTransactions(limit);
    }

    @POST
    @Path(value = "/getTransactions/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactions> getTransactions(@PathParam(value = "email") String email) {
        Customers cm = customerLogicI.getCustomer(email);
        return cm != null ? tranasctionLogicI.getTransactions(cm)
                : null;
    }

    @EJB
    private TranasctionLogicI tranasctionLogicI;

    @EJB
    private CustomerLogicI customerLogicI;

}
