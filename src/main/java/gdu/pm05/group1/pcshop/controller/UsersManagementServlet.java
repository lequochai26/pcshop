package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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
