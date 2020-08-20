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

var app = {};

///////http request
app.loadData = function () {
    var me = this;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                me.data = eval('(' + xhr.responseText + ')');
//                console.log(me.data);
                me.callBack(me.data);
            }
        }
    };

    xhr.open(me.method, me.dataUrl, true);
    xhr.send();
};

//called when register.html is loaded
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