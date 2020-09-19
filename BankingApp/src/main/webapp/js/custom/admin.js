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
    5: 'admin/profile.jsp',
    6: 'admin/client-details.jsp',
    7: 'admin/user-details.jsp',
};

function showValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).addClass('has-error');
}

function hideValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).removeClass('has-error');
}

function validate(input) {
    if ($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
        if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
            return false;
        }
    } else {
        if ($(input).val().trim() == '') {
            return false;
        }
    }
}

function validateData(isEmail, data) {
    if (isEmail) {
        if ((data.trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/)) == null) {
            return false;
        }
    } else {
        if ((data.trim()) == '') {
            return false;
        }
    }
}

/*
 *
 * @returns {undefined}
 */
admin.dtTables = function () {
    $('.dataTables-example').DataTable({
        pageLength: 25,
        responsive: true
    });
};

/*
 *
 * @returns {undefined}
 */
admin.auth = function () {
    var usr = $('input[type="email"]').val();
    var pwd = $('input[type="password"]').val();
    if (usr == '') {
        showValidate('input[type="email"]');
        return;
    }
    if (pwd == '') {
        showValidate('input[type="password"]');
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
                    admin.dtTables();
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
                    admin.dtTables();
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
                    countries: [],
                    acctypes: [],
                    trtyps: []
                },
                created: function () {
                    const me = this;
                    me.countries = res.countries;
                    me.acctypes = res.acctypes;
                    me.trtyps = res.trtyps;
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

/*
 *
 */
admin.addAccountType = function () {
    var acctype = $('input[name="acctype"]').val();
    if (acctype == '') {
        showValidate('input[name="acctype"]');
        return;
    }
    var accmaxbal = $('input[name="accmaxbal"]').val();
    if (accmaxbal == '') {
        showValidate('input[name="accmaxbal"]');
        return;
    }
    var accminbal = $('input[name="accminbal"]').val();
    if (accminbal == '') {
        showValidate('input[name="accminbal"]');
        return;
    }
    var accdescription = $('textarea[name="accdescription"]').val();
    if (accdescription == '') {
        showValidate('textarea[name="accdescription"]');
        return;
    }

//    var data = $('form[id="miform"]').serialize();
    $.post('admin', {
        q: 'addacctype'
    }, function (data) {
        if (data.success) {
            $('input[name="acctype"]').val('');
            $('input[name="accmaxbal"]').val('');
            $('input[name="accminbal"]').val('');
            $('textarea[name="accdescription"]').val('');
            swal({
                title: "Done",
                text: data.message,
                type: "success"
            });
            setTimeout(function () {
                admin.processIndex4.call();
            }, 500);
        } else {
            swal({
                title: "Failed",
                text: data.message,
                type: "error"
            });
        }
    });
    app.loadData.call({
        dataUrl: "",
        method: 'POST',
        isJson: true,
        params: `q=&acctype=${acctype}&accmaxbal=${accmaxbal}&accminbal=${accminbal}&accdescription=${accdescription}`,
        callBack: function (data) {

        }
    });

};

/*
 *
 */
admin.addUser = function () {
    /*==================================================================
     [ Validate ]*/
    var input = $('form input');
    var check = true;
    for (var i = 0; i < input.length; i++) {
        if (validate(input[i]) == false) {
            showValidate(input[i]);
            check = false;
        }
    }
    if (check) {
        var vals = $('form').serialize();
        console.log(vals);

        app.loadData.call({
            dataUrl: "admin",
            method: 'POST',
            isJson: true,
            params: vals,
            callBack: function (data) {
                if (data.success) {
                    swal({
                        title: "Done",
                        text: data.message,
                        type: "success"
                    });
                    setTimeout(function () {
                        admin.processIndex3.call();
                    }, 500);
                } else {
                    swal({
                        title: "Failed",
                        text: data.message,
                        type: "error"
                    });
                }
            }
        });
    }
};

/*
 *
 */
admin.addCountry = function () {
    var ctryName = $('input[name="ctryName"]').val();
    if (ctryName == '') {
        showValidate('input[name="ctryName"]');
        return;
    }

    app.loadData.call({
        dataUrl: "admin",
        method: 'POST',
        isJson: true,
        params: `q=addctry&ctryName=${ctryName}`,
        callBack: function (data) {
            if (data.success) {
                $('input[name="ctryName"]').val('');
                swal({
                    title: "Done",
                    text: data.message,
                    type: "success"
                });
                setTimeout(function () {
                    admin.processIndex4.call();
                }, 500);
            } else {
                swal({
                    title: "Failed",
                    text: data.message,
                    type: "error"
                });
            }
        }
    });

};

/*
 *
 */
admin.processIndex6 = function (parent) {//client details

    var usr_email = $.trim($(parent).text());

    console.log(usr_email);

    var index = 6;
    var page = adminPages[index];
    loadTemplate(page);

    setTimeout(function () {
        $.getJSON(`admin?action=${index}&email=${usr_email}`, function (customer) {
            new Vue({
                el: "#adm_home",
                data: {
                    fname: customer.clientUserSd.ctFname,
                    lname: customer.clientUserSd.ctLname,
                    rdate: customer.clientUserSd.ctDate,
                    accno: customer.ctAccountnumber,
                    gender: customer.ctGender,
                    email: customer.clientUserSd.ctEmail,
                    phone: customer.clientUserSd.ctPhone,
                    country: customer.ctCountry,
                    city: customer.ctCity,
                    address: customer.ctAddress,
                    acctype: customer.ctAccounttype
                }
            });
        });
    }
    , 500);
};

/*
 *
 */
admin.processIndex7 = function (parent) {//user details
    var id = $.trim($(parent).attr('data'));
    var index = 7;
    var page = adminPages[index];
    loadTemplate(page);

    setTimeout(function () {
        $.getJSON(`admin?action=${index}&id=${id}`, function (customer) {
            new Vue({
                el: "#adm_home",
                data: {
                    name: customer.clientUserSd.ctFname + ' ' + customer.clientUserSd.ctLname,
                    email: customer.clientUserSd.ctEmail,
                    phone: customer.clientUserSd.ctPhone,
                    username: customer.usrUsername,
                    pwd: customer.usrPwd
                }
            });
        });
    }
    , 500);
};

/*
 *
 */
admin.updateUserUsername = function () {
    var email = $('input[name="txtEmail"]').val();
    var txtUsrName = $('input[name="txtUsrName"]').val();
    if (!validateData(false, txtUsrName)) {
        showValidate('input[name="txtUsrName"]');
        return;
    }
    $.ajax({
        url: "admin",
        type: 'PUT',
        data: {
            'usrname': txtUsrName,
            'email': email
        },
        beforeSend: function (xhr) {
            xhr.overrideMimeType("text/plain; charset=x-user-defined");
        }
    }).done(function (data) {

    });
};

admin.test = function () {
    // capture and pass your form data in this params object
    var params = {
        name: 'name',
        email: 'email'
    };

    //send data to server
    // endpointurl should be http://localhost:8080/....
    //If it fails, check how to set application/json header and making cross origin request
    $.post('endpointurl', {
        data: JSON.stringify(params)
    }, function (response) {
        console.log(response);
    });
};