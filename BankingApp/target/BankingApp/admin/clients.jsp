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
                    <h5>Clients</h5>
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
                                    <th>Gender </th>
                                    <th>Address </th>
                                    <th>City</th>
                                    <th>Country</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="gradeX" v-for="customer in customers">
                                    <td>{{customer.clientUserSd.ctFname}} {{customer.clientUserSd.ctLname}}</td>
                                    <td><a href="javascript:void(0)" onclick="admin.processIndex6('{{customer.clientUserSd.ctEmail}}')">
                                            {{customer.clientUserSd.ctEmail}}
                                        </a>
                                    </td>
                                    <td>{{customer.clientUserSd.ctPhone}}</td>
                                    <td>{{customer.ctGender}}</td>
                                    <td>{{customer.ctAddress}}</td>
                                    <td>{{customer.ctCity}}</td>
                                    <td>{{customer.ctCountry}}</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th>Name</th>
                                    <th>Email </th>
                                    <th>Phone </th>
                                    <th>Gender </th>
                                    <th>Address </th>
                                    <th>City</th>
                                    <th>Country</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>