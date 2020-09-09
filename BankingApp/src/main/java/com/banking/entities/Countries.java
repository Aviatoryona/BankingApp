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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aviator
 */
@Entity
@Table(name = "countries")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Countries.findAll", query = "SELECT c FROM Countries c"),
    @NamedQuery(name = "Countries.findByCtryId", query = "SELECT c FROM Countries c WHERE c.ctryId = :ctryId"),
    @NamedQuery(name = "Countries.findByCtryName", query = "SELECT c FROM Countries c WHERE c.ctryName = :ctryName")})
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ctry_id")
    private Integer ctryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ctry_name")
    private String ctryName;

    public Countries() {
    }

    public Countries(Integer ctryId) {
        this.ctryId = ctryId;
    }

    public Countries(Integer ctryId, String ctryName) {
        this.ctryId = ctryId;
        this.ctryName = ctryName;
    }

    public Integer getCtryId() {
        return ctryId;
    }

    public void setCtryId(Integer ctryId) {
        this.ctryId = ctryId;
    }

    public String getCtryName() {
        return ctryName;
    }

    public void setCtryName(String ctryName) {
        this.ctryName = ctryName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctryId != null ? ctryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Countries)) {
            return false;
        }
        Countries other = (Countries) object;
        if ((this.ctryId == null && other.ctryId != null) || (this.ctryId != null && !this.ctryId.equals(other.ctryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.banking.entities.Countries[ ctryId=" + ctryId + " ]";
    }

}
