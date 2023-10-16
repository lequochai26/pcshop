<!-- JSP Page Configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->
<!DOCTYPE html>

<html>
    <head>
        <!-- Title -->
        <title>Giỏ hàng</title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->
        <jsp:include page="styles/general.jsp"/>
        <jsp:include page="styles/cartitemboxstyle.jsp"/>
        <jsp:include page="styles/cartitemcontrolboxstyle.jsp"/>

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
        <link rel="stylesheet" href="./css/cart.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">
            <!-- Cart items control content box -->
            <div class="contentBox" style="margin-bottom: 25px;">
               <jsp:include page="components/cartitemcontrolbox.jsp"/>
            </div>

            <!-- Cart items content box -->
            <div class="contentBox">
                <!-- Cart Items -->
                <c:forEach var="i" begin="1" end="100">
                    <jsp:include page="components/cartitembox.jsp"/>
                </c:forEach>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>