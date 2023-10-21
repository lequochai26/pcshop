<!-- JSP Page Configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->
<!DOCTYPE html>

<html>
    <head>
        <!-- Title -->
        <title>Quản lý đon hàng</title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->
        <jsp:include page="styles.general.jsp"/>

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header"/>

        <!-- Body -->
        <div class="body">

            <!-- Content box -->
            <div class="contentBox">

                Hello World!

            </div>

        </div>

        <!-- Footer -->
        <jsp:include page="footer"/>
    </body>
</html>