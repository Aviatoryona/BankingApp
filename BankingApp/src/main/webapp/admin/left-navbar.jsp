<%--
    Document   : left-navbar
    Created on : Sep 8, 2020, 5:25:42 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar-collapse">
    <ul class="nav metismenu" id="side-menu">
        <li class="nav-header">
            <div class="dropdown profile-element">
                <img alt="image" class="rounded-circle" src="img/05.png"/>
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                    <span class="block m-t-xs font-bold"><%=cm.getUsrUsername()%></span>
                    <span class="text-muted text-xs block">Admin <b class="caret"></b></span>
                </a>
                <ul class="dropdown-menu animated fadeInRight m-t-xs">
                    <li><a class="dropdown-item" href="javascript:void(0)">Profile</a></li>
                    <li><a class="dropdown-item" href="javascript:void(0)">Activity</a></li>
                    <li><a class="dropdown-item" href="javascript:void(0)">Mailbox</a></li>
                    <li class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="admin?action=-1">Logout</a></li>
                </ul>
            </div>
            <div class="logo-element"> </div>
        </li>

        <li class="active">
            <a href="javascript:void(0)" onclick="admin.processIndex0()"><i class="fa fa-dashboard"></i> <span class="nav-label">Home</span></a>
        </li>
        <li>
            <a href="javascript:void(0)" onclick="admin.processIndex2()"><i class="fa fa-users"></i> <span class="nav-label">Clients</span></a>
        </li>
        <li>
            <a href="javascript:void(0)" onclick="admin.processIndex1()"><i class="fa fa-bar-chart"></i> <span class="nav-label">Transactions</span></a>
        </li>
        <li>
            <a href="javascript:void(0)" onclick="admin.processIndex3()"><i class="fa fa-user-circle"></i> <span class="nav-label">Users</span></a>
        </li>
        <li>
            <a href="javascript:void(0)" onclick="admin.processIndex4()"><i class="fa fa-cogs"></i> <span class="nav-label">Manage</span></a>
        </li>
        <li class="special_link">
            <a href="admin?action=-1"><i class="fa fa-sign-out"></i> <span class="nav-label">Log Out</span></a>
        </li>
    </ul>

</div>