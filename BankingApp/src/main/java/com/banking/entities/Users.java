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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findAllOrdered", query = "SELECT u FROM Users u ORDER BY u.usrId DESC"),
    @NamedQuery(name = "Users.findByUsrId", query = "SELECT u FROM Users u WHERE u.usrId = :usrId"),
    @NamedQuery(name = "Users.findByUsrFname", query = "SELECT u FROM Users u WHERE u.usrFname = :usrFname"),
    @NamedQuery(name = "Users.findByUsrLname", query = "SELECT u FROM Users u WHERE u.usrLname = :usrLname"),
    @NamedQuery(name = "Users.findByUsrEmail", query = "SELECT u FROM Users u WHERE u.usrEmail = :usrEmail"),
    @NamedQuery(name = "Users.findByUsrUsername", query = "SELECT u FROM Users u WHERE u.usrUsername = :usrUsername"),
    @NamedQuery(name = "Users.findByUsrPwd", query = "SELECT u FROM Users u WHERE u.usrPwd = :usrPwd"),
    @NamedQuery(name = "Users.findByUsrPhone", query = "SELECT u FROM Users u WHERE u.usrPhone = :usrPhone"),
    @NamedQuery(name = "Users.findByUsrRole", query = "SELECT u FROM Users u WHERE u.usrRole = :usrRole"),
    @NamedQuery(name = "Users.findByUsrStatus", query = "SELECT u FROM Users u WHERE u.usrStatus = :usrStatus"),
    @NamedQuery(name = "Users.findByUsrImage", query = "SELECT u FROM Users u WHERE u.usrImage = :usrImage"),
    @NamedQuery(name = "Users.findByUsrDate", query = "SELECT u FROM Users u WHERE u.usrDate = :usrDate")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usr_id")
    private Integer usrId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_fname")
    private String usrFname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_lname")
    private String usrLname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_email")
    private String usrEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_username")
    private String usrUsername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_pwd")
    private String usrPwd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_phone")
    private String usrPhone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usr_role")
    private String usrRole;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_status")
    private short usrStatus;
    @Size(max = 255)
    @Column(name = "usr_image")
    private String usrImage;

    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usrDate;

    public Users() {
    }

    public Users(Integer usrId) {
        this.usrId = usrId;
    }

    public Users(Integer usrId, String usrFname, String usrLname, String usrEmail, String usrUsername, String usrPwd, String usrPhone, String usrRole, short usrStatus, Date usrDate) {
        this.usrId = usrId;
        this.usrFname = usrFname;
        this.usrLname = usrLname;
        this.usrEmail = usrEmail;
        this.usrUsername = usrUsername;
        this.usrPwd = usrPwd;
        this.usrPhone = usrPhone;
        this.usrRole = usrRole;
        this.usrStatus = usrStatus;
        this.usrDate = usrDate;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrFname() {
        return usrFname;
    }

    public void setUsrFname(String usrFname) {
        this.usrFname = usrFname;
    }

    public String getUsrLname() {
        return usrLname;
    }

    public void setUsrLname(String usrLname) {
        this.usrLname = usrLname;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrUsername() {
        return usrUsername;
    }

    public void setUsrUsername(String usrUsername) {
        this.usrUsername = usrUsername;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public String getUsrPhone() {
        return usrPhone;
    }

    public void setUsrPhone(String usrPhone) {
        this.usrPhone = usrPhone;
    }

    public String getUsrRole() {
        return usrRole;
    }

    public void setUsrRole(String usrRole) {
        this.usrRole = usrRole;
    }

    public short getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(short usrStatus) {
        this.usrStatus = usrStatus;
    }

    public String getUsrImage() {
        return usrImage;
    }

    public void setUsrImage(String usrImage) {
        this.usrImage = usrImage;
    }

    public Date getUsrDate() {
        return usrDate;
    }

    public void setUsrDate(Date usrDate) {
        this.usrDate = usrDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.banking.entities.Users[ usrId=" + usrId + " ]";
    }

}
