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
var buildFormContent = {
    email: {
        "id": "input_email",
        "type": "email",
        "placeholder": "Email",
        "btnText": "Next",
        btnCallBack: function () {
//            console.log('check mail');
            var email = document.getElementById("input_email").value;
            if (email == '') {
                return;
            }
            checkEmail(email);
        }
    },
    pwd: {
        "id": "input_pwd",
        "type": "password",
        "placeholder": "Password",
        "btnText": "Next",
        btnCallBack: function () {
            window.location.replace('dashboard');
        }
    }
};

/*
 *
 * @param {type} field
 * @returns {undefined|String}
 */
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
                                <input id="${data.id}" type="${data.type}" class="form-control" placeholder="${data.placeholder}" required="">
                            </div>`;

    var btn = document.getElementById('mibtn');
    btn.textContent = data.btnText;
    btn.addEventListener('click', function () {
        data.btnCallBack();
    });
    return content;
}

/*
 *
 * @returns {String}
 */
function otherFormUtils() {
    return `<div id="mimsg"></div><a href="#">
                                <small>Forgot password?</small>
                            </a>

                            <p class="text-muted text-center">
                                <small>Do not have an account?</small>
                            </p>
                            <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>`;
}

/*
 *
 */
function checkEmail(email) {
    app.loadData.call({
        dataUrl: "auth",
        method: 'POST',
        isJson: true,
        params: `action=checkmail&email=` + email,
        callBack: function (data) {
            //alert(data.message);
            if (data.success) {
                document.getElementById("formData").innerHTML =
                        `<small style="color:red;">Password has been sent to your email</small>` +
                        buildRequestFormField("pwd");
            } else {
                document.getElementById("mimsg").innerHTML =
                        `<small style="color:red;">${data.message}</small>`;
                setTimeout(function () {
                    document.getElementById("mimsg").innerHTML = '';
                }, 5000);
            }
        }
    });
}