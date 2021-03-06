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
package com.banking.interfaces;

import com.banking.entities.Accounttypes;
import com.banking.entities.Countries;
import com.banking.models.MessageModel;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Aviator
 */
@Remote
public interface AppI {

    Map<String, Object> registerInit();

    List<Countries> getCountries();

    List<Accounttypes> getAccountTypes();

    MessageModel adCountry(Countries c);

    String getAccountNumber();

    String getAccessCode(String code);
}
