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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aviator
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findAllOrdered", query = "SELECT u FROM Users u ORDER BY u.usrId DESC"),
    @NamedQuery(name = "Users.findByUsrId", query = "SELECT u FROM Users u WHERE u.usrId = :usrId"),
    @NamedQuery(name = "Users.findByUsrUsername", query = "SELECT u FROM Users u WHERE u.usrUsername = :usrUsername"),
    @NamedQuery(name = "Users.findByUsrPwd", query = "SELECT u FROM Users u WHERE u.usrPwd = :usrPwd"),
    @NamedQuery(name = "Users.findByUsrRole", query = "SELECT u FROM Users u WHERE u.usrRole = :usrRole"),
    @NamedQuery(name = "Users.findByUsrStatus", query = "SELECT u FROM Users u WHERE u.usrStatus = :usrStatus"),})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usr_id")
    private Integer usrId;

    @Size(min = 1, max = 255)
    @Column(name = "usr_username")
    private String usrUsername;

    @Size(min = 1, max = 255)
    @Column(name = "usr_pwd")
    private String usrPwd;

    @Column(name = "usr_role")
    private String usrRole;

    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_status")
    private short usrStatus;

    @Embedded
    private ClientUserSd clientUserSd;

    public Users() {
    }

    public Users(Integer usrId) {
        this.usrId = usrId;
    }

    public Users(Integer usrId, String usrUsername, String usrPwd, String usrRole, short usrStatus, ClientUserSd clientUserSd) {
        this.usrId = usrId;
        this.usrUsername = usrUsername;
        this.usrPwd = usrPwd;
        this.usrRole = usrRole;
        this.usrStatus = usrStatus;
        this.clientUserSd = clientUserSd;
    }

    /**
     * @return the usrId
     */
    public Integer getUsrId() {
        return usrId;
    }

    /**
     * @param usrId the usrId to set
     */
    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    /**
     * @return the usrUsername
     */
    public String getUsrUsername() {
        return usrUsername;
    }

    /**
     * @param usrUsername the usrUsername to set
     */
    public void setUsrUsername(String usrUsername) {
        this.usrUsername = usrUsername;
    }

    /**
     * @return the usrPwd
     */
    public String getUsrPwd() {
        return usrPwd;
    }

    /**
     * @param usrPwd the usrPwd to set
     */
    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    /**
     * @return the usrRole
     */
    public String getUsrRole() {
        return usrRole;
    }

    /**
     * @param usrRole the usrRole to set
     */
    public void setUsrRole(String usrRole) {
        this.usrRole = usrRole;
    }

    /**
     * @return the usrStatus
     */
    public short getUsrStatus() {
        return usrStatus;
    }

    /**
     * @param usrStatus the usrStatus to set
     */
    public void setUsrStatus(short usrStatus) {
        this.usrStatus = usrStatus;
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
