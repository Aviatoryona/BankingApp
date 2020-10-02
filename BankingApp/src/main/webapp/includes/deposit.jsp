<%--
    Document   : withdraw
    Created on : Sep 3, 2020, 10:39:09 AM
    Author     : Aviator
--%>

<%@page import="com.banking.entities.Customers"%>
<%@page import="com.banking.AppEnum"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customers cm = (Customers) request.getSession().getAttribute(AppEnum.LOGGED_IN_USER.getName());
//    TransactionType transactionType = TransactionTypeLogic.getInstance(dbConnection).getTransactionType(AppEnum.WITHDRAW.getName());
%>
<!DOCTYPE html>
<div class="row">
    <div class="col-lg-4">
        <button class="btn btn-info btn-circle" type="button" onclick="getTemplate(0)">
            <i class="fa fa-arrow-left"></i>
        </button>
    </div>
    <div class="col-lg-4">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Deposit</h5>
                <div class="ibox-tools"></div>
            </div>
            <div class="ibox-content">
                <div class="mismsg"></div>
                <form role="form" onsubmit="return false">
                    <div class="form-group">
                        <label for="amntTxt" class="sr-only">Amount</label>
                        <input type="number"  id="amntTxt" max="300000" min="1000" maxlength="6" minlength="4"  data="<%= cm.getClientUserSd().getCtEmail()%>"  class="form-control">
                    </div>
                    <button class="btn btn-white" type="submit" onclick="doDeposit()">Process</button>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-4"></div>
</div>
