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

import com.banking.entities.Transactiontypes;
import com.banking.interfaces.TransactionTypeLogicI;
import com.banking.models.MessageModel;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@Path("/transactiontypes")
public class TransactiontypesFacadeREST extends AbstractFacade<Transactiontypes> {

    @PersistenceContext(unitName = "banking-app")
    private EntityManager em;

    public TransactiontypesFacadeREST() {
        super(Transactiontypes.class);
    }

    @POST
    @Override
    @Path(value = "/create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String create(Transactiontypes entity) {
        return super.create(entity);
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
    private TransactionTypeLogicI transactionTypeLogicI;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path(value = "/create")
    public MessageModel addTransactiontypes(Transactiontypes transactiontypes) {
        return transactionTypeLogicI.addTransactiontypes(transactiontypes);
    }

    @GET
    @Path("/remove/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    public MessageModel removeTransactiontypes(@PathParam(value = "type") String type) {
        return transactionTypeLogicI.removeTransactiontypes(transactionTypeLogicI.getTransactionType(type));
    }

    @GET
    @Path("/get/{type}")
    @Produces({MediaType.APPLICATION_JSON})
    public Transactiontypes getTransactionType(@PathParam(value = "type") String type) {
        return transactionTypeLogicI.getTransactionType(type);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Transactiontypes> getTransactiontypeses() {
        return transactionTypeLogicI.getTransactiontypeses();
    }

}
