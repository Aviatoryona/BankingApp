<%--
    Document   : home_dashboard
    Created on : Aug 21, 2020, 12:04:11 PM
    Author     : Aviator
--%>

<%@page import="javax.ejb.EJB"%>
<%@page import="com.banking.entities.Transactions"%>
<%@page import="com.banking.entities.Customers"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="com.banking.AppEnum"%>
<%@page import="com.banking.models.MessageModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customers cm = (Customers) request.getSession().getAttribute(AppEnum.LOGGED_IN_USER.getName());
%>
<div id="home">
    <div class="row" id="row1">
        <div class="col-md-3">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Deposits</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins" id="el_deposit">{{el_deposit}}</h1>
                    <small>Total deposits(Kes)</small>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Withdrawals</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins" id="el_withdrawal">{{el_withdrawal}}</h1>
                    <small>Total withdrawals(Kes)</small>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Balance</h5>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="no-margins" id="el_balance"><%= cm.getCtAccbalance()%></h1>
                            <small>Acc. Balance(Kes)</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Account</h5>
                    <div class="ibox-tools">
                        <span class="label label-primary">
                            <%=new SimpleDateFormat("dd.MM.YYYY").format(new Date())%>
                        </span>
                    </div>
                </div>
                <div class="ibox-content no-padding">
                    <ul>
                        <li><a href="javascript:void(0)" onclick="getTemplate(5)">Withdraw</a></li>
                        <li><a href="javascript:void(0)" onclick="getTemplate(4)">Deposit</a></li>
                        <li><a href="javascript:void(0)" onclick="getTemplate(2)">Balance</a></li>
                        <li><a href="javascript:void(0)" onclick="getTemplate(3)">Transactions</a></li>
                        <li><a href="javascript:void(0)" onclick="getTemplate(1)">My Account</a></li>
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
    </div>
</div>