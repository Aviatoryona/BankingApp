<%--
    Document   : transactions
    Created on : Sep 13, 2020, 8:54:27 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="p-w-md m-t-sm  animated fadeInDown" id="adm_home">
    <div class="row">
        <div class="col-lg-6"></div>
        <div class="col-lg-6">
            <div class="input-group">
                From: <input placeholder="From" type="date" class="form-control form-control-sm">
                To: <input placeholder="To" type="date" class="form-control form-control-sm">
                <span class="input-group-append">
                    <button type="button" class="btn btn-sm btn-primary">Go!</button>
                </span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Transactions</h5>
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