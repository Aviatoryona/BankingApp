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

/*
 *
 * @type type
 */
var app = {};

/*
 * init
 */
app.init = function () {
    $('#btnRegister').click(function () {
        register();
    });
};

/*
 * http request
 */
app.loadData = function () {
    var me = this;
    var xhr;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
//                alert(xhr.responseText);
                me.data = me.isJson ? eval('(' + xhr.responseText + ')') : xhr.responseText;
                me.callBack(me.data);
            }
//            console.log(xhr.responseText);
        }
    };
    xhr.open(me.method, me.dataUrl, true);
    if (me.params != null) {
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(me.params);
    } else {
        xhr.send();
    }
};

app.loadTemplate = function () {
    var me = this;
    $.ajax({
        url: this.dataUrl,
        type: this.method,
        success: function (data, textStatus, jqXHR) {
//            console.log(data);
            me.callBack(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
//            console.log(jqXHR);
            me.callBack("Error encountered");
        }
    });
};

/*
 * called when register.html is loaded
 */
function registerInit(data) {
    var countries = data.countries;
    if (countries != null) {
        var element = document.getElementById("input_countries");
        var content = `<option selected disabled>Country</option>`;
        countries.forEach(country => {
            content += `<option value="${country.ctry_name}">${country.ctry_name}</option>`;
        });
        element.innerHTML = content;
    }

    var acctypes = data.acctypes;
    if (acctypes != null) {
        var element = document.getElementById("input_accounttypes");
        var content = `<option selected disabled>Account Type</option>`;
        acctypes.forEach(acctype => {
            content += `<option value="${acctype.acctype}">${acctype.acctype}</option>`;
        });
        element.innerHTML = content;
    }
}

/*
 * do register
 */
app.doRegister = function () {
    var url = `ct_fname=` + this.fname
            + `&ct_lname=` + this.lname
            + `&ct_email=` + this.email
            + `&ct_phone=` + this.phone
            + `&ct_country=` + this.country
            + `&ct_city=` + this.city
            + `&ct_address=` + this.address
            + `&ct_gender=` + this.gender
            + `&ct_accounttype=` + this.acctype;
    app.loadData.call({
        dataUrl: "register",
        method: 'POST',
        isJson: true,
        params: url,
        callBack: function (data) {
            if (data.success) {
                swal({
                    title: "Registration Successful",
                    text: data.message,
                    type: "success"
                });
                setTimeout(function () {
                    window.location.href = "auth";
                }, 5000);
            } else {
                swal({
                    title: "Registration Failed",
                    text: data.message,
                    type: "error"
                });
            }
        }
    });
};

/*
 * initialize app
 */
app.init.call();