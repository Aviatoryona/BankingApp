<%--
    Document   : home_dashboard
    Created on : Aug 21, 2020, 12:04:11 PM
    Author     : Aviator
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="com.banking.models.TransactionModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="com.banking.AppEnum"%>
<%@page import="com.banking.models.CustomerModel"%>
<%@page import="com.banking.logic.DashboardLogic"%>
<%@page import="com.banking.models.MessageModel"%>
<%@page import="com.banking.db.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DbConnection dbConnection = (DbConnection) getServletContext().getAttribute("dbConnection");
    CustomerModel cm = (CustomerModel) request.getSession().getAttribute(AppEnum.LOGGED_IN_USER.getName());
    MessageModel messageModel = DashboardLogic.getInstance(dbConnection).processIndexHome(cm);
    Map<String, Object> map = (Map<String, Object>) messageModel.getObject();

    List<TransactionModel> models = (List<TransactionModel>) map.get(AppEnum.TRANSACTIONS.getName());
%>
<div class="row">
    <div class="col-md-2">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Deposits</h5>
            </div>
            <div class="ibox-content">
                <h1 class="no-margins"><%= map.get(AppEnum.DEPOSIT.getName())%></h1>
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
                <h1 class="no-margins"><%= map.get(AppEnum.WITHDRAW.getName())%></h1>
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
                        <h1 class="no-margins">KES <%= cm.getCt_accbalance()%></h1>
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
                            <%
                                if (models != null) {
                                    for (TransactionModel model : models) {
                            %>
                            <tr>
                                <td><%= model.getTr_id()%></td>
                                <td><%= model.getTr_type()%></td>
                                <td><%= model.getTr_amount()%></td>
                                <td><%= model.getTr_charge()%></td>
                                <td><%=model.getTr_date()%></td>
                                <td><a href="javascript:void(0)"><i class="fa fa-check text-navy"></i></a></td>
                            </tr>
                            <%
                                    }
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
