<%--
    Document   : client-details
    Created on : Sep 15, 2020, 10:58:16 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="p-w-md m-t-sm" id="adm_home">

    <div class="row m-b-lg m-t-lg">
        <div class="col-md-3">
            <button class="btn btn-info btn-circle" type="button" onclick="admin.processIndex2()">
                <i class="fa fa-arrow-left"></i>
            </button>
        </div>
        <div class="col-md-6">
            <div class="profile-image">
                <img src="img/05.png" class="rounded-circle circle-border m-b-md" alt="profile">
            </div>
            <div class="profile-info">
                <div class="">
                    <div>
                        <h2 class="no-margins">{{fname}} {{lname}}</h2>
                        <h4><small>Member since: </small>{{rdate}}</h4>
                        <small>{{accno}} [{{gender}}]</small>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Personal Information<small></small></h5>
                    <div class="ibox-tools"> </div>
                </div>
                <div class="ibox-content">
                    <form onsubmit="return false">
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10"><input type="text" class="form-control" v-bind:value="email" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10"><input type="text" class="form-control" v-bind:value="phone" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <div class="col-sm-6">
                                <label class="col-sm-2 col-form-label">Country</label>
                                <input type="text" class="col-sm-10 form-control" v-bind:value="country" readonly="">
                            </div>
                            <div class="col-sm-6">
                                <label class="col-sm-2 col-form-label">City</label>
                                <input type="text" class="col-sm-10 form-control" v-bind:value="city" readonly="">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Address</label>
                            <div class="col-sm-10"><input type="text" class="form-control" v-bind:value="address" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                    </form>
                </div>
            </div>
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Account Information</h5>
                    <div class="ibox-tools"></div>
                </div>
                <div class="ibox-content">
                    <form onsubmit="return false">
                        <p>Your bank account details</p>
                        <div class="form-group row has-success">
                            <label class="col-lg-2 col-form-label">Account Type</label>
                            <div class="col-lg-10">
                                <input type="text" class="form-control" v-bind:value="acctype" readonly="">
                                <span class="form-text m-b-none"></span>
                            </div>
                        </div>
                        <div class="form-group row has-error">
                            <label class="col-lg-2 col-form-label">Account Number</label>
                            <div class="col-lg-10">
                                <input type="text" class="form-control" v-bind:value="accno" readonly="">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Recent Transactions</h5>
                    <div class="ibox-tools"> </div>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Transaction Type</th>
                                    <th>Amount </th>
                                    <th>Fee </th>
                                    <th>Date </th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="gradeX" v-for="transaction in transactions">
                                    <td>{{transaction.trId}}</td>
                                    <td>{{transaction.trType}}</td>
                                    <td>{{transaction.trAmount}}</td>
                                    <td>{{transaction.trCharge}}</td>
                                    <td>{{new Date(transaction.trDate)}}</td>
                                    <td class="center">
                                        <a href="javascript:void(0)">
                                            <i class="fa fa-check text-navy"></i>
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>#</th>
                                    <th>Transaction Type</th>
                                    <th>Amount </th>
                                    <th>Fee </th>
                                    <th>Date </th>
                                    <th>Status</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>