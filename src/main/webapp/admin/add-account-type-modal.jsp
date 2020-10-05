<%--
    Document   : add-account-type-modal
    Created on : Sep 14, 2020, 9:07:58 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="modal-form" class="modal fade show" aria-modal="true" style="padding-right: 17px; display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">

                        <h3 class="m-t-none m-b">
                            <i class="fa fa-plus-circle"></i> Account Type
                        </h3>

                        <p>Provide more options to your clients</p>

                        <form role="form" id="miform" onsubmit="return false">
                            <div class="form-group hide" style="display: none">
                                <input type="text" name="q" value="addacctype" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Account Name</label>
                                <input type="text" placeholder="Account Name" name="acctype" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Max Balance</label>
                                <input type="number" min="5000" name="accmaxbal" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Min Balance</label>
                                <input type="number" min="0" name="accminbal" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea class="form-control" name="accdescription" rows="4"></textarea>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-sm btn-primary float-right m-t-n-xs" type="submit"
                                        onclick="admin.addAccountType()">
                                    <strong>Save</strong>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
