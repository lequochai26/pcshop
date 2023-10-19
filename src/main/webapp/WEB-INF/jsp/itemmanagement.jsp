<!-- JSP Page configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->
<!DOCTYPE html>

<html>
    <head>
        <title>
            <c:choose>
                <c:when test="${param.action == 'new'}" >
                    Thêm mới sản phẩm
                </c:when>
                <c:when test="${param.action == 'detail'}" >
                    Chi tiết sản phẩm
                </c:when>
            </c:choose>

            <!-- CSS Inclusion -->
            <jsp:include page="css_inclusion/general.xml"/>

            <!-- Styles inclusion -->

            <!-- CSS files linking -->
            <link rel="stylesheet" href="./css/formpage.css"/>

        </title>

        <body>
            <!-- Header -->
            <jsp:include page="header.jsp"/>

            <!-- Body -->
            <div class="body">
                Hello World!
            </div>

            <!-- Footer -->
            <jsp:include page="footer.jsp"/>
        </body>
    </head>
</html>