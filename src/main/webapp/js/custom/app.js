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
var app = app || {};
/*
 * Used to populate template data at a specified index using GET request
 */
const BASE_URL = "concorde";
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
        xhr.setRequestHeader("Content-type", typeof me.contentType !== 'undefined' ? me.contentType : "application/x-www-form-urlencoded");
//        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        if (typeof me.contentType !== 'undefined')
            if (me.contentType == 'application/json') {
                xhr.send(JSON.stringify(me.params));
                return;
            }
        xhr.send(me.params);
    } else {
        xhr.send();
    }
};
/*
 *
 * @returns {undefined}
 */
app.fetchData = async function () {
    var me = this;
    const myHeaders = new Headers({
        'Content-Type': 'application/x-www-form-urlencoded'
    });
    var body = me.params != null ? me.params : '';
    const myRequest = new Request(me.dataUrl, {
        method: me.method,
        headers: myHeaders,
        mode: 'cors',
        cache: 'default',
        body: body
    });
    var res = await fetch(myRequest);
    return me.isJson ? res.json() : res.text();
};
/*
 *
 * @returns {undefined}
 */

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
 *
 * @returns {app.submitForm}
 */
app.submitForm = function () {
    var url = this.url;
    var params = JSON.stringify(this.params);
    var bfor = this.bfor;
    var afta = this.afta;
    jQuery.ajax({
        url: `${url}`,
        type: "POST",
        data: params,
        processData: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        beforeSend: function (xhr) {
            bfor();
        },
        success: function (data, textStatus, jqXHR) {
            afta(data);
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
            content += `<option value="${country.ctryName}">${country.ctryName}</option>`;
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
 *
 * @returns {undefined}
 */
app.doRegister = function () {
    var json = {
        "ctId": -1,
        "ctNextkin": '',
        "ctAddress": this.address,
        "ctCity": this.city,
        "ctCountry": this.country,
        "ctGender": this.gender,
        "ctAccounttype": this.acctype,
        "ctAccountnumber": "",
        "ctAccbalance": 0,
        "ctAccesscode": '',
        "clientUserSd": {
            "ctFname": this.fname,
            "ctLname": this.lname,
            "ctEmail": this.email,
            "ctPhone": this.phone,
            "ctPic": '',
            "ctDate": new Date(),
            "convertedDate": new Date()
        }
    };
    /*
     *
     * @type type
     */
    var obj = {
        url: `${BASE_URL}/customers/createAccount`,
        params: json,
        bfor: function () {
        },
        afta: function (data) {
            if (data.success) {
                swal({
                    title: "Done",
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
    };
    app.submitForm.call(obj);
};
/*
 * do register
 */
app.doRegister1 = function () {

    var url = `ctFname=` + this.fname
            + `&ctLname=` + this.lname
            + `&ctEmail=` + this.email
            + `&ctPhone=` + this.phone
            + `&ctCountry=` + this.country
            + `&ctCity=` + this.city
            + `&ctAddress=` + this.address
            + `&ctGender=` + this.gender
            + `&ctAccounttype=` + this.acctype;
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
 *create json dynamically from form
 */
function populateFormJson(formData, fieldName, fieldValue) {
    var formFieldNameParts = fieldName.split('.');
    var lastFieldNamePartIdx = formFieldNameParts.length - 1;
    for (var i = 0; i < lastFieldNamePartIdx; ++i) {
        var currentFieldNamePart = formFieldNameParts[i];
        if (!(currentFieldNamePart in formData)) {
            formData[currentFieldNamePart] = {};
        }

        formData = formData[currentFieldNamePart];
    }

    formData[formFieldNameParts[lastFieldNamePartIdx]] = fieldValue;
}

/*
 *
 * @returns {undefined}
 */
function setPlaceholder(callBack = null) {
    $('#mi_content').html(`<div class="middle-box text-center loginscreen animated fadeInDown">
    <div class="ibox">
        <div class="ibox-content">
            <img src="./img/waiting_Live.gif"/>
        </div>
    </div>
</div>`);
    /*
     *
     *
     */
    if (callBack != null)
        setTimeout(function () {
            callBack();
        }, 3000);
}

/*
 * initialize app
 */
app.init.call();