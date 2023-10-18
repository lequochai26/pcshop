package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet (name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    // CONSTRUCTORS:
    public LoginServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session
        HttpSession session = request.getSession(false);

        // User logged in case
        if (session != null) {
            if (session.getAttribute("user") != null) {
                RequestDispatcher messageDispatcher = request.getRequestDispatcher(
                    "WEB-INF/jsp/message.jsp"
                );

                request.setAttribute("color", "black");
                request.setAttribute(
                    "message", "Bạn đã đăng nhập!"
                );

                messageDispatcher.forward(request, response);
                return;
            }
        }

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get servlet context
        ServletContext context = request.getServletContext();

        // Get dbHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get parameters
        String username = request.getParameter("email");
        String password = request.getParameter("password");

        // Get user from username
        User user = dbHandler.get(
            User.class,
            new HQLParameter("username", username)
        );

        // User not exist case
        if (user == null) {
            request.setAttribute(
                "message",
                "Tài khoản không tồn tại!"
            );
            this.doGet(request, response);
            return;
        }

        // Wrong password case
        if (!user.getPassword().equals(password)) {
            request.setAttribute(
                "message",
                "Sai mật khẩu!"
            );
            this.doGet(request, response);
            return;
        }

        // Login successfully
        // Get or create new session
        HttpSession session = request.getSession(true);
        
        // Set user as an attribute for session
        session.setAttribute("user", user);

        // Send redirect to home page
        response.sendRedirect("home");
    }
}
