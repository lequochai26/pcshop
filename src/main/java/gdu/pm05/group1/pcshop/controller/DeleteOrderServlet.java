package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.Order;
import gdu.pm05.group1.pcshop.model.OrderItem;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Set;

@WebServlet (name = "deleteorder", urlPatterns = "/deleteorder")
public class DeleteOrderServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public DeleteOrderServlet() {
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

        // Get id parameter
        String idStr = request.getParameter("id");

        // ID null case
        if (idStr == null) {
            request.setAttribute(
                "message",
                "Không đủ thông tin truy cập!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Get integer id
        int id;
        try {
            id = Integer.parseInt(idStr);
        }
        catch (Exception e) {
            request.setAttribute(
                "message",
                e.toString()
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Get context
        ServletContext context = request.getServletContext();

        // Get DBHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get order entity with given id
        Order order = dbHandler.get(
            Order.class,
            new HQLParameter("id", id)
        );

        // Order null case
        if (order == null) {
            request.setAttribute(
                "message",
                "Đơn hàng không tồn tại! Xin vui lòng thử lại!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Get related entities
        Set<OrderItem> items = order.getItems();

        // Delete all order items
        for (OrderItem item : items) {
            dbHandler.remove(item);
        }

        // Remove order
        dbHandler.remove(order);

        // Send redirect back to ordersmanagement page
        response.sendRedirect("ordersmanagement");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
