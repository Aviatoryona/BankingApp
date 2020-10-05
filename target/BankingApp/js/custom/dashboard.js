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
 */
$(document).ready(function () {
    $('input').click(function () {
        $('input').css('border', '');
    });
});
/*
 *
 * @returns {app.processIndex}
 */
app.processIndex = function () {
    var index = this.index;
    var email = this.email;
    switch (index) {
        case 0:
            process_home_dashboard(email);
            break;
        case 1:
            break;
        case 2:
            process_balance(email);
            break;
        case 3:
            process_get_all_transactions(email);
            break;
    }

};
/*
 *
 * @returns {undefined}
 */
function process_home_dashboard(email) {
    $.getJSON(`${BASE_URL}/customers/index/${email}`, function (res) {
        new Vue({
            el: '#home',
            data: {
                el_deposit: res.object.deposit,
                el_withdrawal: res.object.withdraw,
                transactions: []
            },
            created: function () {
                let cobject = this; // here stored currect instance
                cobject.transactions = res.object.transactions;
            }
        });
    });
}

/*
 *
 * @returns {undefined}
 */
function process_get_all_transactions(email) {
    $.getJSON(`${BASE_URL}/transactions/getTransactions/${email}`, function (res) {
        new Vue({
            el: '#tr_table',
            data: {
                transactions: []
            },
            created: function () {
                this.transactions = res;
            }
        });
    });
}

/*
 *
 * @returns {undefined}
 */
function process_balance(email) {
    $.post(`${BASE_URL}/customers/getBalance/${email}`, function (data) {
        new Vue({
            el: '#balanceView',
            data: {
                bal: data
            }
        });
    });
}

/*
 *
 * @returns {undefined}
 */
function doWithdraw() {
    var amntTxt = document.getElementById('amntTxt').value;
    if (amntTxt == '') {
        $('#amntTxt').css('border', '1px solid red');
        return;
    }
    var mail = $('#amntTxt').attr('data');
    jQuery.ajax({
        url: `${BASE_URL}/customers/withdraw/${mail}/${amntTxt}`,
        type: "POST",
        data: ``,
        processData: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data, textStatus, jqXHR) {
            if (data.success) {
                swal({
                    title: "Transaction Successful",
                    text: data.message,
                    type: "success"
                });
                setTimeout(function () {
                    getTemplate(2); //display balance after a successful transaction
                }, 5000);
            } else {
                swal({
                    title: "Transaction Failed",
                    text: data.message,
                    type: "error"
                });
            }
        }
    });
}

/*
 *
 * @returns {undefined}
 */
function doWithdraw_1() {
    var amntTxt = document.getElementById('amntTxt').value;
    if (amntTxt == '') {
        $('#amntTxt').css('border', '1px solid red');
        return;
    }

    app.loadData.call({
        dataUrl: "dashboard",
        method: 'POST',
        isJson: false,
        params: "q=w&amt=" + amntTxt,
        callBack: function (data) {
            if (data.success) {
                swal({
                    title: "Transaction Successful",
                    text: data.message,
                    type: "success"
                });
                setTimeout(function () {
                    getTemplate(0); //display balance after a successful transaction
                }, 5000);
            } else {
                swal({
                    title: "Transaction Failed",
                    text: data.message,
                    type: "error"
                });
            }

        }
    }
    );
}

/*
 *
 * @returns {undefined}
 */
function doDeposit() {
    var amntTxt = document.getElementById('amntTxt').value;
    if (amntTxt == '') {
        $('#amntTxt').css('border', '1px solid red');
        return;
    }
    var mail = $('#amntTxt').attr('data');
    jQuery.ajax({
        url: `${BASE_URL}/customers/deposit/${mail}/${amntTxt}`,
        type: "POST",
        data: ``,
        processData: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data, textStatus, jqXHR) {
            if (data.success) {
                swal({
                    title: "Transaction Successful",
                    text: data.message,
                    type: "success"
                });
                setTimeout(function () {
                    getTemplate(2); //display balance after a successful transaction
                }, 5000);
            } else {
                swal({
                    title: "Transaction Failed",
                    text: data.message,
                    type: "error"
                });
            }
        }
    });
}

/*
 *
 */
function doDeposit1() {
    var amntTxt = document.getElementById('amntTxt').value;
    if (amntTxt == '') {
        $('#amntTxt').css('border', '1px solid red');
        return;
    }
    app.loadData.call({
        dataUrl: "dashboard",
        method: 'POST',
        isJson: true,
        params: "q=d&amt=" + amntTxt,
        callBack: function (data) {
//            console.log(data);
            if (data.success) {
                swal({
                    title: "Transaction Successful",
                    text: data.message,
                    type: "success"
                });
                setTimeout(function () {
                    getTemplate(0); //display balance after a successful transaction
                }, 5000);
            } else {
                swal({
                    title: "Transaction Failed",
                    text: data.message,
                    type: "error"
                });
            }

        }
    });
}

