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
public class TransactionType {

    private int tp_id;
    private String tp_type;
    private double tp_charge;

    public TransactionType() {
    }

    /**
     * @return the tp_id
     */
    public int getTp_id() {
        return tp_id;
    }

    /**
     * @param tp_id the tp_id to set
     */
    public void setTp_id(int tp_id) {
        this.tp_id = tp_id;
    }

    /**
     * @return the tp_type
     */
    public String getTp_type() {
        return tp_type;
    }

    /**
     * @param tp_type the tp_type to set
     */
    public void setTp_type(String tp_type) {
        this.tp_type = tp_type;
    }

    /**
     * @return the tp_charge
     */
    public double getTp_charge() {
        return tp_charge;
    }

    /**
     * @param tp_charge the tp_charge to set
     */
    public void setTp_charge(double tp_charge) {
        this.tp_charge = tp_charge;
    }

}
