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
public class AccountTypes {

    private int accid;
    private String acctype;
    private double accminbal;
    private double accmaxbal;
    private String accdescription;
    private String accdate;

    public AccountTypes(int accid, String acctype) {
        this.accid = accid;
        this.acctype = acctype;
    }

    public AccountTypes(int accid, String acctype, double accminbal, double accmaxbal, String accdescription, String accdate) {
        this.accid = accid;
        this.acctype = acctype;
        this.accminbal = accminbal;
        this.accmaxbal = accmaxbal;
        this.accdescription = accdescription;
        this.accdate = accdate;
    }

    /**
     * @return the accid
     */
    public int getAccid() {
        return accid;
    }

    /**
     * @param accid the accid to set
     */
    public void setAccid(int accid) {
        this.accid = accid;
    }

    /**
     * @return the acctype
     */
    public String getAcctype() {
        return acctype;
    }

    /**
     * @param acctype the acctype to set
     */
    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    /**
     * @return the accminbal
     */
    public double getAccminbal() {
        return accminbal;
    }

    /**
     * @param accminbal the accminbal to set
     */
    public void setAccminbal(double accminbal) {
        this.accminbal = accminbal;
    }

    /**
     * @return the accmaxbal
     */
    public double getAccmaxbal() {
        return accmaxbal;
    }

    /**
     * @param accmaxbal the accmaxbal to set
     */
    public void setAccmaxbal(double accmaxbal) {
        this.accmaxbal = accmaxbal;
    }

    /**
     * @return the accdescription
     */
    public String getAccdescription() {
        return accdescription;
    }

    /**
     * @param accdescription the accdescription to set
     */
    public void setAccdescription(String accdescription) {
        this.accdescription = accdescription;
    }

    /**
     * @return the accdate
     */
    public String getAccdate() {
        return accdate;
    }

    /**
     * @param accdate the accdate to set
     */
    public void setAccdate(String accdate) {
        this.accdate = accdate;
    }

}
