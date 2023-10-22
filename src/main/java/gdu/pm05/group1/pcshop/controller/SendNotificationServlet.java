package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import java.util.Map;

import gdu.pm05.group1.pcshop.controller.util.ServletUtil;
import gdu.pm05.group1.pcshop.controller.util.enums.AdministratorValidationResult;
import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "sendnotification", urlPatterns = "/sendnotification")
public class SendNotificationServlet extends HttpServlet {
    // CONSTRUCTORS:
    public SendNotificationServlet() {
        super();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Administrator validation
        Map<String, Object> path = ServletUtil.administratorValidate(request, response);

        // Get administrator validation result
        AdministratorValidationResult result = (AdministratorValidationResult)path.get("administratorValidatResult");

        // Not administrator case
        if (result != AdministratorValidationResult.IS_ADMINISTRATOR) {
            ServletUtil.showPermissionRequiredMessage(request, response);
            return;
        }

        // Get 
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }
}
