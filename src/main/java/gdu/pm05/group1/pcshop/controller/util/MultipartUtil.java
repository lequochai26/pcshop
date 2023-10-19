package gdu.pm05.group1.pcshop.controller.util;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.http.Part;

public class MultipartUtil {
    // STATIC METHODS:
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
    private MultipartUtil() {

    }
}
