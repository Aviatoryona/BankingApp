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

import static com.banking.entities.Accounttypes.AccounttypesFindAll;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "accounttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = AccounttypesFindAll, query = "SELECT a FROM Accounttypes a"),
    @NamedQuery(name = "Accounttypes.findByAccid", query = "SELECT a FROM Accounttypes a WHERE a.accid = :accid"),
    @NamedQuery(name = "Accounttypes.findByAcctype", query = "SELECT a FROM Accounttypes a WHERE a.acctype = :acctype"),
    @NamedQuery(name = "Accounttypes.findByAccminbal", query = "SELECT a FROM Accounttypes a WHERE a.accminbal = :accminbal"),
    @NamedQuery(name = "Accounttypes.findByAccmaxbal", query = "SELECT a FROM Accounttypes a WHERE a.accmaxbal = :accmaxbal"),
    @NamedQuery(name = "Accounttypes.findByAccdate", query = "SELECT a FROM Accounttypes a WHERE a.accdate = :accdate")})
public class Accounttypes implements Serializable {

    static final String AccounttypesFindAll = "Accounttypes.findAll";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accid")
    private Integer accid;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "acctype")
    private String acctype;

    @Basic(optional = false)
    @NotNull
    @Column(name = "accminbal")
    private double accminbal;

    @Basic(optional = false)
    @NotNull
    @Column(name = "accmaxbal")
    private double accmaxbal;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "accdescription")
    private String accdescription;

    @Basic(optional = false)
    @NotNull
    @Column(name = "accdate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accdate;

    public Accounttypes() {
    }

    public Accounttypes(Integer accid) {
        this.accid = accid;
    }

    public Accounttypes(Integer accid, String acctype, double accminbal, double accmaxbal, String accdescription, Date accdate) {
        this.accid = accid;
        this.acctype = acctype;
        this.accminbal = accminbal;
        this.accmaxbal = accmaxbal;
        this.accdescription = accdescription;
        this.accdate = accdate;
    }

    public Integer getAccid() {
        return accid;
    }

    public void setAccid(Integer accid) {
        this.accid = accid;
    }

    public String getAcctype() {
        return acctype;
    }

    public void setAcctype(String acctype) {
        this.acctype = acctype;
    }

    public double getAccminbal() {
        return accminbal;
    }

    public void setAccminbal(double accminbal) {
        this.accminbal = accminbal;
    }

    public double getAccmaxbal() {
        return accmaxbal;
    }

    public void setAccmaxbal(double accmaxbal) {
        this.accmaxbal = accmaxbal;
    }

    public String getAccdescription() {
        return accdescription;
    }

    public void setAccdescription(String accdescription) {
        this.accdescription = accdescription;
    }

    public Date getAccdate() {
        return accdate;
    }

    public void setAccdate(Date accdate) {
        this.accdate = accdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accid != null ? accid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounttypes)) {
            return false;
        }
        Accounttypes other = (Accounttypes) object;
        if ((this.accid == null && other.accid != null) || (this.accid != null && !this.accid.equals(other.accid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.banking.entities.Accounttypes[ accid=" + accid + " ]";
    }

}
