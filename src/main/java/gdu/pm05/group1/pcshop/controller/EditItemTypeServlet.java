package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;

import gdu.pm05.group1.pcshop.model.ItemType;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "edititemtype", urlPatterns = "/edititemtype")
public class EditItemTypeServlet extends ItemTypeManagementServlet {
    // CONSTRUCTORS:
    public EditItemTypeServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // METHOD NOT ALLOWED
        response.setStatus(
            HttpServletResponse.SC_METHOD_NOT_ALLOWED
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get necessary parameters
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        // Get context
        ServletContext context = request.getServletContext();

        // Get dbHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get ItemType entity with given id
        ItemType type = dbHandler.get(
            ItemType.class,
            new HQLParameter("id", id)
        );

        // Type not exist case
        if (type == null) {
            request.setAttribute(
                "message",
                "Loại sản phẩm không tồn tại! Vui lòng thử lại!"
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Assigning new value for type
        type.setName(name);

        // Save to db
        dbHandler.save(type);

        // Send redirect back to item types management page
        response.sendRedirect("itemtypesmanagement");
    }
}
