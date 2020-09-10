<%--
    Document   : transactions
    Created on : Sep 3, 2020, 10:23:02 AM
    Author     : Aviator
--%>

<%@page import="com.banking.entities.Transactions"%>
<%@page import="com.banking.entities.Customers"%>
<%@page import="com.banking.AppEnum"%>
<%@page import="com.banking.logic.TranasctionLogic"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customers cm = (Customers) request.getSession().getAttribute(AppEnum.LOGGED_IN_USER.getName());
    List<Transactions> models = new TranasctionLogic().getTransactions(cm);
%>
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
                                    for (Transactions model : models) {
                            %>
                            <tr>
                                <td><%= model.getTrId()%></td>
                                <td><%= model.getTrType()%></td>
                                <td><%= model.getTrAmount()%></td>
                                <td><%= model.getTrCharge()%></td>
                                <td><%=model.getTrDate()%></td>
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
    <div class="col-lg-2"></div>
</div>
