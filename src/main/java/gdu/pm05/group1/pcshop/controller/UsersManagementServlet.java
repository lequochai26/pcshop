package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import java.util.List;

import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "usersmanagement", urlPatterns = "/usersmanagement")
public class UsersManagementServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public UsersManagementServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Administrator validation
        boolean valid = this.validateAdministrator(request, response);

        // Exit of not valid
        if (!valid) {
            return;
        }

        // Get context
        ServletContext context = request.getServletContext();

        // Get dbHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get all users in db
        List<User> users = dbHandler.getAll(User.class);

        // Set users attribute for request
        request.setAttribute("users", users);
        
        // Get request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/usersmanagement.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
