<%--
    Document   : account
    Created on : Sep 3, 2020, 9:15:10 AM
    Author     : Aviator
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.banking.AppEnum"%>
<%@page import="com.banking.models.CustomerModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<%
    CustomerModel cm = (CustomerModel) request.getSession().getAttribute(AppEnum.LOGGED_IN_USER.getName());
%>
<div class="row m-b-lg m-t-lg">
    <div class="col-md-3">
        <button class="btn btn-info btn-circle" type="button" onclick="getTemplate(0)">
            <i class="fa fa-arrow-left"></i>
        </button>
    </div>
    <div class="col-md-6">
        <div class="profile-image">
            <img src="img/05.png" class="rounded-circle circle-border m-b-md" alt="profile">
        </div>
        <div class="profile-info">
            <div class="">
                <div>
                    <h2 class="no-margins"><%= cm.getCt_fname() + " " + cm.getCt_lname()%></h2>
                    <h4><small>Member since: </small><%=new SimpleDateFormat("MMM dd, YYYY").format(DateFormat.getDateInstance().parse(cm.getCt_date()))%></h4>
                    <small><%= cm.getCt_gender()%></small>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-3"> </div>
</div>

<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Personal Information<small></small></h5>
                <div class="ibox-tools"> </div>
            </div>
            <div class="ibox-content">
                <form onsubmit="return false">
                    <div class="form-group  row">
                        <label class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10"><input type="text" class="form-control" value="<%= cm.getCt_email()%>" readonly=""></div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group  row">
                        <label class="col-sm-2 col-form-label">Phone</label>
                        <div class="col-sm-10"><input type="text" class="form-control" value="<%= cm.getCt_phone()%>" readonly=""></div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group  row">
                        <div class="col-sm-6">
                            <label class="col-sm-2 col-form-label">Country</label>
                            <input type="text" class="col-sm-10 form-control" value="<%= cm.getCt_country()%>" readonly="">
                        </div>
                        <div class="col-sm-6">
                            <label class="col-sm-2 col-form-label">City</label>
                            <input type="text" class="col-sm-10 form-control" value="<%= cm.getCt_city()%>" readonly="">
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group  row">
                        <label class="col-sm-2 col-form-label">Address</label>
                        <div class="col-sm-10"><input type="text" class="form-control" value="<%= cm.getCt_address()%>" readonly=""></div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group row"><label class="col-sm-2 col-form-label">Help text</label>
                        <div class="col-sm-10"><input type="text" class="form-control">
                            <span class="form-text m-b-none">A block of help text that breaks onto a new line and may extend beyond one line.</span>
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-3"></div>
</div>
<div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Account Information</h5>
                <div class="ibox-tools"></div>
            </div>
            <div class="ibox-content">
                <form onsubmit="return false">
                    <p>Your bank account details</p>
                    <div class="form-group row has-success">
                        <label class="col-lg-2 col-form-label">Account Type</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" value="<%= cm.getCt_accounttype()%>" readonly="">
                            <span class="form-text m-b-none"></span>
                        </div>
                    </div>
                    <div class="form-group row has-error">
                        <label class="col-lg-2 col-form-label">Account Number</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" value="<%= cm.getCt_accountnumber()%>" readonly="">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-lg-2 col-form-label">Current Access Code</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control" value="<%= cm.getCt_accesscode()%>" readonly="">
                            <span class="form-text m-b-none">Changes every time you log in to ensure your account security</span>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-3"></div>
</div>
