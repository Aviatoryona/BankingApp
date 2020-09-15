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
package com.banking;

import com.banking.entities.Accounttypes;
import com.banking.entities.Countries;
import com.banking.interfaces.AppI;
import com.banking.models.MessageModel;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class App implements AppI {

    @PersistenceContext
    EntityManager em;

    /*
    Register init fields
    Loads data to Populate  registration input values like countries and account type
     */
    @Override
    public Map<String, Object> registerInit() {
        Map<String, Object> map = new HashMap<>();
        map.put("acctypes", getAccountTypes());
        map.put("countries", getCountries());
        return map;
    }

    //get countries
    @Override
    public List<Countries> getCountries() {
        return em.createNamedQuery("Countries.findAll").getResultList();
    }

    //get account types
    @Override
    public List<Accounttypes> getAccountTypes() {
        return em.createNamedQuery("Accounttypes.findAll").getResultList();
    }

    //Generate account number
    public static String getAccountNumber() {
        Date date = new Date();
        long l = date.getTime();
        String cvv = getCvv();
        return String.valueOf(l).concat(cvv);
    }

    public static String getCvv() {
        int x = new Random().nextInt(10000);
        String s = String.valueOf(x);
        if (s.length() < 3) {
            getCvv();
        }
        return s;
    }

    //get access code. A Random generate key like a password
    private static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$*";

    //generate access code
    public static String getAccessCode(String code) {
        char[] cs = characters.toCharArray();
        code += String.valueOf(cs[new Random().nextInt(cs.length)]);
        return code.length() == 8 ? code : getAccessCode(code);
    }

    @Override
    public MessageModel adCountry(Countries c) {
        em.merge(c);
        return new MessageModel(true, "Added " + c.getCtryName());
    }

}
