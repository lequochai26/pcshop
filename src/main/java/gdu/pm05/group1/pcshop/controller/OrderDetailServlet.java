package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.Order;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "orderdetail", urlPatterns = "/orderdetail")
public class OrderDetailServlet extends HttpServlet {
    // CONSTRUCTORS:
    public OrderDetailServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get id parameter from request
        String idStr = request.getParameter("id");

        // ID Null case
        if (idStr == null) {
            request.setAttribute(
                "message",
                "Không đủ thông tin truy cập! Vui lòng thử lại!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Get id as an integer
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

        // Get Order entity with given id
        Order order = dbHandler.get(
            Order.class,
            new HQLParameter("id", id)
        );

        // Order not exist case
        if (order == null) {
            request.setAttribute(
                "message",
                "Đơn hàng không tồn tại! Vui lòng thử lại!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Set order attribute for request
        request.setAttribute("order", order);

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/orderdetail.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
