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

//do not change USED DVLPMT FOR REFERENCE
var admin = {};

/*
 * @type type
 */
var adminPages = {
    0: 'admin/home-welcome.jsp',
    1: 'admin/transactions.jsp',
    2: 'admin/clients.jsp',
    3: 'admin/users.jsp',
    4: 'admin/manage.jsp',
    5: 'admin/profile.jsp',
    6: 'admin/client-details.jsp',
    7: 'admin/user-details.jsp',
    8: 'includes/placeholder.html',
};

/*
 *
 * @param {type} input
 * @returns {undefined}
 */
function showValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).addClass('has-error');
}

/*
 *
 * @param {type} input
 * @returns {undefined}
 */
function hideValidate(input) {
    var thisAlert = $(input).parent();
    $(thisAlert).removeClass('has-error');
}

/*
 *
 * @param {type} input
 * @returns {Boolean}
 */
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

/*
 *
 * @param {type} isEmail
 * @param {type} data
 * @returns {Boolean}
 */
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

    return true;
}

/*
 *
 * @returns {undefined}
 */
admin.dtTables = function () {
    $('.dataTables-example').footable();//.DataTable();
};

/*
 *
 * @returns {undefined}
 */
admin.auth = function () {
    var usr = $('input[type="text"]').val();
    var pwd = $('input[type="password"]').val();
    if (usr == '') {
        showValidate('input[type="text"]');
        return;
    }
    if (pwd == '') {
        showValidate('input[type="password"]');
        return;
    }


    /*
     * @param {type} page
     * @returns {undefined}
     */
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
 * @param {type} page
 * @returns {undefined}
 */
function loadTemplate(page, callBack = null) {
    app.loadTemplate.call({
        dataUrl: page,
        method: 'POST',
        isJson: false,
        params: null,
        callBack: function (data) {
            $('#mi_content').html(data);
            if (callBack != null)
                callBack();
        }
    });
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
 *
 */
admin.processIndex0 = function () {  //home
    var index = 0;
    var page = adminPages[index];
    setPlaceholder(function () {
        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`${BASE_URL}/admin/`, function (res) {
            console.log(res);
            loadTemplate(page, function () {
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

        });
    });

};

/*
 *
 */
admin.processIndex1 = function () { //transactions
    var index = 1;
    var page = adminPages[index];
    setPlaceholder(function () {

        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`${BASE_URL}/admin/getLimitedTransactions/-1`, function (res) {
            console.log(res);
            loadTemplate(page, function () {
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
        });
    });

};

/*
 *
 */
admin.processIndex2 = function () { //clients
    var index = 2;
    var page = adminPages[index];
    setPlaceholder(function () {

        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`${BASE_URL}/admin/getRegisteredCustomers/-1`, function (res) {
            console.log(res);
            loadTemplate(page, function () {
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
        });
    });

};

/*
 *
 */
admin.processIndex3 = function () { //users
    var index = 3;
    var page = adminPages[index];
    setPlaceholder(function () {
        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`${BASE_URL}/admin/getUsers`, function (res) {
            console.log(res);
            loadTemplate(page, function () {
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
        });
    });

};

/*
 *
 */
admin.processIndex4 = function () { //Manage
    var index = 4;
    var page = adminPages[index];
    setPlaceholder(function () {
        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`admin?action=${index}`, function (res) {
            console.log(res);
            loadTemplate(page, function () {
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
        });
    });

};

/*
 *
 */
admin.processIndex5 = function () { //Profile
    var index = 5;
    var page = adminPages[index];
    setPlaceholder(function () {
        loadTemplate(page);
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
    setPlaceholder(function () {
        $.getJSON(`${BASE_URL}/admin/getCustomer/${usr_email}`, function (customer) {
            setTimeout(function () {
                $.getJSON(`${BASE_URL}/transactions/getTransactions/${usr_email}`, function (res) {
                    loadTemplate(page, function () {
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
                                acctype: customer.ctAccounttype,
                                transactions: []
                            },
                            created: function () {
                                this.transactions = res;
                                $('.dataTables-example').DataTable();
                            }
                        });
                    });
                });
            }, 300);

        });
    });
};

/*
 *
 */
admin.processIndex7 = function (parent) {//user details
    var id = $.trim($(parent).attr('data'));
    var index = 7;
    var page = adminPages[index];
    setPlaceholder(function () {
        $.getJSON(`${BASE_URL}/admin/getUser/${id}`, function (customer) {
            loadTemplate(page, function () {
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
        });
    });
};

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
//    console.log(data);
    /*
     *
     * @type type
     */
    var obj = {
        url: `${BASE_URL}/accounttypes/create`,
        params: {
            'acctype': acctype,
            'accmaxbal': accmaxbal,
            'accminbal': accminbal,
            'accdescription': accdescription,
            'accdate': new Date()
        },
        bfor: function () {
        },
        afta: function (data) {
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
        }
    };
    app.submitForm.call(obj);
};

/*
 *
 */
admin.addUser = function () {
    /*==================================================================
     [ Validate ]*/
    var input = $('#miform .user');
    var check = true;
    console.log(input.length);
    for (var i = 0; i < input.length; i++) {
        if (validate(input[i]) == false) {
            showValidate(input[i]);
            check = false;
        }
    }

    if (check) {
//        var vals = $('#miform').serialize();
//        console.log(vals);

        var vals = {
            "usrUsername": $('input[name="usrUsername"]').val(),
            "usrPwd": $('input[name="usrPwd"]').val(),
            "usrRole": 1,
            "usrStatus": 1,
            clientUserSd: {
                'ctFname': $('input[name="ctFname"]').val(),
                'ctLname': $('input[name="ctLname"]').val(),
                'ctEmail': $('input[name="ctEmail"]').val(),
                'ctPhone': $('input[name="ctPhone"]').val(),
                'ctPic': '',
                'ctDate': new Date()
            }
        };

        /*
         *
         * @type type
         */
        var obj = {
            url: `${BASE_URL}/users/addUser`,
            params: vals,
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
        };
        app.submitForm.call(obj);
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

    /*
     *
     * @type type
     */
    var obj = {
        url: `${BASE_URL}/countries/addCountry`,
        params: {
            ctryName: `${ctryName}`
        },
        bfor: function () {
        },
        afta: function (data) {
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
    };
    app.submitForm.call(obj);
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
            'q': 'usrname',
            'usrname': txtUsrName,
            'email': email
        },
        beforeSend: function (xhr) {
            xhr.overrideMimeType("text/plain; charset=x-user-defined");
        }
    }).done(function (res) {
        var data = JSON.parse(res);
        if (data.success) {
            swal({
                title: "Successful",
                text: data.message,
                type: "success"
            });
        } else {
            swal({
                title: "Failed",
                text: data.message,
                type: "error"
            });
        }
    });
};

/*
 *
 */
admin.updateUserPassword = function () {
    var email = $('input[name="txtEmail"]').val();
    var txtPwd = $('input[name="txtPwd"]').val();
    if (!validateData(false, txtPwd)) {
        showValidate('input[name="txtPwd"]');
        return;
    }
    $.ajax({
        url: "admin",
        type: 'PUT',
        data: {
            'q': 'pwd',
            'pwd': txtPwd,
            'email': email
        },
        beforeSend: function (xhr) {
            xhr.overrideMimeType("text/plain; charset=x-user-defined");
        }
    }).done(function (res) {
        var data = JSON.parse(res);
        if (data.success) {
            swal({
                title: "Successful",
                text: data.message,
                type: "success"
            });
        } else {
            swal({
                title: "Failed",
                text: data.message,
                type: "error"
            });
        }
    });
};

/*
 *
 */
admin.searchClients = function () {
    var q = $('#search_clients').val();
    if (q == '')
        return;

    var index = 2;
    var page = adminPages[index];
    setPlaceholder(function () {

        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`${BASE_URL}/customers/search/${q}`, function (res) {
            console.log(res);
            loadTemplate(page, function () {
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
        });
    });
};

/*
 *
 */
admin.searchTransaction = function () {
    var inp_from = $('#inp_from').val();
    var inp_to = $('#inp_to').val();

    if (inp_from == '')
        return;
    if (inp_to == '')
        inp_to = new Date();

    var index = 1;
    var page = adminPages[index];
    setPlaceholder(function () {

        /*
         * call this function to load and bind data to loaded template
         */
        $.getJSON(`${BASE_URL}/admin/getTransactions/-1/${inp_from}/${inp_to}`, function (res) {
            console.log(res);
            loadTemplate(page, function () {
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
        });
    });
};
