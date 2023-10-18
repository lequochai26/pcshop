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
        // Validation
        boolean isValid = this.validateAdministrator(request, response);

        // VALIDATION FAILED
        if (!isValid) {
            return;
        }

        // VALIDATION SUCCESSFULLY
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
     * @throws IOException
     * @throws ServletException
     */
    protected boolean validateAdministrator(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a validation result map
        Map<String, Object> validationResult = new HashMap<>();

        // USER VALIDATION
        userValidator.validate(validationResult, request);

        // Get userValid result from validation result map
        boolean userValid = (boolean)validationResult.get("userValid");

        // User validation failed
        if (!userValid) {
            // Format message
            request.setAttribute("color", "red");
            request.setAttribute(
                "message",
                "Bạn chưa đăng nhập hoặc phiên đăng nhập của bạn không hợp lệ! Vui lòng đăng nhập hoặc đăng nhập lại!"
            );

            // Get dispatcher
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");

            // Forward
            dispatcher.forward(request, response);

            return false;
        }

        // ADMINISTRATOR VALIDATION
        // Clear validation result map
        validationResult.clear();

        // Administrator validation
        administratorValidator.validate(validationResult, request);

        // Retrieve isAdministrator result from administrator validator
        boolean isAdministrator = (boolean)validationResult.get("isAdministrator");

        // Administrator validation failed
        if (!isAdministrator) {
            // Format message
            request.setAttribute("color", "red");
            request.setAttribute(
                "message",
                "Bạn không có quyền truy cập vào trang này!"
            );

            // Get dispatcher
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/message.jsp");

            // Forward
            dispatcher.forward(request, response);

            return false;
        }

        // VALIDATION SUCCESSFULLY
        return true;
    }
}
