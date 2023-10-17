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
    </head>

    <body>
        <div class="body">
            <div class="content">
                <p style="color: ${color}; text-align: left;">${message}</p>
            </div>
        </div>
    </body>
</html>