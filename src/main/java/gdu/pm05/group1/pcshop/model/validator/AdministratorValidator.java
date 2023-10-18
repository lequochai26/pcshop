package gdu.pm05.group1.pcshop.model.validator;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import gdu.pm05.group1.pcshop.model.enums.UserPermission;

public class AdministratorValidator implements Validator {
    // CONSTRUCTORS:
    public AdministratorValidator() {

    }

    // METHODS:
    @Override
    public void validate(Object... params) {
        // Get request
        HttpServletRequest request = (HttpServletRequest)params[0];

        // Get validate result
        Map<String, Object> validateResult = (Map<String, Object>)params[1];

        // Validating
        this.validate(request, validateResult);
    }

    private void validate(HttpServletRequest request, Map<String, Object> validateResult) {
        // Get session
        HttpSession session = request.getSession(false);

        // isAdministrator false if session null
        if (session != null) {
            // Get user attribute from session
            User user = (User)session.getAttribute("user");

            // isAdministrator false if user null
            if (user != null) {
                // Get dbHandler
                ServletContext context = request.getServletContext();
                IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

                // Refresh user
                dbHandler.refresh(user);

                // Get user's permission
                UserPermission permission = user.getPermission();

                // isAdministrator true if permission is ADMIN and otherwise, false
                if (permission == UserPermission.ADMIN) {
                    validateResult.put("isAdministrator", true);
                }
                else {
                    validateResult.put(
                        "isAdministrator", false
                    );
                }
            }
            else {
                validateResult.put(
                    "isAdministrator", false
                );
            }
        }
        else {
            // Set validate result of "isAdministrator"
            validateResult.put(
                "isAdministrator", false
            );
        }
    }
}
