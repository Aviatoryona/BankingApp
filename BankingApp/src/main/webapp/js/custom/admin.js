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

var admin = {};

//do not change USED DVLPMT FOR REFERENCE
var adminPages = {
    0: 'admin/home-welcome.jsp'
};

function showValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).addClass('has-error');
}

function hideValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).removeClass('has-error');
}

/*
 *
 * @returns {undefined}
 */
admin.auth = function () {
    var usr = $('input[type="email"]').val();
    var pwd = $('input[type="password"]').val();
    if (usr == '') {
        showValidate($('input[type="email"]'));
        return;
    }
    if (pwd == '') {
        showValidate($('input[type="password"]'));
        return;
    }

    app.loadData.call({
        dataUrl: "admin",
        method: 'POST',
        isJson: true,
        params: `q=auth&usr=${usr}&pwd=${pwd}`,
        callBack: function (data) {
            if (data.success) {
                window.location.href = 'admin';
            } else {
                $('#msgbox').addClass('alert alert-danger').text('Incorect credentials');
                setTimeout(function () {
                    $('#msgbox').removeClass('alert alert-danger').text('');
                }, 3000);
            }
        }
    });
};

/*
 *
 */
admin.processIndex0 = function () {
    var index = 0;
    var page = adminPages[index];
    app.loadTemplate.call({
        dataUrl: page,
        method: 'POST',
        isJson: false,
        params: null,
        callBack: function (data) {
            $('#mi_content').html(data);
        }
    });
    /*
     * call this function to load and bind data to loaded template
     */
    app.loadData.call({
        dataUrl: `admin?action=${index}`,
        method: 'GET',
        isJson: true,
        params: null,
        callBack: function (res) {
            console.log(res);
            new Vue({
                el: '#adm_home',
                data: {
                    customers: [],
                    transactions: []
                },
                created: function () {

                }
            });
        }
    });
};
