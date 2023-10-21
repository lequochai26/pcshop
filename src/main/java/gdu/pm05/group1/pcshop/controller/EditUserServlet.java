package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.UserInfo;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "edituser", urlPatterns = "/edituser")
public class EditUserServlet extends UserManagementServlet {
    // CONSTRUCTORS:
    public EditUserServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // METHOD NOT ALLOWED
        response.setStatus(
            HttpServletResponse.SC_METHOD_NOT_ALLOWED
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Administrator validation
        boolean valid = this.validateAdministrator(request, response);

        // Exit if not valid
        if (!valid) {
            return;
        }

        // Get request parameters
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        boolean gender = request.getParameter("gender").equals("Male");
        String phoneNumbers = request.getParameter("phoneNumbers");
        String address = request.getParameter("address");

        // Check inputs
        if (!phoneNumbers.matches("^0\\d{9}$")) {
            request.setAttribute(
                "message",
                "Số điện thoại không hợp lệ!"
            );
            super.doGet(request, response);
            return;
        }

        // Get context
        ServletContext context = request.getServletContext();

        // Get DBHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get user from given username
        User user = dbHandler.get(
            User.class,
            new HQLParameter("username", username)
        );

        // User not exist case
        if (user == null) {
            request.setAttribute(
                "message",
                "Tài khoản với email '@username', không tồn tại!".replace(
                    "@username", username
                )
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Get user's user info entity
        UserInfo userInfo = user.getUserInfo();

        // Assigning new values for userInfo
        userInfo.setFullName(fullName);
        userInfo.setGender(gender);
        userInfo.setPhoneNumbers(phoneNumbers);
        userInfo.setAddress(address);

        // Save userInfo
        dbHandler.save(userInfo);

        // Forward back to users management page
        RequestDispatcher dispatcher = request.getRequestDispatcher("usersmanagement");

        // Forward
        dispatcher.forward(request, response);
    }
}
