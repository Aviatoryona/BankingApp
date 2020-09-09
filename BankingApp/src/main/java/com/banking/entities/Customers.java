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
@Table(name = "customers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
    @NamedQuery(name = "Customers.findByCtId", query = "SELECT c FROM Customers c WHERE c.ctId = :ctId"),
    @NamedQuery(name = "Customers.findByCtFname", query = "SELECT c FROM Customers c WHERE c.ctFname = :ctFname"),
    @NamedQuery(name = "Customers.findByCtLname", query = "SELECT c FROM Customers c WHERE c.ctLname = :ctLname"),
    @NamedQuery(name = "Customers.findByCtEmail", query = "SELECT c FROM Customers c WHERE c.ctEmail = :ctEmail"),
    @NamedQuery(name = "Customers.findByCtPhone", query = "SELECT c FROM Customers c WHERE c.ctPhone = :ctPhone"),
    @NamedQuery(name = "Customers.findByCtNextkin", query = "SELECT c FROM Customers c WHERE c.ctNextkin = :ctNextkin"),
    @NamedQuery(name = "Customers.findByCtAddress", query = "SELECT c FROM Customers c WHERE c.ctAddress = :ctAddress"),
    @NamedQuery(name = "Customers.findByCtCity", query = "SELECT c FROM Customers c WHERE c.ctCity = :ctCity"),
    @NamedQuery(name = "Customers.findByCtCountry", query = "SELECT c FROM Customers c WHERE c.ctCountry = :ctCountry"),
    @NamedQuery(name = "Customers.findByCtGender", query = "SELECT c FROM Customers c WHERE c.ctGender = :ctGender"),
    @NamedQuery(name = "Customers.findByCtAccounttype", query = "SELECT c FROM Customers c WHERE c.ctAccounttype = :ctAccounttype"),
    @NamedQuery(name = "Customers.findByCtAccountnumber", query = "SELECT c FROM Customers c WHERE c.ctAccountnumber = :ctAccountnumber"),
    @NamedQuery(name = "Customers.findByCtAccbalance", query = "SELECT c FROM Customers c WHERE c.ctAccbalance = :ctAccbalance"),
    @NamedQuery(name = "Customers.findByCtAccesscode", query = "SELECT c FROM Customers c WHERE c.ctAccesscode = :ctAccesscode"),
    @NamedQuery(name = "Customers.findByCtPic", query = "SELECT c FROM Customers c WHERE c.ctPic = :ctPic"),
    @NamedQuery(name = "Customers.findByCtDate", query = "SELECT c FROM Customers c WHERE c.ctDate = :ctDate"),
    @NamedQuery(name = "Customers.findAllOrderById", query = "SELECT c FROM Customers c ORDER BY c.ctId DESC")
})
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ct_id")
    private Integer ctId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_fname")
    private String ctFname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_lname")
    private String ctLname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_email")
    private String ctEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_phone")
    private String ctPhone;
    @Size(max = 255)
    @Column(name = "ct_nextkin")
    private String ctNextkin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_address")
    private String ctAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_city")
    private String ctCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_country")
    private String ctCountry;
    @Size(max = 255)
    @Column(name = "ct_gender")
    private String ctGender;
    @Size(max = 255)
    @Column(name = "ct_accounttype")
    private String ctAccounttype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_accountnumber")
    private String ctAccountnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ct_accbalance")
    private long ctAccbalance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ct_accesscode")
    private String ctAccesscode;
    @Size(max = 255)
    @Column(name = "ct_pic")
    private String ctPic;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ct_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ctDate;

    public Customers() {
    }

    public Customers(Integer ctId) {
        this.ctId = ctId;
    }

    public Customers(Integer ctId, String ctFname, String ctLname, String ctEmail, String ctPhone, String ctAddress, String ctCity, String ctCountry, String ctAccountnumber, long ctAccbalance, String ctAccesscode, Date ctDate) {
        this.ctId = ctId;
        this.ctFname = ctFname;
        this.ctLname = ctLname;
        this.ctEmail = ctEmail;
        this.ctPhone = ctPhone;
        this.ctAddress = ctAddress;
        this.ctCity = ctCity;
        this.ctCountry = ctCountry;
        this.ctAccountnumber = ctAccountnumber;
        this.ctAccbalance = ctAccbalance;
        this.ctAccesscode = ctAccesscode;
        this.ctDate = ctDate;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public String getCtFname() {
        return ctFname;
    }

    public void setCtFname(String ctFname) {
        this.ctFname = ctFname;
    }

    public String getCtLname() {
        return ctLname;
    }

    public void setCtLname(String ctLname) {
        this.ctLname = ctLname;
    }

    public String getCtEmail() {
        return ctEmail;
    }

    public void setCtEmail(String ctEmail) {
        this.ctEmail = ctEmail;
    }

    public String getCtPhone() {
        return ctPhone;
    }

    public void setCtPhone(String ctPhone) {
        this.ctPhone = ctPhone;
    }

    public String getCtNextkin() {
        return ctNextkin;
    }

    public void setCtNextkin(String ctNextkin) {
        this.ctNextkin = ctNextkin;
    }

    public String getCtAddress() {
        return ctAddress;
    }

    public void setCtAddress(String ctAddress) {
        this.ctAddress = ctAddress;
    }

    public String getCtCity() {
        return ctCity;
    }

    public void setCtCity(String ctCity) {
        this.ctCity = ctCity;
    }

    public String getCtCountry() {
        return ctCountry;
    }

    public void setCtCountry(String ctCountry) {
        this.ctCountry = ctCountry;
    }

    public String getCtGender() {
        return ctGender;
    }

    public void setCtGender(String ctGender) {
        this.ctGender = ctGender;
    }

    public String getCtAccounttype() {
        return ctAccounttype;
    }

    public void setCtAccounttype(String ctAccounttype) {
        this.ctAccounttype = ctAccounttype;
    }

    public String getCtAccountnumber() {
        return ctAccountnumber;
    }

    public void setCtAccountnumber(String ctAccountnumber) {
        this.ctAccountnumber = ctAccountnumber;
    }

    public long getCtAccbalance() {
        return ctAccbalance;
    }

    public void setCtAccbalance(long ctAccbalance) {
        this.ctAccbalance = ctAccbalance;
    }

    public String getCtAccesscode() {
        return ctAccesscode;
    }

    public void setCtAccesscode(String ctAccesscode) {
        this.ctAccesscode = ctAccesscode;
    }

    public String getCtPic() {
        return ctPic;
    }

    public void setCtPic(String ctPic) {
        this.ctPic = ctPic;
    }

    public Date getCtDate() {
        return ctDate;
    }

    public void setCtDate(Date ctDate) {
        this.ctDate = ctDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctId != null ? ctId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        return !((this.ctId == null && other.ctId != null) || (this.ctId != null && !this.ctId.equals(other.ctId)));
    }

    @Override
    public String toString() {
        return "com.banking.entities.Customers[ ctId=" + ctId + " ]";
    }

}
