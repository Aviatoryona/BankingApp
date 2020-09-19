<%--
    Document   : profile
    Created on : Sep 14, 2020, 9:56:03 AM
    Author     : Aviator
--%>

<%@page import="com.banking.AppEnum"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.banking.entities.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!Users cm = null;%>

<%

    Enumeration<String> vals = session.getAttributeNames();
    while (vals.hasMoreElements()) {
        String nextElement = vals.nextElement();
        if (nextElement.equalsIgnoreCase(AppEnum.LOGGED_IN_ADMIN.getName())) {
            cm = (Users) session.getAttribute(AppEnum.LOGGED_IN_ADMIN.getName());
        }
    }

    if (cm == null) {
        response.sendRedirect("admin-login.html");
        return;
    }


%>
<div class="p-w-md m-t-sm" id="adm_home">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Account Information<small></small></h5>
                    <div class="ibox-tools"> </div>
                </div>
                <div class="ibox-content">
                    <form onsubmit="return false" name="user-profile">
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">First Name</label>
                            <input type="text" class="col-sm-10 form-control" value="<%= cm.getClientUserSd().getCtFname()%>" readonly="">
                        </div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Last Name</label>
                            <input type="text" class="col-sm-10 form-control" value="<%= cm.getClientUserSd().getCtLname()%>" readonly="">
                        </div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10"><input type="text" name="txtEmail" class="form-control" value="<%= cm.getClientUserSd().getCtEmail()%>" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10"><input type="text" class="form-control" value="<%= cm.getClientUserSd().getCtPhone()%>" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Username</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="txtUsrName" value="<%= cm.getUsrUsername()%>">
                            </div>
                            <div class="col-sm-2">
                                <a href="javascript:void(0)" class="btn btn-xs btn-warning" onclick="admin.updateUserUsername()">update</a>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" name="txtPwd" class="form-control" value="<%= cm.getUsrPwd()%>">
                            </div>
                            <div class="col-sm-2">
                                <a href="javascript:void(0)" class="col-sm-2 btn btn-xs btn-warning">update</a>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
