package gdu.pm05.group1.pcshop.presentation.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gdu.pm05.group1.pcshop.domain.Notification;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name="notification", urlPatterns="/notification")
public class NotificationServlet extends HttpServlet {
    // CONSTRUCTORS:
    public NotificationServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        // TESTING
        List<Notification> notifications = new ArrayList<>();
        Notification notification = new Notification();
        notification.setTitle("This is a very long title prepared by Le Quoc Hai, you should view it carefully or he'll f*ck you up!");
        notification.setContent("Hôm nay, PM05 Gear chúng tôi hân hạnh mang đến cho các bạn các sản phẩm với mức giá ưu đãi vô cùng hấp dẫn hỗ trợ cho tất cả mọi người từ học sinh, sinh viên cho đến doanh nhân, công nhân cho đến cán bộ viên nhà nước. Còn chờ gì mà không đến PM05 Gear ? Hãy nhanh tay đến thăm trang web của chúng tôi nào!");
        notifications.add(notification);
        request.setAttribute("notifications", notifications);

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/notification.jsp");

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