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
    0: 'admin/home-welcome.jsp',
    1: 'admin/transactions.jsp',
    2: 'admin/clients.jsp',
    3: 'admin/users.jsp',
    4: 'admin/manage.jsp',
    5: 'admin/profile.jsp'
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

function loadTemplate(page) {
    app.loadTemplate.call({
        dataUrl: page,
        method: 'POST',
        isJson: false,
        params: null,
        callBack: function (data) {
            $('#mi_content').html(data);
        }
    });
}
/*
 *
 */
admin.processIndex0 = function () {  //home
    var index = 0;
    var page = adminPages[index];
    loadTemplate(page);

    setTimeout(function () {
        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`admin?action=${index}`, function (res) {
            console.log(res);
            new Vue({
                el: '#adm_home',
                data: {
                    incomeToday: res.object.profit_today,
                    incomeTotal: res.object.total_profit,
                    total_clients: res.object.num_customers,
                    total_transactions: res.object.num_transactions,
                    customers: [],
                    transactions: []
                },
                created: function () {
                    const me = this;
                    me.customers = res.object.customers;
                    me.transactions = res.object.transactions;
                }
            });
        });
    }, 1000);

};

/*
 *
 */
admin.processIndex1 = function () { //transactions
    var index = 1;
    var page = adminPages[index];
    loadTemplate(page);

    setTimeout(function () {
        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`admin?action=${index}`, function (res) {
            console.log(res);
            new Vue({
                el: '#adm_home',
                data: {
                    transactions: []
                },
                created: function () {
                    const me = this;
                    me.transactions = res;
                }
            });
        });
    }, 1000);

};

/*
 *
 */
admin.processIndex2 = function () { //clients
    var index = 2;
    var page = adminPages[index];
    loadTemplate(page);

    setTimeout(function () {

        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`admin?action=${index}`, function (res) {
            console.log(res);
            new Vue({
                el: '#adm_home',
                data: {
                    customers: []
                },
                created: function () {
                    const me = this;
                    me.customers = res;
                }
            });
        });
    }, 1000);

};

/*
 *
 */
admin.processIndex3 = function () { //users
    var index = 3;
    var page = adminPages[index];
    loadTemplate(page);

    setTimeout(function () {
        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`admin?action=${index}`, function (res) {
            console.log(res);
            new Vue({
                el: '#adm_home',
                data: {
                    users: []
                },
                created: function () {
                    const me = this;
                    me.users = res;
                }
            });
        });
    }, 1000);

};

/*
 *
 */
admin.processIndex4 = function () { //Manage
    var index = 4;
    var page = adminPages[index];
    loadTemplate(page);

    setTimeout(function () {
        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`admin?action=${index}`, function (res) {
            console.log(res);
            new Vue({
                el: '#adm_home',
                data: {
                    users: []
                },
                created: function () {
                    const me = this;
                }
            });
        });
    }, 1000);

};

/*
 *
 */
admin.processIndex5 = function () { //Profile
    var index = 5;
    var page = adminPages[index];
    loadTemplate(page);
};
