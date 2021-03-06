<%--
    Document   : balance
    Created on : Sep 3, 2020, 10:14:04 AM
    Author     : Aviator
--%>

<%@page import="com.banking.entities.Customers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.banking.AppEnum"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customers cm = (Customers) request.getSession().getAttribute(AppEnum.LOGGED_IN_USER.getName());
%>
<!DOCTYPE html>
<div class="row" id="balanceView">
    <div class="col-md-4">
        <button class="btn btn-info btn-circle" type="button" onclick="getTemplate(0)">
            <i class="fa fa-arrow-left"></i>
        </button>
    </div>
    <div class="col-md-4">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Balance</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="no-margins">KES {{bal}}</h1>
                        <small>Balance <%=new SimpleDateFormat("MMM dd, yyyy h:m:s").format(new Date())%></small>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4"></div>
</div>
