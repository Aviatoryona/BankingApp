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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Aviator
 */
@Embeddable
public class ClientUserSd implements Serializable {

    @Column(name = "ct_fname")
    private String ctFname;

    @Column(name = "ct_lname")
    private String ctLname;

    @Column(name = "ct_email")
    private String ctEmail;

    @Column(name = "ct_phone")
    private String ctPhone;

    @Column(name = "ct_pic")
    private String ctPic;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date ctDate;

    @Transient
    private String convertedDate;

    /**
     * @return the ctFname
     */
    public String getCtFname() {
        return ctFname;
    }

    /**
     * @param ctFname the ctFname to set
     */
    public void setCtFname(String ctFname) {
        this.ctFname = ctFname;
    }

    /**
     * @return the ctLname
     */
    public String getCtLname() {
        return ctLname;
    }

    /**
     * @param ctLname the ctLname to set
     */
    public void setCtLname(String ctLname) {
        this.ctLname = ctLname;
    }

    /**
     * @return the ctEmail
     */
    public String getCtEmail() {
        return ctEmail;
    }

    /**
     * @param ctEmail the ctEmail to set
     */
    public void setCtEmail(String ctEmail) {
        this.ctEmail = ctEmail;
    }

    /**
     * @return the ctPhone
     */
    public String getCtPhone() {
        return ctPhone;
    }

    /**
     * @param ctPhone the ctPhone to set
     */
    public void setCtPhone(String ctPhone) {
        this.ctPhone = ctPhone;
    }

    /**
     * @return the ctPic
     */
    public String getCtPic() {
        return ctPic;
    }

    /**
     * @param ctPic the ctPic to set
     */
    public void setCtPic(String ctPic) {
        this.ctPic = ctPic;
    }

    /**
     * @return the ctDate
     */
    public Date getCtDate() {
        return ctDate;
    }

    /**
     * @param ctDate the ctDate to set
     */
    public void setCtDate(Date ctDate) {
        this.ctDate = ctDate;
    }

    /**
     * @return the convertedDate
     */
    public String getConvertedDate() {
        return convertedDate;
    }

    /**
     * @param convertedDate the convertedDate to set
     */
    public void setConvertedDate(String convertedDate) {
        this.convertedDate = new SimpleDateFormat("MM dd, YYYY", Locale.getDefault()).format(getCtDate());
    }

}
