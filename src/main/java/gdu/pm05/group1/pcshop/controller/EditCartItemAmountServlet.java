package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.controller.util.ServletUtil;
import gdu.pm05.group1.pcshop.model.Cart;
import gdu.pm05.group1.pcshop.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet (name = "editcartitemamount", urlPatterns = "/editcartitemamount")
public class EditCartItemAmountServlet extends HttpServlet {
    // CONSTRUCTORS:
    public EditCartItemAmountServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get id parameter
        String id = request.getParameter("id");

        // ID null case
        if (id == null) {
            ServletUtil.showInputRequiredMessage(request, response);
            return;
        }

        // Get amount parameter
        String amountStr = request.getParameter("amount");

        // Amount null case
        if (amountStr == null) {
            ServletUtil.showInputRequiredMessage(request, response);
            return;
        }

        // Cast amount string to an integer
        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } 
        catch (Exception e) {
            e.printStackTrace();
            ServletUtil.showInvalidInputMessage(request, response);
            return;
        }

        // Get session
        HttpSession session = request.getSession(false);

        // Session null case
        if (session == null) {
            ServletUtil.showMessage(
                request, response,
                "Không tìm thấy giỏ hàng, vui lòng thử lại!"
            );
        }

        // Get user from session
        User user = (User)session.getAttribute("user");

        // Get cart
        Cart cart = null;
        if (user == null) {
            cart = (Cart)session.getAttribute("cart");
        }
        else {
            cart = user.getCart();
        }

        // Cart null case
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
