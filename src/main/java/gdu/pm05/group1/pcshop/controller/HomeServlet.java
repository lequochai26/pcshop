package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "home", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    // CONSTRUCTORS:
    public HomeServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        this.doGet(request, response);
    }
}