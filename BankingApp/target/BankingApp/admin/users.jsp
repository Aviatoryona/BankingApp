<%--
    Document   : clients
    Created on : Sep 13, 2020, 9:45:12 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="p-w-md m-t-sm" id="adm_home">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>All Users</h5>
                    <div class="ibox-tools"> </div>
                </div>
                <div class="ibox-content">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Email </th>
                                    <th>Phone </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="gradeX" v-for="customer in users">
                                    <td>{{customer.clientUserSd.ctFname}} {{customer.clientUserSd.ctLname}}</td>
                                    <td>
                                        <span class="hide" :id="{{customer.usrId}}"></span>
                                        <a href="javascript:void(0)" onclick="admin.processIndex7(this)">
                                            {{customer.clientUserSd.ctEmail}}
                                        </a>
                                    </td>
                                    <td>{{customer.clientUserSd.ctPhone}}</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Name</th>
                                    <th>Email </th>
                                    <th>Phone </th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5><i class="fa fa-plus-circle"></i> User</h5>
                    <div class="ibox-tools"> </div>
                </div>
                <div class="ibox-content">
                    <p>Add user to assign roles</p>
                    <form role="form" onsubmit="return false">
                        <div class="form-group hide">
                            <input type="text" name="q" value="adduser" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>First Name</label>
                            <input type="text" name="ctFname" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" name="ctLname" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="ctEmail"  class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="tel" name="ctPhone" class="form-control" required>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label>Username</label>
                            <input type="text" name="usrUsername" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="usrPwd" class="form-control" required>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <button class="btn btn-sm btn-primary float-right m-t-n-xs" type="submit"><strong>Save</strong></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>