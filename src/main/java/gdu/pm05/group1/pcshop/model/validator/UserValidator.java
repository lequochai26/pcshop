package gdu.pm05.group1.pcshop.model.validator;

import java.util.Map;

import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserValidator implements Validator {
    // STATIC FIELDS:
    private static UserValidator instance = new UserValidator();

    // STATIC METHODS:
    public static UserValidator getInstance() {
        return instance;
    }

    // CONSTRUCTORS:
    private UserValidator() {

    }

    // METHODS:
    @Override
    public void validate(Map<String, Object> validationResult, Object... params) {
        // Get request
        HttpServletRequest request = (HttpServletRequest)params[0];

        // Validating
        this.validate(validationResult, request);
    }

    private void validate(Map<String, Object> validationResult, HttpServletRequest request) {
        // Set default userValid for validationResult
        validationResult.put("userValid", false);

        // Get session
        HttpSession session = request.getSession(false);

        // Bypass if session null
        if (session != null) {
            // Get user attribute from session
            User user = (User)session.getAttribute("user");

            // Bypass if user null
            if (user != null) {
                // Get context
                ServletContext context = request.getServletContext();

                // Get dbHandler
                IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

                // Get latest user data
                User latestUserData=  dbHandler.get(
                    User.class, user
                );

                // Status declaration
                boolean valid = true;

                // Validating
                if (latestUserData != null) {
                    if (!latestUserData.getPassword().equals(user.getPassword())) {
                        valid = false;
                    }
                }
                else {
                    valid = false;
                }

                // Not valid
                if (!valid) {
                    // Remove user attribute from session
                    session.removeAttribute("user");
                }
                // Valid
                else {
                    // Refresh for current user
                    dbHandler.refresh(user);

                    // Update userValid for validationResult
                    validationResult.put("userValid", true);
                }
            }
        }
    }
}
