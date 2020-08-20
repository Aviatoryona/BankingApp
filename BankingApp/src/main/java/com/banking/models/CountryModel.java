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
package com.banking.models;

/**
 *
 * @author Aviator
 */
public class CountryModel {

    private int ctry_id;
    private String ctry_name;

    public CountryModel(int ctry_id, String ctry_name) {
        this.ctry_id = ctry_id;
        this.ctry_name = ctry_name;
    }

    /**
     * @return the ctry_id
     */
    public int getCtry_id() {
        return ctry_id;
    }

    /**
     * @param ctry_id the ctry_id to set
     */
    public void setCtry_id(int ctry_id) {
        this.ctry_id = ctry_id;
    }

    /**
     * @return the ctry_name
     */
    public String getCtry_name() {
        return ctry_name;
    }

    /**
     * @param ctry_name the ctry_name to set
     */
    public void setCtry_name(String ctry_name) {
        this.ctry_name = ctry_name;
    }
}
