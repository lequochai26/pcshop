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

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
        <link rel="stylesheet" href="./css/cart.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">

            <!-- Cart item displaying -->
            <div class="contentBox">
                
                <!-- Order form -->
                <form action="orderrequest" method="post">

                    <!-- Cart items displaying table -->
                    <table cellpadding="10">
                        <!-- Heading row -->
                        <tr>
                            <th></th>
                            <th>Sản phẩm</th>
                            <th>Đơn giá</th>
                            <th>Số lượng</th>
                            <th>Tổng tiền</th>
                            <th>Hành động</th>
                        </tr>

                        <!-- Displaying each item per row -->
                        <c:forEach var="cartItem" items="${cartItems}">
                            <tr>
                                <td>
                                    <input type="checkbox" id="${cartItem.item.id}" name="${cartItem.item.name}"/>
                                </td>

                                <td>
                                    <img src="itemimage?id=${cartItem.item.avatar.id}" class="itemAvatar"/>
                                    <a href="itemdetail?id=${cartItem.item.id}">
                                        ${cartItem.item.name}
                                    </a>
                                </td>

                                <td>
                                    ${cartItem.amount}
                                </td>

                                <td>
                                    ${cartItem.amount * cartItem.item.price}
                                </td>

                                <td>
                                    <a href="removecartitem?id=${cartItem.item.id}">
                                        Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </form>

            </div>

        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>