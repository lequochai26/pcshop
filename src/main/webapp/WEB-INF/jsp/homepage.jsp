<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <!-- Title -->
        <title>PM05 Gear</title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->
        <jsp:include page="styles/general.jsp"/>

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/homepage.css"/>
        <link rel="stylesheet" href="./css/formpage.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">

            <!-- Type filter box -->
            <div id="typeFilterBox" class="contentBox">
                <form action="typesearch" method="post">
                    <label for="id" class="label">Tìm kiếm theo loại: </label>
                    <select id="id" name="id" class="textbox" required>

                        <c:if test="${empty param.id}">
                            <option value="" selected>Tất cả</option>
                        </c:if>
                        <c:if test="${not empty param.id}">
                            <option value="">Tất cả</option>
                        </c:if>

                        <c:forEach var="type" items="${types}">
                            <c:if test="${type.id == param.id}">
                                <option value="${type.id}" selected>${type.name}</option>
                            </c:if>
                            <c:if test="${type.id != param.id}">
                                <option value="${type.id}">${type.name}</option>
                            </c:if>
                        </c:forEach>

                    </select>
                    <input type="submit" class="button" value="Tìm kiếm"/>
                </form>
            </div>

        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>