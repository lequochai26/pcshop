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
                    ${item.name}
                </c:when>
            </c:choose>
        </title>

        <!-- CSS Inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->
        <jsp:include page="styles/general.jsp"/>

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
        <link rel="stylesheet" href="./css/itemmanagement.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">
            
            <!-- Content box -->
            <div class="contentBox">

                <!-- Item management form -->
                <form action="newitem" method="post">
                    <!-- Hidden ID input for detail action -->
                    <c:if test="${param.action == 'detail'}">
                        <input name="id" value="${item.id}"/>
                    </c:if>

                    <!-- Item management table -->
                    <table cellpadding="5">
                        <!-- ID row -->
                        <c:if test="${param.action == 'new'}">
                            <tr>
                                <td>
                                    <label for="id" class="label">Mã sản phẩm:</label>
                                </td>
    
                                <td>
                                    <c:set var="textBoxId" scope="request" value="id"/>
                                    <c:set var="textBoxName" scope="request" value="id"/>
                                    <jsp:include page="components/textbox.jsp"/>
                                </td>
                            </tr>
                        </c:if>

                        <!-- Name row -->
                        <tr>
                            <td>
                                <label for="name" class="label">Tên sản phẩm:</label>
                            </td>

                            <td>
                                <c:set var="textBoxId" scope="request" value="name"/>
                                <c:set var="textBoxName" scope="request" value="name"/>
                                <jsp:include page="components/textbox.jsp"/>
                            </td>
                        </tr>

                        <!-- Price row -->
                        <tr>
                            <td>
                                <label for="price">Đơn giá:</label>
                            </td>

                            <td>
                                <c:set var="textBoxId" scope="request" value="price"/>
                                <c:set var="textBoxName" scope="request" value="price"/>
                                <jsp:include page="components/textbox.jsp"/>
                            </td>
                        </tr>
                    </table>
                </form>

            </div>

        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>