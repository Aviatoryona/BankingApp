<%--
    Document   : manage
    Created on : Sep 13, 2020, 11:18:34 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="p-w-md m-t-sm" id="adm_home">
    <div class="row">
        <div class="col-sm-4">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Countries</h5>
                    <div class="ibox-tools"></div>
                </div>
                <div class="ibox-content">
                    <form onsubmit="return false">
                        <select class="form-control" multiple="">
                            <option v-for="country in countries">
                                {{country.ctryName}}
                            </option>
                        </select>
                        <div class="hr-line-dashed"></div>
                        <p><i class="fa fa-plus-circle"></i> Countries</p>
                        <div class="form-group row">
                            <div class="col-lg-8">
                                <input name="ctryName" type="text" placeholder="Country" class="form-control" required/>
                            </div>
                            <div class="col-lg-4">
                                <button class="btn btn-sm btn-white" type="submit" onclick="admin.a">Save</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Account Types</h5>
                    <div class="ibox-tools"></div>
                </div>
                <div class="ibox-content">
                    <form onsubmit="return false">
                        <select class="form-control" multiple="">
                            <option v-for="acctype in acctypes">
                                {{acctype.acctype}}
                            </option>
                        </select>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group row">
                            <div class="col-lg-6">
                                <!--<a class="btn btn-sm btn-white" type="submit" data-toggle="modal" href="#modal-form"><i class="fa fa-refresh"></i>Update</a>-->
                            </div>
                            <div class="col-lg-6">
                                <a class="btn btn-sm btn-white" type="submit" data-toggle="modal" href="#modal-form"><i class="fa fa-plus-circle"></i> Account Type</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox ">
                <div class="ibox-title">
                    <h5>Transaction Types</h5>
                    <div class="ibox-tools"></div>
                </div>
                <div class="ibox-content">
                    <form onsubmit="return false">
                        <select class="form-control" multiple="">
                            <option v-for="trtyp in trtyps">
                                {{trtyp.tpType}} ({{trtyp.tpCharge}})
                            </option>
                        </select>
                        <div class="hr-line-dashed"></div>
                        <p><i class="fa fa-refresh"></i> Select to update transaction charges</p>
                        <div class="form-group row">
                            <div class="col-lg-8">
                                <input type="number" placeholder="Transaction Charge" class="form-control" required/>
                            </div>
                            <div class="col-lg-4">
                                <button class="btn btn-sm btn-white" type="submit">Update</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
