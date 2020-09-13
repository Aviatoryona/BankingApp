<%--
    Document   : admin
    Created on : Sep 8, 2020, 5:11:42 PM
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

                <div class="wrapper wrapper-content animated fadeIn" id="mi_content"></div>

                <%@include file="includes/footer.jsp" %>
            </div>
            <div id="right-sidebar">
                <%@include file="admin/sidebar-nav.jsp" %>
            </div>
        </div>

        <%@include file="includes/scripts.jsp" %>

        <!--===============================================================================================-->
        <script src="js/custom/app.js" type="text/javascript"></script>
        <script src="js/custom/admin.js" type="text/javascript"></script>
        <script>
            $(document).ready(function () {

                /*
                 *
                 */
                admin.processIndex0.call();

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
                };

                var sparkResize;

                $(window).resize(function (e) {
                    clearTimeout(sparkResize);
                    sparkResize = setTimeout(sparklineCharts, 500);
                });

                sparklineCharts();

            });
        </script>
    </body>
</html>

