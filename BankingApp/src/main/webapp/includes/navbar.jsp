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
    CustomerModel cm = null;
    Cookie[] cookies = request.getCookies();
    for (int i = 0; i < cookies.length; i++) {
        Cookie cookie1 = cookies[0];
        if (cookie1.getName().equalsIgnoreCase(AppEnum.LOGGED_IN_USER.getName())) {
            String s = cookie1.getValue();
            cm = new ObjectMapper().convertValue(s, CustomerModel.class);
        }
    }
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
                    <a href="login.html">
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
