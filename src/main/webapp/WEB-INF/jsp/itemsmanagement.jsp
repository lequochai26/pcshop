<!-- JSP Page configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->
<!DOCTYPE html>

<html>
    <head>
        <title>Quản lý thông tin sản phẩm</title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
        <link rel="stylesheet" href="./css/itemsmanagement.css"/>
    </head>

    <body>
        <!-- Heder -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">

            <!-- Content box -->
            <div class="contentBox">
                Hello World!
            </div>

        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>