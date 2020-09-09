<%--
    Document   : dashboard
    Created on : Aug 21, 2020, 11:49:07 AM
    Author     : Aviator
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!CustomerModel cm = null;%>

<%Enumeration<String> vals = session.getAttributeNames();

//    Cookie[] cookies = request.getCookies();
//    for (int i = 0; i < cookies.length; i++) {
//        Cookie cookie1 = cookies[i];
//        response.getWriter().println(cookie1.getName());
//        if (cookie1.getName().equalsIgnoreCase(AppEnum.LOGGED_IN_USER.getName())) {
//            String s = cookie1.getValue();
//            cm = new ObjectMapper().convertValue(s, CustomerModel.class);
//        }
//    }
    while (vals.hasMoreElements()) {
        String nextElement = vals.nextElement();
//        response.getWriter().println(nextElement);
        if (nextElement.equalsIgnoreCase(AppEnum.LOGGED_IN_USER.getName())) {
            cm = (CustomerModel) session.getAttribute(AppEnum.LOGGED_IN_USER.getName());
        }
    }
    if (cm == null) {
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
                <div class="wrapper wrapper-content animated fadeInRight">
                    <div class="container"></div>
                </div>
                <%@include file="includes/footer.jsp" %>
            </div>
        </div>

        <%@include file="includes/scripts.jsp" %>
        <script type="text/javascript" src="js/custom/app.js"></script>
        <script type="text/javascript" src="js/custom/dashboard.js"></script>
        <script type="text/javascript" src="js/custom/template.js"></script>

    </body>

</html>
