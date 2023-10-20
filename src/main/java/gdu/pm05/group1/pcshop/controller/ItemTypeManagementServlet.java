package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.ItemType;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "itemtypemanagement", urlPatterns = "/itemtypemanagement")
public class ItemTypeManagementServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public ItemTypeManagementServlet() {
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

        // Get necessary parameters
        String action = request.getParameter("action");

        // Action null case
        if (action == null) {
            request.setAttribute("message", "Vui lòng cung cấp đủ thông tin truy cập trước khi truy cập!");
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Action invalid case
        if (
            !action.equals("new")
            &&
            !action.equals("detail")
        ) {
            request.setAttribute(
                "message",
                "Thông tin truy cập không hợp lệ! Vui lòng thử lại!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // External conditions for detail action case
        if (action.equals("detail")) {
            // Get Id parameter
            String id = request.getParameter("id");

            // ID null case
            if (id == null) {
                request.setAttribute(
                    "message",
                    "Vui lòng cung cấp đủ thông tin truy cập trước khi truy cập!"
                );
                request.setAttribute("color", "red");
                request.getRequestDispatcher("message").forward(request, response);
                return;
            }

            // Get context
            ServletContext context = request.getServletContext();

            // Get DBHandler
            IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

            // Get item type with given id
            ItemType type = dbHandler.get(
                ItemType.class,
                new HQLParameter("id", id)
            );

            // Type not exist case
            if (type == null) {
                request.setAttribute(
                    "message",
                    "Loại sản phẩm này không tồn tại! Vui lòng thử lại!"
                );
                request.setAttribute("color", "red");
                request.getRequestDispatcher("message").forward(request, response);
                return;
            }

            // Set type attribute for request
            request.setAttribute("type", type);
        }

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/itemtypemanagement.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
