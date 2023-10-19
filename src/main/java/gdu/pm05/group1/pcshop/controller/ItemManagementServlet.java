package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.Item;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "itemmanagement", urlPatterns = "/itemmanagement")
public class ItemManagementServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public ItemManagementServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Validation
        boolean valid = this.validateAdministrator(request, response);

        // Exit if not valid
        if (!valid) {
            return;
        }

        // VALIDATION SUCCESSFULLY
        // Get action parameter
        String action = request.getParameter("action");

        // Action null case
        if (action == null) {
            // Show message
            this.showMessage(
                request,
                response,
                "Không có đủ thông tin để truy cập vào trang này!",
                "red"
            );
            return;
        }

        // Invalid action case
        if (
            !action.equals("new") &&
            !action.equals("detail")
        ) {
            // Show message
            this.showMessage(
                request,
                response,
                "Thông tin truy cập không hợp lệ!",
                "red"
            );
            return;
        }

        // Action detail case
        if (action.equals("detail")) {
            // Get ID parameter
            String id = request.getParameter("id");

            // Id null case
            if (id == null) {
                // Show message
                this.showMessage(
                    request,
                    response,
                    "Không có đủ thông tin để truy cập vào trang này!",
                    "red"
                );
                return;
            }

            // Get dbHandler
            ServletContext context = request.getServletContext();
            IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

            // Get item with given id
            Item item = dbHandler.get(
                Item.class,
                new HQLParameter("id", id)
            );

            // Set item as an attribute to request
            request.setAttribute("item", item);
        }

        // Get request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher(
            "WEB-INF/jsp/itemmanagement.jsp"
        );

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private void showMessage(HttpServletRequest request, HttpServletResponse response, String message, String color) throws ServletException, IOException {
        // Set attributes for request
        request.setAttribute("message", message);
        request.setAttribute("color", color);

        // Get request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");

        // Forward
        dispatcher.forward(request, response);
    }
}