<%--
    Document   : home_dashboard
    Created on : Aug 21, 2020, 12:04:11 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-md-2">
        <div class="ibox ">
            <div class="ibox-title">
                <div class="ibox-tools">
                    <span class="label label-success float-right">Monthly</span>
                </div>
                <h5>Views</h5>
            </div>
            <div class="ibox-content">
                <h1 class="no-margins">386,200</h1>
                <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i></div>
                <small>Total views</small>
            </div>
        </div>
    </div>
    <div class="col-md-2">
        <div class="ibox ">
            <div class="ibox-title">
                <div class="ibox-tools">
                    <span class="label label-info float-right">Annual</span>
                </div>
                <h5>Orders</h5>
            </div>
            <div class="ibox-content">
                <h1 class="no-margins">80,800</h1>
                <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i></div>
                <small>New orders</small>
            </div>
        </div>
    </div>

    <div class="col-md-4">
        <div class="ibox ">
            <div class="ibox-title">
                <div class="ibox-tools">
                    <span class="label label-primary float-right">Today</span>
                </div>
                <h5>visits</h5>
            </div>
            <div class="ibox-content">

                <div class="row">
                    <div class="col-md-6">
                        <h1 class="no-margins">$ 406,420</h1>
                        <div class="font-bold text-navy">44% <i class="fa fa-level-up"></i> <small>Rapid pace</small></div>
                    </div>
                    <div class="col-md-6">
                        <h1 class="no-margins">206,120</h1>
                        <div class="font-bold text-navy">22% <i class="fa fa-level-up"></i> <small>Slow pace</small></div>
                    </div>
                </div>


            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Monthly income</h5>
                <div class="ibox-tools">
                    <span class="label label-primary">Updated 12.2015</span>
                </div>
            </div>
            <div class="ibox-content no-padding">
                <div class="flot-chart m-t-lg" style="height: 55px;">
                    <div class="flot-chart-content" id="flot-chart1"></div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="ibox ">
            <div class="ibox-title">
                <h5>Recent Transactions</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#" class="dropdown-item">Config option 1</a>
                        </li>
                        <li><a href="#" class="dropdown-item">Config option 2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <div class="col-sm-9 m-b-xs">
                        <div data-toggle="buttons" class="btn-group btn-group-toggle">
                            <label class="btn btn-sm btn-white"> <input type="radio" id="option1" name="options"> Day </label>
                            <label class="btn btn-sm btn-white active"> <input type="radio" id="option2" name="options"> Week </label>
                            <label class="btn btn-sm btn-white"> <input type="radio" id="option3" name="options"> Month </label>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control form-control-sm" placeholder="Search">
                            <div class="input-group-append">
                                <button class="btn btn-sm btn-primary" type="button">Go!</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>

                                <th>#</th>
                                <th>Project </th>
                                <th>Name </th>
                                <th>Phone </th>
                                <th>Company </th>
                                <th>Completed </th>
                                <th>Task</th>
                                <th>Date</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Project <small>This is example of project</small></td>
                                <td>Patrick Smith</td>
                                <td>0800 051213</td>
                                <td>Inceptos Hymenaeos Ltd</td>
                                <td><span class="pie">0.52/1.561</span></td>
                                <td>20%</td>
                                <td>Jul 14, 2013</td>
                                <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>Alpha project</td>
                                <td>Alice Jackson</td>
                                <td>0500 780909</td>
                                <td>Nec Euismod In Company</td>
                                <td><span class="pie">6,9</span></td>
                                <td>40%</td>
                                <td>Jul 16, 2013</td>
                                <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>
</div>
