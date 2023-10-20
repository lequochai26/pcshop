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

@WebServlet (name = "deleteitemtype", urlPatterns = "/deleteitemtype")
public class DeleteItemTypeServlet extends AdministratorServlet {
    // CONSTRUCTORS:
    public DeleteItemTypeServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validation
        boolean valid = this.validateAdministrator(request, response);

        // Exit if not valid
        if (!valid) {
            return;
        }

        // Get id parameter
        String id = request.getParameter("id");

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
                "Không tồn tại loại sản phẩm với mã '@id', vui lòng thử lại!".replace(
                    "@id", id
                )
            );
            request.setAttribute("color", "red");
            request.getRequestDispatcher("message").forward(request, response);
            return;
        }

        // Delete type
        dbHandler.remove(type);

        // Send redirect back to item types management page
        response.sendRedirect("itemtypesmanagement");
    }

    
}
