package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gdu.pm05.group1.pcshop.model.validator.AdministratorValidator;
import gdu.pm05.group1.pcshop.model.validator.UserValidator;
import gdu.pm05.group1.pcshop.model.validator.Validator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet (name = "administrator", urlPatterns = "/administrator")
public class AdministratorServlet extends HttpServlet {
    // FIELDS:
    private Validator userValidator;
    private Validator administratorValidator;

    // CONSTRUCTORS:
    public AdministratorServlet() {
        super();

        userValidator = UserValidator.getInstance();
        administratorValidator = AdministratorValidator.getInstance();
    }

    // METHODS:
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validate user
        userValidator.validate(request);

        // Create a validate result set
        Map<String, Object> administratorValidateResult = new HashMap<>();

        // Validate administrator
        administratorValidator.validate(request, administratorValidateResult);

        // Retrieve validating result
        boolean isAdministrator = (boolean)administratorValidateResult.get("isAdministrator");

        // If not administrator
        if (!isAdministrator) {
            RequestDispatcher messageDispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");
            request.setAttribute("message", "Bạn không có quyền truy cập vào trang này!");
            request.setAttribute("color", "red");
            messageDispatcher.forward(request, response);
            return;
        }

        // Get dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/administrator.jsp");

        // Forward
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    /**
     * 
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @return validation status: (true: validation complete | false: validation failed)
     */
    protected boolean validateAdministrator(HttpServletRequest request, HttpServletResponse response) {
        // USER VALIDATION
        
        return true;
    }
}
