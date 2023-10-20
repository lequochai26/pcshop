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

@WebServlet (name = "newitemtype", urlPatterns = "/newitemtype")
public class NewItemTypeServlet extends ItemTypeManagementServlet {
    // CONSTRUCTORS:
    public NewItemTypeServlet() {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validation
        boolean valid = this.validateAdministrator(request, response);

        // Exit if not valid
        if (!valid) {
            return;
        }

        // Get necessary parameters
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        // Get context
        ServletContext context = request.getServletContext();

        // Get dbHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Get item type with given id
        ItemType type = dbHandler.get(
            ItemType.class,
            new HQLParameter("id", id)
        );

        // Item type already exist case
        if (type != null) {
            request.setAttribute(
                "message",
                "Đã tồn tại một loại sản phẩm với mã '@id', vui lòng thử lại!".replace(
                    "@id", id
                )
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Create new type
        type = new ItemType();

        // Type's values assigning
        type.setId(id);
        type.setName(name);

        // Save type to DB
        dbHandler.save(type);

        // Send redirect
        response.sendRedirect("itemtypesmanagement");
    }
}
