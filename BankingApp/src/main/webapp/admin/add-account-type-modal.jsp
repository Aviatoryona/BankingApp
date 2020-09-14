<%--
    Document   : add-account-type-modal
    Created on : Sep 14, 2020, 9:07:58 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="modal-form" class="modal fade show" aria-modal="true" style="padding-right: 17px; display: block;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12"><h3 class="m-t-none m-b"><i class="fa fa-plus-circle"></i> Account Type</h3>

                        <p>Provide more options to your clients</p>

                        <form role="form" onsubmit="return false">
                            <div class="form-group">
                                <label>Account Name</label>
                                <input type="text" placeholder="Account Name" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Max Balance</label>
                                <input type="number" min="5000" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Min Balance</label>
                                <input type="number" min="0" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea class="form-control" rows="4"></textarea>
                            </div>
                            <div>
                                <button class="btn btn-sm btn-primary float-right m-t-n-xs" type="submit"><strong>Save</strong></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
