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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @NamedQuery(name = "Customers.findByCtNextkin", query = "SELECT c FROM Customers c WHERE c.ctNextkin = :ctNextkin"),
    @NamedQuery(name = "Customers.findByCtAddress", query = "SELECT c FROM Customers c WHERE c.ctAddress = :ctAddress"),
    @NamedQuery(name = "Customers.findByCtCity", query = "SELECT c FROM Customers c WHERE c.ctCity = :ctCity"),
    @NamedQuery(name = "Customers.findByCtCountry", query = "SELECT c FROM Customers c WHERE c.ctCountry = :ctCountry"),
    @NamedQuery(name = "Customers.findByCtGender", query = "SELECT c FROM Customers c WHERE c.ctGender = :ctGender"),
    @NamedQuery(name = "Customers.findByCtAccounttype", query = "SELECT c FROM Customers c WHERE c.ctAccounttype = :ctAccounttype"),
    @NamedQuery(name = "Customers.findByCtAccountnumber", query = "SELECT c FROM Customers c WHERE c.ctAccountnumber = :ctAccountnumber"),
    @NamedQuery(name = "Customers.findByCtAccbalance", query = "SELECT c FROM Customers c WHERE c.ctAccbalance = :ctAccbalance"),
    @NamedQuery(name = "Customers.findByCtAccesscode", query = "SELECT c FROM Customers c WHERE c.ctAccesscode = :ctAccesscode"),

    @NamedQuery(name = "Customers.findAllOrderById", query = "SELECT c FROM Customers c ORDER BY c.ctId DESC")
})
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ct_id")
    private Integer ctId;

    @Column(name = "ct_nextkin")
    private String ctNextkin;

    @Column(name = "ct_address")
    private String ctAddress;

    @Column(name = "ct_city")
    private String ctCity;

    @Column(name = "ct_country")
    private String ctCountry;

    @Column(name = "ct_gender")
    private String ctGender;

    @Column(name = "ct_accounttype")
    private String ctAccounttype;

    @Column(name = "ct_accountnumber")
    private String ctAccountnumber;

    @Column(name = "ct_accbalance")
    private long ctAccbalance;

    @Column(name = "ct_accesscode")
    private String ctAccesscode;

    @Embedded
    private ClientUserSd clientUserSd;

    public Customers() {
    }

    public Customers(Integer ctId) {
        this.ctId = ctId;
    }

    public Customers(Integer ctId, String ctNextkin, String ctAddress, String ctCity, String ctCountry, String ctGender, String ctAccounttype, String ctAccountnumber, long ctAccbalance, String ctAccesscode, ClientUserSd clientUserSd) {
        this.ctId = ctId;
        this.ctNextkin = ctNextkin;
        this.ctAddress = ctAddress;
        this.ctCity = ctCity;
        this.ctCountry = ctCountry;
        this.ctGender = ctGender;
        this.ctAccounttype = ctAccounttype;
        this.ctAccountnumber = ctAccountnumber;
        this.ctAccbalance = ctAccbalance;
        this.ctAccesscode = ctAccesscode;
        this.clientUserSd = clientUserSd;
    }

    /**
     * @return the ctId
     */
    public Integer getCtId() {
        return ctId;
    }

    /**
     * @param ctId the ctId to set
     */
    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    /**
     * @return the ctNextkin
     */
    public String getCtNextkin() {
        return ctNextkin;
    }

    /**
     * @param ctNextkin the ctNextkin to set
     */
    public void setCtNextkin(String ctNextkin) {
        this.ctNextkin = ctNextkin;
    }

    /**
     * @return the ctAddress
     */
    public String getCtAddress() {
        return ctAddress;
    }

    /**
     * @param ctAddress the ctAddress to set
     */
    public void setCtAddress(String ctAddress) {
        this.ctAddress = ctAddress;
    }

    /**
     * @return the ctCity
     */
    public String getCtCity() {
        return ctCity;
    }

    /**
     * @param ctCity the ctCity to set
     */
    public void setCtCity(String ctCity) {
        this.ctCity = ctCity;
    }

    /**
     * @return the ctCountry
     */
    public String getCtCountry() {
        return ctCountry;
    }

    /**
     * @param ctCountry the ctCountry to set
     */
    public void setCtCountry(String ctCountry) {
        this.ctCountry = ctCountry;
    }

    /**
     * @return the ctGender
     */
    public String getCtGender() {
        return ctGender;
    }

    /**
     * @param ctGender the ctGender to set
     */
    public void setCtGender(String ctGender) {
        this.ctGender = ctGender;
    }

    /**
     * @return the ctAccounttype
     */
    public String getCtAccounttype() {
        return ctAccounttype;
    }

    /**
     * @param ctAccounttype the ctAccounttype to set
     */
    public void setCtAccounttype(String ctAccounttype) {
        this.ctAccounttype = ctAccounttype;
    }

    /**
     * @return the ctAccountnumber
     */
    public String getCtAccountnumber() {
        return ctAccountnumber;
    }

    /**
     * @param ctAccountnumber the ctAccountnumber to set
     */
    public void setCtAccountnumber(String ctAccountnumber) {
        this.ctAccountnumber = ctAccountnumber;
    }

    /**
     * @return the ctAccbalance
     */
    public long getCtAccbalance() {
        return ctAccbalance;
    }

    /**
     * @param ctAccbalance the ctAccbalance to set
     */
    public void setCtAccbalance(long ctAccbalance) {
        this.ctAccbalance = ctAccbalance;
    }

    /**
     * @return the ctAccesscode
     */
    public String getCtAccesscode() {
        return ctAccesscode;
    }

    /**
     * @param ctAccesscode the ctAccesscode to set
     */
    public void setCtAccesscode(String ctAccesscode) {
        this.ctAccesscode = ctAccesscode;
    }

    /**
     * @return the clientUserSd
     */
    public ClientUserSd getClientUserSd() {
        return clientUserSd;
    }

    /**
     * @param clientUserSd the clientUserSd to set
     */
    public void setClientUserSd(ClientUserSd clientUserSd) {
        this.clientUserSd = clientUserSd;
    }

}
