package gdu.pm05.group1.pcshop.controller.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import gdu.pm05.group1.pcshop.controller.util.enums.UserValidationResult;
import gdu.pm05.group1.pcshop.model.User;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class ServletUtil {
    // STATIC METHODS:
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

    public static void userValidate(
        HttpServletRequest request,
        HttpServletResponse response,
        Map<String, Object> path
    ) {
        userValidate(request, response, false, path);
    }

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
