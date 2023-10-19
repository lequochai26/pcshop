package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import java.util.List;
import gdu.pm05.group1.pcshop.model.Item;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "itemsmanagement", urlPatterns = "/itemsmanagement")
public class ItemsManagementServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public ItemsManagementServlet() {
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

        // Get context
        ServletContext context = request.getServletContext();

        // Get dbHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get all items in db
        List<Item> items = dbHandler.getAll(Item.class);

        // Set items attribute for request
        request.setAttribute("items", items);

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/itemsmanagement.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}