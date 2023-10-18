package gdu.pm05.group1.pcshop.model.validator;

import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserValidator implements Validator<User> {
    // CONSTRUCTORS:
    private UserValidator() {

    }

    // METHODS:
    @Override
    public void validate(User target, Object... params) {
        // Get request
        HttpServletRequest request = (HttpServletRequest)params[0];

        // Validating
        this.validate(target, request);
    }

    private void validate(User target, HttpServletRequest request) {
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
                else {
                    // Refresh for current user
                    // To be continue...
                }
            }
        }
    }
}
