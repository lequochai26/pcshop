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
                        <input name="id" value="${item.id}" hidden/>
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
                                    <input type="text" id="id" name="id" class="textbox" required />
                                </td>
                            </tr>
                        </c:if>

                        <!-- Name row -->
                        <tr>
                            <td>
                                <label for="name" class="label">Tên sản phẩm:</label>
                            </td>

                            <td>
                                <input type="text" id="name" name="name" value="${item.name}" class="textbox" required />
                            </td>
                        </tr>

                        <!-- Price row -->
                        <tr>
                            <td>
                                <label for="price" class="label">Đơn giá:</label>
                            </td>

                            <td>
                                <input type="number" id="price" name="price" value="${item.price}" class="textbox" required/>
                            </td>
                        </tr>

                        <!-- Description row -->
                        <tr>
                            <td>
                                <label for="description" class="label">Mô tả sản phẩm:</label>
                            </td>

                            <td>
                                <textarea id="description" name="description" class="areabox" style="font-size: 18px;" required>${item.description}</textarea>
                            </td>
                        </tr>

                        <!-- Type row -->
                        <tr>
                            <td>
                                <label for="type" class="label">Loại sản phẩm:</label>
                            </td>

                            <td>
                                <select id="type" name="type" class="textbox">
                                    <c:forEach var="itemType" items="${itemTypes}">
                                        <option value="${itemType.id}">${itemType.id} - ${itemType.name}</option>
                                    </c:forEach>
                                </select>
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