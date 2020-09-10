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

import com.banking.entities.Accounttypes;
import com.banking.interfaces.AccounttypesI;
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
@Remote
public class AccountTypeLogic implements AccounttypesI {

    @PersistenceContext
    private EntityManager em;

    @Override
    public MessageModel addAccountType(Accounttypes accounttypes) {
        em.merge(accounttypes);
        return new MessageModel(true, "Successfully added", accounttypes);
    }

    @Override
    public MessageModel removeAccountType(Accounttypes accounttypes) {
        em.remove(em.find(Accounttypes.class, accounttypes));
        return new MessageModel(true, "Deleted", accounttypes);
    }

    @Override
    public List<Accounttypes> getAccounttypeses() {
        return em.createNamedQuery("Accounttypes.findAll").getResultList();
    }

}
