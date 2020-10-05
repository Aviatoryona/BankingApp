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

import com.banking.entities.Customers;
import com.banking.entities.Transactions;
import com.banking.interfaces.TranasctionLogicI;
import com.banking.models.MessageModel;
import java.util.Calendar;
import java.util.List;
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
@Remote(TranasctionLogicI.class)
public class TranasctionLogic implements TranasctionLogicI {

    @PersistenceContext
    EntityManager em;

    @Override
    public MessageModel executeTransaction(Transactions model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageModel createTransaction(Transactions model) {
        model.setTrDate(Calendar.getInstance().getTime());
        em.merge(model);
        return new MessageModel(true, "Successfull");
    }

    @Override
    public List<Transactions> getTransactions(Query q) {
        return q.getResultList();
    }

    @Override
    public List<Transactions> getTransactions(Customers cm) {
        Query q = em.createQuery("SELECT t FROM Transactions t WHERE t.trAccountnumber = :trAccountnumber ORDER BY t.trId DESC");
        q.setParameter("trAccountnumber", cm.getCtAccountnumber());
        q.setMaxResults(1000);
        return getTransactions(q);
    }

    @Override
    public List<Transactions> getTransactions() {
        return em.createQuery("FROM Transactions o").getResultList();
    }

    @Override
    public List<Transactions> getTransactions(int limit) {
        Query q = em.createQuery("FROM Transactions o");
        if (limit != -1) {
            q.setMaxResults(limit);
        }
        return q.getResultList();
    }

}
