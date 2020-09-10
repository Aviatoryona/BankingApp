<%-- 
    Document   : admin
    Created on : Sep 8, 2020, 5:11:42 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <%@include file="includes/styles.jsp" %>
    </head>

    <body>
        <div id="wrapper">
            <nav class="navbar-default navbar-static-side" role="navigation">
                <%@include file="admin/left-navbar.jsp"%>
            </nav>

            <div id="page-wrapper" class="gray-bg">
                <div class="row border-bottom">
                    <nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
                        <%@include file="admin/top-nav.jsp"%>
                    </nav>
                </div>

                <div class="wrapper wrapper-content animated fadeIn">
                    <%@include file="admin/home-welcome.jsp"%>
                </div>

                <%@include file="includes/footer.jsp" %>
            </div>
            <div id="right-sidebar">
                <%@include file="admin/sidebar-nav.jsp" %>
            </div>
        </div>

        <%@include file="includes/scripts.jsp" %>
        <script>
            $(document).ready(function () {

                var sparklineCharts = function () {
                    $("#sparkline1").sparkline([34, 43, 43, 35, 44, 32, 44, 52], {
                        type: 'line',
                        width: '100%',
                        height: '50',
                        lineColor: '#1ab394',
                        fillColor: "transparent"
                    });

                    $("#sparkline2").sparkline([32, 11, 25, 37, 41, 32, 34, 42], {
                        type: 'line',
                        width: '100%',
                        height: '50',
                        lineColor: '#1ab394',
                        fillColor: "transparent"
                    });

                    $("#sparkline3").sparkline([34, 22, 24, 41, 10, 18, 16, 8], {
                        type: 'line',
                        width: '100%',
                        height: '50',
                        lineColor: '#1C84C6',
                        fillColor: "transparent"
                    });
                };

                var sparkResize;

                $(window).resize(function (e) {
                    clearTimeout(sparkResize);
                    sparkResize = setTimeout(sparklineCharts, 500);
                });

                sparklineCharts();




                var data1 = [
                    [0, 4], [1, 8], [2, 5], [3, 10], [4, 4], [5, 16], [6, 5], [7, 11], [8, 6], [9, 11], [10, 20], [11, 10], [12, 13], [13, 4], [14, 7], [15, 8], [16, 12]
                ];
                var data2 = [
                    [0, 0], [1, 2], [2, 7], [3, 4], [4, 11], [5, 4], [6, 2], [7, 5], [8, 11], [9, 5], [10, 4], [11, 1], [12, 5], [13, 2], [14, 5], [15, 2], [16, 0]
                ];
                $("#flot-dashboard5-chart").length && $.plot($("#flot-dashboard5-chart"), [
                    data1, data2
                ],
                        {
                            series: {
                                lines: {
                                    show: false,
                                    fill: true
                                },
                                splines: {
                                    show: true,
                                    tension: 0.4,
                                    lineWidth: 1,
                                    fill: 0.4
                                },
                                points: {
                                    radius: 0,
                                    show: true
                                },
                                shadowSize: 2
                            },
                            grid: {
                                hoverable: true,
                                clickable: true,

                                borderWidth: 2,
                                color: 'transparent'
                            },
                            colors: ["#1ab394", "#1C84C6"],
                            xaxis: {
                            },
                            yaxis: {
                            },
                            tooltip: false
                        }
                );

            });
        </script>
    </body>
</html>
