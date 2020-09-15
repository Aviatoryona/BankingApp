<%--
    Document   : user-details
    Created on : Sep 15, 2020, 10:58:55 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="p-w-md m-t-sm" id="adm_home">
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
                            <div class="col-sm-10"><input type="text" class="form-control" value="{{email}}" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Phone</label>
                            <div class="col-sm-10"><input type="text" class="form-control" value="{{phone}}" readonly=""></div>
                        </div>
                        <div class="hr-line-dashed"></div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Username</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" value="{{username}}">
                            </div>
                            <a href="javascript:void(0)" class="col-sm-2 btn btn-xs btn-warning">update</a>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group  row">
                            <label class="col-sm-2 col-form-label">Password</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" value="{{pwd}}">
                            </div>
                            <a href="javascript:void(0)" class="col-sm-2 btn btn-xs btn-warning">update</a>
                        </div>
                        <div class="hr-line-dashed"></div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>
