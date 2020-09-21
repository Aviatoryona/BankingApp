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
import com.banking.entities.Users;
import com.banking.interfaces.UsersLogicI;
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
@Remote(UsersLogicI.class)
public class UsersLogic implements UsersLogicI {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Users> getUsers(int limit) {
        if (limit == -1) {
            return em.createNamedQuery("Users.findAllOrdered").getResultList();
        }

        Query q = em.createNamedQuery("Users.findAllOrdered");
        q.setMaxResults(limit);
        return q.getResultList();
    }

    @Override
    public MessageModel addUser(Users users) {
        users.getClientUserSd().setCtDate(Calendar.getInstance().getTime());
        em.persist(users);
        return new MessageModel(true, "Successfully added", users);
    }

    @Override
    public MessageModel removeUser(Users users) {
        em.remove(em.find(Accounttypes.class, users));
        return new MessageModel(true, "Deleted", users);
    }

    @Override
    public Users getUser(int id) {
        return em.find(Users.class, id);
    }

    @Override
    public Users getUser(String username, String pwd) {
        try {
            Query q = em.createQuery("SELECT u FROM Users u WHERE u.clientUserSd.ctEmail = :usrUsername OR u.usrUsername = :usrUsername AND u.usrPwd = :usrPwd");
            q.setParameter("usrUsername", username.trim());
            q.setParameter("usrPwd", pwd.trim());
            return (Users) q.getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    @Override
    public MessageModel updateUser(Users users) {
        em.merge(users);
        return new MessageModel(true, "User information updated");
    }

    @Override
    public Users getUser(String email) {
        try {
            Query q = em.createNamedQuery("Users.findByUsrEmail", Users.class);
            q.setParameter("usrEmail", email);
            return (Users) q.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public MessageModel changeUsername(String email, String userName) {
        Users u = getUser(email);
        if (u == null) {
            return new MessageModel(false, "User not found");
        }
        Users uu = u;
        uu.setUsrUsername(userName);
        return updateUser(uu);
    }

    @Override
    public MessageModel changePwd(String email, String pwd) {
        Users u = getUser(email);
        if (u == null) {
            return new MessageModel(false, "User not found");
        }
        Users uu = u;
        uu.setUsrPwd(pwd);
        return updateUser(uu);
    }

}
