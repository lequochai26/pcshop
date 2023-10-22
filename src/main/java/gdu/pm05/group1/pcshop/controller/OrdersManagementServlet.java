package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import java.util.List;

import gdu.pm05.group1.pcshop.controller.util.ServletUtil;
import gdu.pm05.group1.pcshop.model.Order;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import gdu.pm05.group1.pcshop.model.holder.OrderStatusHolder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "ordersmanagement", urlPatterns = "/ordersmanagement")
public class OrdersManagementServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public OrdersManagementServlet() {
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

        // Get context
        ServletContext context = request.getServletContext();

        // Get DBHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get all orders
        List<Order> orders = dbHandler.getAll(Order.class);

        // Set orders attribute for request
        request.setAttribute("orders", orders);

        // Get all order status holders
        List<OrderStatusHolder> statusHolders;
        try {
            statusHolders = ServletUtil.getAllStatusHolders();
        }
        catch (Exception e) {
            e.printStackTrace();
            ServletUtil.showMessage(
                request, response, e.toString(), "red"
            );
            return;
        }

        // Set OrderStatus attribute for request
        request.setAttribute("OrderStatus", statusHolders);

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/ordersmanagement.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
