package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import java.util.Set;

import gdu.pm05.group1.pcshop.model.Cart;
import gdu.pm05.group1.pcshop.model.Order;
import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.UserInfo;
import gdu.pm05.group1.pcshop.model.UserNotification;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "deleteuser", urlPatterns = "/deleteuser")
public class DeleteUserServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public DeleteUserServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Administrator validation
        boolean valid = this.validateAdministrator(request, response);

        // Exit if not valud
        if (!valid) {
            return;
        }

        // Get username parameter
        String username = request.getParameter("username");

        // Username null case
        if (username == null) {
            request.setAttribute(
                "message",
                "Không đủ thông tin truy cập! Vui lòng thử lại!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
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
                "Tài khoản người dùng không tồn tại!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Get related entities
        UserInfo userInfo = user.getUserInfo();
        Cart cart = user.getCart();
        Set<UserNotification> notifications = user.getNotifications();
        Set<Order> orders = user.getOrders();

        // Having orders case
        if (orders.size() > 0) {
            request.setAttribute(
                "message",
                "Tài khoản người dùng này đang có đơn hàng! Không thể xóa!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Delete all user notifications
        for (UserNotification notification : notifications) {
            // Remove notification
            dbHandler.remove(notification);
        }

        // Deleting all related entities
        dbHandler.remove(cart, userInfo, user);

        // Send redirect to users management page
        response.sendRedirect("usersmanagement");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
