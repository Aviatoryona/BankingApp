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

import com.banking.entities.Transactions;
import com.banking.rest.TransactionsFacadeREST;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author Aviator
 */
@WebService(serviceName = "TransactionsFacadeSOAP")
@Stateless()
public class TransactionsFacadeSOAP {

    @EJB
    private TransactionsFacadeREST transactionsFacadeREST;

    @WebMethod(operationName = "create")
    public String create(@WebParam(name = "entity") Transactions entity) {
        return transactionsFacadeREST.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "id") Integer id, @WebParam(name = "entity") Transactions entity) {
        transactionsFacadeREST.edit(entity);
    }

    @WebMethod(operationName = "find")
    public Transactions find(@WebParam(name = "id") Integer id) {
        return transactionsFacadeREST.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Transactions> findAll() {
        return transactionsFacadeREST.findAll();
    }

    @WebMethod(operationName = "countREST")
    public String countREST() {
        return transactionsFacadeREST.countREST();
    }

    @WebMethod(operationName = "getTransactions")
    public List<Transactions> getTransactions() {
        return transactionsFacadeREST.getTransactions();
    }

    @WebMethod(operationName = "getTransactionsWithLimit")
    @RequestWrapper(className = "getTransactionsWithLimit")
    @ResponseWrapper(className = "getTransactionsWithLimitResponse")
    public List<Transactions> getTransactions(@WebParam(name = "limit") int limit) {
        return transactionsFacadeREST.getTransactions(limit);
    }

    @WebMethod(operationName = "getTransactionsByEmail")
    @RequestWrapper(className = "getTransactionsByEmail")
    @ResponseWrapper(className = "getTransactionsByEmailResponse")
    public List<Transactions> getTransactions(@WebParam(name = "email") String email) {
        return transactionsFacadeREST.getTransactions(email);
    }

}
