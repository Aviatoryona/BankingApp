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
 *DO NOT CHANGE ORDER OF INDEXES
 */
var pages = {
    0: "includes/home_dashboard.jsp",
    1: "includes/account.jsp",
    2: "includes/balance.jsp",
    3: "includes/transactions.jsp",
    4: "includes/deposit.jsp",
    5: "includes/withdraw.jsp"
};

/*
 *
 * @returns {undefined}
 */
app.processTemplate = function () {
    /*
     *
     *
     */
    var index0 = this.index;
    var page = pages[index0];
    var mail = $('.container').attr('data');
    templatePlaceHolder(function () {
        app.loadTemplate.call({
            dataUrl: page,
            method: 'GET',
            isJson: false,
            params: null,
            callBack: function (data) {
                $('.container').html(data);

                /*
                 * call this function to load and bind data to loaded template
                 */
                app.processIndex.call({index: index0, email: mail});

            }
        });
    });

};

/*
 *
 * @returns {undefined}
 */
function templatePlaceHolder(callBack = null) {
    $('.container').html(`<div class="middle-box text-center loginscreen animated fadeInDown">
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
function getTemplate(tmp_index) {
    app.processTemplate.call({index: tmp_index});
}

/*
 * Default home
 */
app.processTemplate.call({index: 0});