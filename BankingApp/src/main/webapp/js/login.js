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

//
var buildFormContent = {
    email: {
        "type": "email",
        "placeholder": "Email",
        "btnText": "Next",
        btnCallBack: function () {
            console.log('check mail');
            //checkEmail();
        }
    },
    pwd: {
        "type": "password",
        "placeholder": "Email",
        "btnText": "Next",
        btnCallBack: function () {
        }
    }
};

//
function buildRequestFormField(field) {
    var data;
    switch (field) {
        case "email":
            data = buildFormContent.email;
            break;
        case "pwd":
            data = buildFormContent.pwd;
            break;

        default:
            return;
    }
    var content = `<div class="form-group">
                                <input type="${data.type}" class="form-control" placeholder="${data.placeholder}" required="">
                            </div>
                <button type="submit" class="btn btn-primary block full-width m-b" onclick="${data.btnCallBack.bind()}">${data.btnText}</button>`;
    return content;
}

//
function otherFormUtils() {
    return `<a href="#">
                                <small>Forgot password?</small>
                            </a>

                            <p class="text-muted text-center">
                                <small>Do not have an account?</small>
                            </p>
                            <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>`;
}

//
function checkEmail() {
    app.loadData.call({
        dataUrl: "auth",
        method: 'get',
        params: null,
        callBack: function (data) {
            alert(data);
        }
    });
}