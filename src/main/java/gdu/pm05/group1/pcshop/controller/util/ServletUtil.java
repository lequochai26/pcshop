package gdu.pm05.group1.pcshop.controller.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import gdu.pm05.group1.pcshop.controller.util.enums.AdministratorValidationResult;
import gdu.pm05.group1.pcshop.controller.util.enums.UserValidationResult;
import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import gdu.pm05.group1.pcshop.model.enums.UserPermission;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class ServletUtil {
    // STATIC METHODS:
    public static Map<String, Object> administratorValidate(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        return administratorValidate(request, response, true);
    }

    public static Map<String, Object> administratorValidate(
        HttpServletRequest request,
        HttpServletResponse response,
        boolean logout
    ) {
        // Path initialization
        Map<String, Object> path = new HashMap<>();

        // Administrator validation
        administratorValidate(request, response, logout, path);

        // Return path
        return path;
    }

    public static void administratorValidate(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, Object> path
    ) {
        administratorValidate(request, response, true, path);
    }

    public static void administratorValidate(
        HttpServletRequest request,
        HttpServletResponse response,
        boolean logout,
        Map<String, Object> path
    ) {
        // User validation
        userValidate(request, response, logout, path);

        // Get userValidateResult from path
        UserValidationResult userValidateResult = (UserValidationResult)path.get("userValidateResult");

        // User validation failed case
        if (userValidateResult != UserValidationResult.SUCCESSFULLY) {
            path.put("administratorValidateResult", AdministratorValidationResult.USER_VALIDATION_FAILED);
            return;
        }

        // User validation successfully case
        // Get user entity from path
        User user = (User)path.get("user");

        // Check user's permission
        if (user.getPermission() != UserPermission.ADMIN) {
            path.put("administratorValidateResult", AdministratorValidationResult.NOT_ADMINISTRATOR);
            return;
        }

        // Administrator validation successfully
        path.put("administratorValidateResult", AdministratorValidationResult.IS_ADMINISTRATOR);
    }

    /**
     * 
     * @param request Servlet request
     * @param response Servlet response
     * @return Path stores all objects and results during validation processing.
     */
    public static Map<String, Object> userValidate(
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        // Path initialization
        Map<String, Object> path = new HashMap<>();

        // User validate
        userValidate(request, response, path);

        // Return path
        return path;
    }

    /**
     * 
     * @param request Servlet request
     * @param response Servlet response
     * @param logout Logout if already logged-in but not valid
     * @return Path stores all objects and results during validation processing. 
     */
    public static Map<String, Object> userValidate(
        HttpServletRequest request,
        HttpServletResponse response,
        boolean logout
    ) {
        // Path initialization
        Map<String, Object> path = new HashMap<>();

        // User validate
        userValidate(request, response, logout, path);

        // Return path
        return path;
    }

    /**
     * 
     * @param request Servlet request
     * @param response Servlet response
     * @param path Path stores all objects and results during validation processing. 
     */
    public static void userValidate(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, Object> path
    ) {
        userValidate(request, response, true, path);
    }

    /**
     * 
     * @param request Servlet request
     * @param response Servlet response
     * @param logout Logout if already logged-in but not valid
     * @param path Path stores all objects and results during validation processing. 
     */
    public static void userValidate(
        HttpServletRequest request,
        HttpServletResponse response,
        boolean logout,
        Map<String, Object> path
    ) {
        // Get session from request
        HttpSession session = request.getSession(false);

        // Session null case
        if (session == null) {
            path.put("userValidateResult", UserValidationResult.NOT_LOGGED_IN);
            return;
        }

        // Get user attribute from session
        User user = (User)session.getAttribute("user");

        // User null
        if (user == null) {
            path.put("userValidateResult", UserValidationResult.NOT_LOGGED_IN);
            return;
        }

        path.put("user", user);

        // Get context
        ServletContext context = request.getServletContext();
        path.put("context", context);

        // Get dbHandler
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");
        path.put("dbHandler", dbHandler);

        // Store current password
        String password = user.getPassword();
        path.put("password", password);

        // Refresh user
        dbHandler.refresh(user);

        // Check password
        if (!user.getPassword().equals(password)) {
            // Logout or not depending on parameter
            if (logout) {
                session.removeAttribute("user");
            }
            // Set result for this validation
            path.put(
                "userValidateResult",
                UserValidationResult.PASSWORD_NOT_MATCH
            );
            return;
        }

        // Set userValidateResult as true for successfully
        path.put(
            "userValidateResult",
            UserValidationResult.SUCCESSFULLY
        );
    }

    public static String readPartAsString(Part part) {
        // Read part as bytes
        byte[] bContent = readPartAsBytes(part);

        // Convert byte content to string content
        String content = new String(bContent);

        // Return content
        return content;
    }

    public static byte[] readPartAsBytes(Part part) {
        // Get part's input stream
        InputStream input = null;
        try {
            input = part.getInputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Read all bytes
        byte[] content = null;
        try {
            content = input.readAllBytes();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Return content
        return content;
    }

    // CONSTRUCTORS:
    private ServletUtil() {

    }
}
