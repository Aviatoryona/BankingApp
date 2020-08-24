<%--
    Document   : navbar
    Created on : Aug 21, 2020, 11:59:25 AM
    Author     : Aviator
--%>

<%@page import="com.banking.AppEnum"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.banking.models.CustomerModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (cm != null) {
%>

<div class="row border-bottom white-bg">
    <nav class="navbar navbar-expand-lg navbar-static-top" role="navigation">
        <a href="#" class="navbar-brand"><%=cm.getCt_fname().toUpperCase()%></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fa fa-reorder"></i>
        </button>

        <div class="navbar-collapse collapse" id="navbar">
            <ul class="nav navbar-nav mr-auto">
                <li><a href="">Withdrawals</a></li>
                <li><a href="">Deposits</a></li>
                <li><a href="">Activity</a></li>
                <li><a href="">Account Info</a></li>
            </ul>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <a href="auth?q=logout">
                        <i class="fa fa-sign-out"></i> Log out
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<%
    }
%>
