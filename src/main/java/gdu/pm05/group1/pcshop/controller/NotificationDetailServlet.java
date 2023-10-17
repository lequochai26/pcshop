package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import gdu.pm05.group1.pcshop.domain.Notification;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "notificationdetail", urlPatterns = "/notificationdetail")
public class NotificationDetailServlet extends HttpServlet {
    // CONSTRUCTORS:
    public NotificationDetailServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get id parameter
        String id = request.getParameter("id");

        // Id null case
        if (id != null) {
            try {
                // Parse id string into an integer
                int intId = Integer.parseInt(id);

                // Get notification with given id
                Notification notification = Notification.getNotification(intId);

                // Set notification as 'notification' attribute in request
                request.setAttribute("notification", notification);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Get referer header
        String referer = request.getHeader("referer");

        // Referer not null null
        if (referer != null) {
            // Set referer as an attribute of request
            request.setAttribute("referer", referer);
        }

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/notificationdetail.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
