<%--
    Document   : transactions
    Created on : Sep 3, 2020, 10:23:02 AM
    Author     : Aviator
--%>

<%@page import="com.banking.entities.Transactions"%>
<%@page import="com.banking.entities.Customers"%>
<%@page import="com.banking.AppEnum"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-lg-2">
        <button class="btn btn-info btn-circle" type="button" onclick="getTemplate(0)">
            <i class="fa fa-arrow-left"></i>
        </button>
    </div>
    <div class="col-lg-8">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Transactions</h5>
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
                    <table class="table table-striped" id="tr_table">
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
    <div class="col-lg-2"></div>
</div>
