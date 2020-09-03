<%--
    Document   : home_dashboard
    Created on : Aug 21, 2020, 12:04:11 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-md-2">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Deposits</h5>
            </div>
            <div class="ibox-content">
                <h1 class="no-margins">386,200</h1>
                <small>Total deposits</small>
            </div>
        </div>
    </div>
    <div class="col-md-2">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Withdrawals</h5>
            </div>
            <div class="ibox-content">
                <h1 class="no-margins">80,800</h1>
                <small>Total withdrawals</small>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Balance</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="no-margins">KES 406,420</h1>
                        <small>Acc. Balance</small>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Account</h5>
                <div class="ibox-tools">
                    <span class="label label-primary">21.08.2020</span>
                </div>
            </div>
            <div class="ibox-content no-padding">
                <ul>
                    <li><a href="">Withdraw</a></li>
                    <li><a href="">Deposit</a></li>
                    <li><a href="">Balance</a></li>
                    <li><a href="">Settings</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Recent Transactions</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-9 m-b-xs"></div>
                    <div class="col-sm-3">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control form-control-sm" placeholder="Search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="button">Go!</button>
                            </div>
                        </div>
                    </div>
                </div>
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
                            <tr>
                                <td>1</td>
                                <td>Deposit</td>
                                <td>2000</td>
                                <td>10</td>
                                <td>Jul 14, 2020</td>
                                <td><a href="javascript:void(0)"><i class="fa fa-check text-navy"></i></a></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>Withdrawal</td>
                                <td>2000</td>
                                <td>10</td>
                                <td>Jul 14, 2020</td>
                                <td><a href="javascript:void(0)"><i class="fa fa-check text-navy"></i></a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
