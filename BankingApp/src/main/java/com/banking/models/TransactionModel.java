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
public class TransactionModel {

    private int tr_id;
    private double tr_amount;
    private double tr_charge;
    private String tr_accountnumber;
    private String tr_type;
    private String tr_date;

    public TransactionModel() {
    }

    /**
     * @return the tr_id
     */
    public int getTr_id() {
        return tr_id;
    }

    /**
     * @param tr_id the tr_id to set
     */
    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
    }

    /**
     * @return the tr_amount
     */
    public double getTr_amount() {
        return tr_amount;
    }

    /**
     * @param tr_amount the tr_amount to set
     */
    public void setTr_amount(double tr_amount) {
        this.tr_amount = tr_amount;
    }

    /**
     * @return the tr_charge
     */
    public double getTr_charge() {
        return tr_charge;
    }

    /**
     * @param tr_charge the tr_charge to set
     */
    public void setTr_charge(double tr_charge) {
        this.tr_charge = tr_charge;
    }

    /**
     * @return the tr_accountnumber
     */
    public String getTr_accountnumber() {
        return tr_accountnumber;
    }

    /**
     * @param tr_accountnumber the tr_accountnumber to set
     */
    public void setTr_accountnumber(String tr_accountnumber) {
        this.tr_accountnumber = tr_accountnumber;
    }

    /**
     * @return the tr_type
     */
    public String getTr_type() {
        return tr_type;
    }

    /**
     * @param tr_type the tr_type to set
     */
    public void setTr_type(String tr_type) {
        this.tr_type = tr_type;
    }

    /**
     * @return the tr_date
     */
    public String getTr_date() {
        return tr_date;
    }

    /**
     * @param tr_date the tr_date to set
     */
    public void setTr_date(String tr_date) {
        this.tr_date = tr_date;
    }

}
