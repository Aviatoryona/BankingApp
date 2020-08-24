1<%--
    Document   : dashboard
    Created on : Aug 21, 2020, 11:49:07 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Cookie[] cookie = request.getCookies();
    if (cookie.length == 0) {
        response.sendRedirect("login.html");
        return;
    }
%>
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
                    <div class="container"></div>
                </div>
                <%@include file="includes/footer.jsp" %>
            </div>
        </div>
        <%@include file="includes/scripts.jsp" %>
        <script type="text/javascript" src="js/custom/template.js"></script>
    </body>
</html>
