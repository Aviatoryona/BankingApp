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

import com.banking.entities.Transactiontypes;
import com.banking.interfaces.TransactionTypeLogicI;
import com.banking.models.MessageModel;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aviator
 */
@Stateless
@Remote(TransactionTypeLogicI.class)
public class TransactionTypeLogic implements TransactionTypeLogicI {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Transactiontypes getTransactionType(String type) {
//        return em.find(Transactiontypes.class, type);
        return (Transactiontypes) em.createNamedQuery("Transactiontypes.findByTpType")
                .setParameter("tpType", type)
                .getSingleResult();
    }

    @Override
    public List<Transactiontypes> getTransactiontypeses() {
        return em.createNamedQuery("Transactiontypes.findAll").getResultList();
    }

    @Override
    public MessageModel addTransactiontypes(Transactiontypes transactiontypes) {
        em.merge(transactiontypes);
        return new MessageModel(true, "Done", transactiontypes);
    }

    @Override
    public MessageModel removeTransactiontypes(Transactiontypes transactiontypes) {
        em.remove(em.find(Transactiontypes.class, transactiontypes));
        return new MessageModel(true, "Done", transactiontypes);
    }

}
