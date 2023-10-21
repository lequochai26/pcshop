package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "usermanagement", urlPatterns = "/usermanagement")
public class UserManagementServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public UserManagementServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Administrator validation
        boolean valid = this.validateAdministrator(request, response);

        // Exit if not valid
        if (!valid) {
            return;
        }

        // Get action parameter from request
        String action = request.getParameter("action");

        // Action null case
        if (action == null) {
            request.setAttribute(
                "message",
                "Không đủ thông tin truy cập!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Invalid action case
        if (
            !action.equals("new")
            &&
            !action.equals("detail")
        ) {
            request.setAttribute(
                "message",
                "Thông tin truy cập không hợp lệ!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Action detail case
        if (action.equals("detail")) {
            // Get username parameter
            String username = request.getParameter("username");

            // Username null case
            if (username == null) {
                request.setAttribute(
                    "message",
                    "Không đủ thông tin truy cập!"
                );
                request.setAttribute("color", "red");
                request.getRequestDispatcher("message").forward(request, response);
                return;
            }

            // Get context
            ServletContext context = request.getServletContext();

            // Get DBHandler
            IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

            // Get User from given username
            User user = dbHandler.get(
                User.class,
                new HQLParameter("username", username)
            );

            // User null case
            if (user == null) {
                request.setAttribute(
                    "message",
                    "Thông tin người dùng với email '@username' không tồn tại!".replace("@username", username)
                );
                request.setAttribute("color", "red");
                request.getRequestDispatcher("message").forward(request, response);
                return;
            }

            // Set user attribute for request
            request.setAttribute("user", user);
        }

        // Get request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/usermanagement.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
