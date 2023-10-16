<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <title>PM05 Gear</title>

        <jsp:include page="css_inclusion/general.xml"/>

        <jsp:include page="styles/general.jsp"/>

        <!-- <link rel="stylesheet" href="./css/homepage.css"/> -->
        <link rel="stylesheet" href="./css/formpage.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <div class="body">
            <div class="contentBox">
                Xem sản phẩm: <select>
                    <option>Tất cả</option>
                    <option>Máy tính để bàn</option>
                    <option>Máy tính xách tay</option>
                    <option>RAM</option>
                    <option>CPU</option>
                    <option>Main board</option>
                    <option>Khác</option>
                </select>
                <table cellspacing="5">
                    <c:forEach var="i" begin="1" end="5">
                        <tr>
                            <c:forEach var="j" begin="1" end="4">
                                <td>
                                    <img src="./assets/item.png" style="width: 50px; height: 50px;"/>
                                    <br/>
                                    <p>
                                        PC Super Fast Century
                                    </p>
                                    <p>
                                        Giá: 900000
                                    </p>
                                    <p>
                                        <button class="button">Xem sản phẩm</button>
                                    </p>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>