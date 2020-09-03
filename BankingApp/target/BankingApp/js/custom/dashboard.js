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

$(document).ready(function () {
    $('input').click(function () {
        $('input').css('border', '');
    });
});

/*
 * Used to populate template data at a specified index using GET request
 */
app.processIndex = function () {
    var index = this.index;
    app.loadData.call({
        dataUrl: "dashboard?q=" + index,
        method: 'GET',
        isJson: true,
        params: null,
        callBack: function (data) {

            /*
             * Provide a callback function to call after data loaded
             */
            alert(data.message);
//            if (data.success) {
            console.log(data);
//            }
        }
    });
};

/*
 *
 */
function doWithdraw() {
    var amntTxt = document.getElementById('amntTxt').value;
    if (amntTxt == '') {
        $('#amntTxt').css('border', '1px solid red');
        return;
    }

    app.loadData.call({
        dataUrl: "dashboard",
        method: 'POST',
        isJson: true,
        params: "q=w&amt=" + amntTxt,
        callBack: function (data) {
            console.log(data);
            if (data.success) {
                $('.mismsg').html(`<small style="color:green;">${data.message}</small>`);
                setTimeout(function () {
                    getTemplate(0); //display balance after a successful transaction
                }, 5000);
            } else {
                $('.mismsg').html(`<small style="color:red;">${data.message}</small>`);
                setTimeout(function () {
                    $('.mismsg').html(``);
                }, 5000);
            }

        }
    });
}

/*
 *
 */
function doDeposit() {
    var amntTxt = document.getElementById('amntTxt').value;
    if (amntTxt == '') {
        $('#amntTxt').css('border', '1px solid red');
        return;
    }

    app.loadData.call({
        dataUrl: "dashboard",
        method: 'POST',
        isJson: true,
        params: "q=w&amt=" + amntTxt,
        callBack: function (data) {
            console.log(data);
            if (data.success) {
                $('.mismsg').html(`<small style="color:green;">${data.message}</small>`);
                setTimeout(function () {
                    getTemplate(0); //display balance after a successful transaction
                }, 5000);
            } else {
                $('.mismsg').html(`<small style="color:red;">${data.message}</small>`);
                setTimeout(function () {
                    $('.mismsg').html(``);
                }, 5000);
            }

        }
    });
}