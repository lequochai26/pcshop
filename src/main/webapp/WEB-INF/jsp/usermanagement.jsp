<!-- JSP Page Configurations -->
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HTML -->
<!DOCTYPE html>

<html>
    <head>
        <!-- Title -->
        <title>
            <c:if test="${not empty user}">
                user.username
            </c:if>
            <c:if test="${empty user}">
                Thêm người dùng
            </c:if>
        </title>

        <!-- CSS inclusion -->
        <jsp:include page="css_inclusion/general.xml"/>

        <!-- Styles inclusion -->
        <jsp:include page="styles/general.jsp"/>

        <!-- CSS Files linking -->
        <link rel="stylesheet" href="./css/formpage.css"/>
    </head>

    <body>
        <!-- Heder -->
        <jsp:include page="header.jsp"/>

        <!-- Body -->
        <div class="body">

            <!-- Content box -->
            <div class="contentBox">
                <!-- User form -->
                <form action="usermanagement" method="post">
                    <!-- Hidden inputs -->
                    <input value="${param.action}" hidden/>
                    <c:if test="${not empty user}">
                        <input id="email" name="email" value="${user.username}" hidden/>
                    </c:if>

                    <!-- User table -->
                    <table cellpadding="5">
                        <!-- Username row -->
                        <c:if test="${empty user}">
                            <tr>
                                <td>
                                    <label for="email" class="label">
                                        Email:
                                    </label>
                                </td>

                                <td>
                                    <input type="email" id="email" name="email" class="textbox"/>
                                </td>
                            </tr>
                        </c:if>

                        <!-- Password row -->
                        <c:if test="${empty user}">
                            <tr>
                                <td>
                                    <label for="password" class="label">
                                        Mật khẩu:
                                    </label>
                                </td>

                                <td>
                                    <input type="password" id="password" name="password" class="textbox"/>
                                </td>
                            </tr>
                        </c:if>

                        <!-- Full name row -->
                        <tr>
                            <td>
                                <label for="fullName" class="label">
                                    Họ và tên:
                                </label>
                            </td>

                            <td>
                                <input type="text" id="fullName" name="fullName" value="${user.userInfo.fullName}" class="textbox"/>
                            </td>
                        </tr>

                        <!-- Gender row -->
                        <tr>
                            <td>
                                <label class="label">
                                    Giới tính:
                                </label>
                            </td>

                            <td>
                                <c:if test="${not empty user}">
                                    <c:if test="${user.userInfo.gender}">
                                        <input type="radio" id="maleRadioSelector" name="gender" value="Male" class="radioselector" checked/>
                                    </c:if>
                                    <c:if test="${not user.userInfo.gender}">
                                        <input type="radio" id="maleRadioSelector" name="gender" value="Male" class="radioselector"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${empty user}">
                                    <input type="radio" id="maleRadioSelector" name="gender" value="Male" class="radioselector" checked/>
                                </c:if>

                                <label for="maleRadioSelector" class="label">Nam</label>

                                <c:if test="${not empty user}">
                                    <c:if test="${not user.userInfo.gender}">
                                        <input type="radio" id="femaleRadioSelector" name="gender" value="Female" class="radioselector" checked/>
                                    </c:if>
                                    <c:if test="${user.userInfo.gender}">
                                        <input type="radio" id="femaleRadioSelector" name="gender" value="Female" class="radioselector"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${empty user}">
                                    <input type="radio" id="femaleRadioSelector" name="gender" value="Female" class="radioselector"/>
                                </c:if>

                                <label for="femaleRadioSelector" class="label">Nữ</label>
                            </td>
                        </tr>

                        <!-- Phone numbers row -->
                        <tr>
                            <td>
                                <label for="phoneNumbers" class="label">
                                    Số điện thoại:
                                </label>
                            </td>

                            <td>
                                <input type="text" id="phoneNumbers" name="phoneNumbers" value="${user.userInfo.phoneNumbers}" class="textbox"/>
                            </td>
                        </tr>

                        <!-- Address row -->
                        <tr>
                            <td>
                                <label for="address" class="label">
                                    Địa chỉ:
                                </label>
                            </td>

                            <td>
                                <textarea name="address" id="address" class="areabox">${user.userInfo.address}</textarea>
                            </td>
                        </tr>

                        <!-- Action row -->
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="Lưu" class="button"/>
                                <c:if test="${not empty user}">
                                    <a href="deleteuser?username=${user.username}" class="button">
                                        Xóa
                                    </a>
                                </c:if>
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