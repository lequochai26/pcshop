<!-- JSP Page Configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->

<!DOCTYPE html>

<html>
    <head>
        <title>Quản lý</title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">
            <!-- Content box -->
            <div class="contentBox">
                <!-- Items management -->
                <a href="itemsmanagement">
                    Quản lý thông tin sản phẩm
                </a>

                <!-- Item types management -->
                <a href="itemtypesmanagement">
                    Quản lý thông tin loại sản phẩm
                </a>

                <!-- Orders management -->
                <a href="ordersmanagement">
                    Quản lý thông tin đơn hàng
                </a>

                <!-- Users management -->
                <a href="usersmanagement">
                    Quản lý thông tin người dùng
                </a>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>