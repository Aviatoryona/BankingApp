<%--
    Document   : dashboard
    Created on : Aug 21, 2020, 11:49:07 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/styles.jsp" %>
    </head>
    <body class="top-navigation">
        <div id="wrapper">
            <div id="page-wrapper" class="gray-bg">
                <%@include file="includes/navbar.jsp" %>
                <div class="wrapper wrapper-content">
                    <div class="container">
                        <%@include file="includes/home_dashboard.jsp" %>
                    </div>
                </div>
                <%@include file="includes/footer.jsp" %>
            </div>
        </div>
        <%@include file="includes/scripts.jsp" %>
    </body>
</html>
