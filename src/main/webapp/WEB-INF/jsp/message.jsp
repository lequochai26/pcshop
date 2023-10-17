<!-- JSP Page Configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->
<!DOCTYPE html>

<html>
    <head>
        <title>
            <c:if test="${not empty message}">
                ${message}
            </c:if>
        </title>

        <!-- CSS Inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- CSS Files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>

        <style>
            .contentBox p {
                /* Font */
                font-family: Arial;
                font-size: 25px;
                font-weight: bold;
            }
        </style>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">
            <div class="contentBox">
                <p style="color: ${color};">
                    ${message}
                </p>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>