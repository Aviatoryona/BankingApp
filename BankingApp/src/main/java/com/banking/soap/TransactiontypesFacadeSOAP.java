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

import com.banking.entities.Transactiontypes;
import com.banking.models.MessageModel;
import com.banking.rest.TransactiontypesFacadeREST;
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
@WebService(serviceName = "TransactiontypesFacadeSOAP")
@Stateless()
public class TransactiontypesFacadeSOAP {

    @EJB
    private TransactiontypesFacadeREST transactiontypesFacadeREST;

    @WebMethod(operationName = "create")
    public String create(@WebParam(name = "entity") Transactiontypes entity) {
        return transactiontypesFacadeREST.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "id") Integer id, @WebParam(name = "entity") Transactiontypes entity) {
        transactiontypesFacadeREST.edit(id, entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "id") Integer id) {
        transactiontypesFacadeREST.remove(id);
    }

    @WebMethod(operationName = "find")
    public Transactiontypes find(@WebParam(name = "id") Integer id) {
        return transactiontypesFacadeREST.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Transactiontypes> findAll() {
        return transactiontypesFacadeREST.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Transactiontypes> findRange(@WebParam(name = "from") Integer from, @WebParam(name = "to") Integer to) {
        return transactiontypesFacadeREST.findRange(from, to);
    }

    @WebMethod(operationName = "countREST")
    public String countREST() {
        return transactiontypesFacadeREST.countREST();
    }

    @WebMethod(operationName = "addTransactiontypes")
    public MessageModel addTransactiontypes(@WebParam(name = "transactiontypes") Transactiontypes transactiontypes) {
        return transactiontypesFacadeREST.addTransactiontypes(transactiontypes);
    }

    @WebMethod(operationName = "removeTransactiontypes")
    public MessageModel removeTransactiontypes(@WebParam(name = "type") String type) {
        return transactiontypesFacadeREST.removeTransactiontypes(type);
    }

    @WebMethod(operationName = "getTransactionType")
    public Transactiontypes getTransactionType(@WebParam(name = "type") String type) {
        return transactiontypesFacadeREST.getTransactionType(type);
    }

    @WebMethod(operationName = "getTransactiontypeses")
    public List<Transactiontypes> getTransactiontypeses() {
        return transactiontypesFacadeREST.getTransactiontypeses();
    }

}
