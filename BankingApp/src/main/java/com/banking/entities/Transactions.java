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
package com.banking.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aviator
 */
@Entity
@Table(name = "transactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTrId", query = "SELECT t FROM Transactions t WHERE t.trId = :trId"),
    @NamedQuery(name = "Transactions.findByTrAccountnumber", query = "SELECT t FROM Transactions t WHERE t.trAccountnumber = :trAccountnumber"),
    @NamedQuery(name = "Transactions.findByTrType", query = "SELECT t FROM Transactions t WHERE t.trType = :trType"),
    @NamedQuery(name = "Transactions.findByTrAmount", query = "SELECT t FROM Transactions t WHERE t.trAmount = :trAmount"),
    @NamedQuery(name = "Transactions.findByTrCharge", query = "SELECT t FROM Transactions t WHERE t.trCharge = :trCharge"),
    @NamedQuery(name = "Transactions.findByTrDate", query = "SELECT t FROM Transactions t WHERE t.trDate = :trDate")})
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tr_id")
    private Integer trId;
    
    @Column(name = "tr_accountnumber")
    private String trAccountnumber;

    @Column(name = "tr_type")
    private String trType;

    @Column(name = "tr_amount")
    private long trAmount;

    @Column(name = "tr_charge")
    private double trCharge;

    @Column(name = "tr_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trDate;

    public Transactions() {
    }

    public Transactions(Integer trId) {
        this.trId = trId;
    }

    public Transactions(Integer trId, String trAccountnumber, String trType, long trAmount, double trCharge, Date trDate) {
        this.trId = trId;
        this.trAccountnumber = trAccountnumber;
        this.trType = trType;
        this.trAmount = trAmount;
        this.trCharge = trCharge;
        this.trDate = trDate;
    }

    public Integer getTrId() {
        return trId;
    }

    public void setTrId(Integer trId) {
        this.trId = trId;
    }

    public String getTrAccountnumber() {
        return trAccountnumber;
    }

    public void setTrAccountnumber(String trAccountnumber) {
        this.trAccountnumber = trAccountnumber;
    }

    public String getTrType() {
        return trType;
    }

    public void setTrType(String trType) {
        this.trType = trType;
    }

    public long getTrAmount() {
        return trAmount;
    }

    public void setTrAmount(long trAmount) {
        this.trAmount = trAmount;
    }

    public double getTrCharge() {
        return trCharge;
    }

    public void setTrCharge(double trCharge) {
        this.trCharge = trCharge;
    }

    public Date getTrDate() {
        return trDate;
    }

    public void setTrDate(Date trDate) {
        this.trDate = trDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trId != null ? trId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        return !((this.trId == null && other.trId != null) || (this.trId != null && !this.trId.equals(other.trId)));
    }

    @Override
    public String toString() {
        return "com.banking.entities.Transactions[ trId=" + trId + " ]";
    }

}
