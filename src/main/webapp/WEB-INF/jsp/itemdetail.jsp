<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <!-- Title -->
        <title>Chi tiết sản phẩm</title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->
        <jsp:include page="styles/general.jsp"/>
        <jsp:include page="styles/itemimagesboxstyle.jsp"/>
        <jsp:include page="styles/iteminfoboxstyle.jsp"/>
        <jsp:include page="styles/addtocartboxstyle.jsp"/>

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
        <link rel="stylesheet" href="./css/itemdetail.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">
            <!-- Content box -->
            <div class="contentBox" style="width: 90%;">
                <!-- Left side area -->
                <div class="leftArea">
                    <!-- Item images box -->
                    <jsp:include page="components/itemimagesbox.jsp"/>
                </div>

                <!-- Right side area -->
                <div class="rightArea">
                    <!-- Item info box -->
                    <jsp:include page="components/iteminfobox.jsp"/>

                    <!-- Add to cart box -->
                    <jsp:include page="components/addtocartbox.jsp"/>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>