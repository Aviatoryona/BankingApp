<%--
    Document   : home-welcome
    Created on : Sep 8, 2020, 5:26:43 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="p-w-md m-t-sm" id="adm_home">
    <div class="row">
        <div class="col-sm-4">
            <h1 class="m-b-xs">
                {{total_clients}}
            </h1>
            <small>
                Clients
            </small>
            <div id="sparkline1" class="m-b-sm"></div>

        </div>
        <div class="col-sm-4">
            <h1 class="m-b-xs">
                {{total_transactions}}
            </h1>
            <small>
                Transactions
            </small>
            <div id="sparkline2" class="m-b-sm"></div>
        </div>
        <div class="col-sm-4">
            <div class="row m-t-xs">
                <div class="col-6">
                    <h5 class="m-b-xs">Income today</h5>
                    <h1 class="no-margins">160,000</h1>
                    <div class="font-bold text-navy">10% <i class="fa fa-bolt"></i></div>
                </div>
                <div class="col-6">
                    <h5 class="m-b-xs">Total Income</h5>
                    <h1 class="no-margins">42,120</h1>
                    <div class="font-bold text-navy">58% <i class="fa fa-bolt"></i></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top: 50px">
        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>Clients</h5>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <tbody>
                                <tr v-for="customer in customers">
                                    <td>{{customer.ctFname}} {{customer.ctLname}}</td>
                                    <td><a href="#">{{customer.ctEmail}}</a></td>
                                    <td>{{customer.ctPhone}}</td>
                                    <td>{{customer.ctAddress}}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>

        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>Transactions</h5>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Transaction Type</th>
                                    <th>Amount </th>
                                    <th>Fee </th>
                                    <th>Date </th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="transaction in transactions">
                                    <td>{{transaction.trId}}</td>
                                    <td>{{transaction.trType}}</td>
                                    <td>{{transaction.trAmount}}</td>
                                    <td>{{transaction.trCharge}}</td>
                                    <td>{{transaction.trDate}}</td>
                                    <td><a href="javascript:void(0)"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>