<%--
    Document   : user-details
    Created on : Sep 15, 2020, 10:58:55 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="p-w-md m-t-  animated fadeInDown" id="adm_home">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>{{name}}<small></small></h5>
                    <div class="ibox-tools"> </div>
                </div>
                <div class="ibox-content">
                    <form onsubmit="return false">
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Email</label>
                            <div class="col-sm-10">
                                <input type="text" name="txtEmail" class="form-control" v-bind:value="email" readonly="">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10"><input type="text" class="form-control" v-bind:value="phone" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Username</label>
                            <div class="col-sm-8">
                                <input type="text" name="txtUsrName" class="form-control" v-bind:value="username">
                            </div>
                            <div class="col-sm-2">
                                <a href="javascript:void(0)" class="btn btn-xs btn-warning" onclick="admin.updateUserUsername()">update</a>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" name="txtPwd"  class="form-control" v-bind:value="pwd">
                            </div>
                            <div class="col-sm-2">
                                <a href="javascript:void(0)" class="btn btn-xs btn-warning" onclick="admin.updateUserPassword()">update</a>
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
