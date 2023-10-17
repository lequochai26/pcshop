package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.Cart;
import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.UserInfo;
import gdu.pm05.group1.pcshop.model.dbhandler.DBHandler;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import gdu.pm05.group1.pcshop.model.enums.UserPermission;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name="register", urlPatterns="/register")
public class RegisterServlet extends HttpServlet {
    // CONSTRUCTORS:
    public RegisterServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from request
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String genderStr = request.getParameter("sex");
        Boolean gender = (
            (genderStr != null)
            ? genderStr.equals("Male")
            : true
        );
        String phoneNumbers = request.getParameter("phoneNumber");
        String address = request.getParameter("address");

        // Validate phoneNumbers
        if (!phoneNumbers.matches("^0{1}\\d{9}$")) {
            request.setAttribute("message", "Số điện thoại không hợp lệ!");
            this.doGet(request, response);
            return;
        }

        // Get DBHandler
        IDBHandler dbHandler = (IDBHandler)request.getServletContext().getAttribute("dbHandler");
        
        // Get user from db with given username
        User user = dbHandler.get(
            User.class,
            new HQLParameter("username", username)
        );

        // User already exist case
        if (user != null) {
            request.setAttribute(
                "message",
                "Người dùng '@username' đã tồn tại!".replace("@username", username)
            );
            this.doGet(request, response);
            return;
        }

        // Create new user
        user = new User();
        UserInfo userInfo = new UserInfo();
        Cart cart = new Cart();

        // Set user's informations
        user.setUsername(username);
        user.setPassword(password);
        user.setPermission(UserPermission.CUSTOMER);
        user.setUserInfo(userInfo);
        user.setCart(cart);

        // Set userInfo's informations
        userInfo.setUsername(username);
        userInfo.setFullName(fullName);
        userInfo.setGender(gender);
        userInfo.setPhoneNumbers(phoneNumbers);
        userInfo.setAddress(address);
        userInfo.setUser(user);

        // Set cart's informations
        cart.setUsername(username);
        cart.setUser(user);

        // Save all data related to user to database
        dbHandler.save(user, userInfo, cart);

        // Set attributes for request
        request.setAttribute("color", "green");
        request.setAttribute(
            "message",
            "Đăng ký tài khoản thành công!"
        );

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");

        // Forward
        dispatcher.forward(request, response);
    }
}
