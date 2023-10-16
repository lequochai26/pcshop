<!-- JSP Page Configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->
<!DOCTYPE html>

<html>
    <head>
        <title>Chi tiết đơn hàng</title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->
        <jsp:include page="styles/general.jsp"/>

        <!-- CSS files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
        <link rel="stylesheet" href="./css/orderdetail.css"/>
    </head>

    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">
            <!-- Content box -->
            <div class="contentBox" style="text-align: justify; font-family: Arial; font-size: 25px;">
                <!-- Items area -->
                <div class="itemsArea">
                    <div class="title">
                        Vật phẩm:
                    </div>
                    <div class="items">
                        <div>
                            Vật phẩm A x 1 25000
                        </div>
                        <div>
                            Vật phẩm B x 2 98000
                        </div>
                        <div>
                            Vật phẩm C x 5 90250
                        </div>
                    </div>
                </div>

                <!-- Price area -->
                <div class="priceArea" style="margin-bottom: 50px;">
                    Tổng đơn hàng: 213250
                </div>

                <!-- Payment area -->
                <div class="paymentArea">
                    <div class="paymentTitle">
                        Phương thức thanh toán:
                    </div>
                    <div class="paymentMethods">
                        <ul>
                            <li>Tài khoản ngân hàng</li>
                            <li>Số dư trong ví</li>
                        </ul>
                    </div>
                </div>

                <div class="orderArea" style="text-align: center;">
                    <button style="width: fit-content; background-color: rgb(98, 175, 252); border: 1px solid black; border-radius: 5px; padding-top: 5px; padding-bottom: 5px;">Đặt hàng</button>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>