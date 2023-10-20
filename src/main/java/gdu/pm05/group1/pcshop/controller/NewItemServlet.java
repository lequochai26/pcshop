package gdu.pm05.group1.pcshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gdu.pm05.group1.pcshop.controller.util.MultipartUtil;
import gdu.pm05.group1.pcshop.model.Item;
import gdu.pm05.group1.pcshop.model.ItemImage;
import gdu.pm05.group1.pcshop.model.ItemType;
import gdu.pm05.group1.pcshop.model.dbhandler.HQLParameter;
import gdu.pm05.group1.pcshop.model.dbhandler.IDBHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet (name = "newitem", urlPatterns = "/newitem")
@MultipartConfig
public class NewItemServlet extends ItemManagementServlet {
    // CONSTRUCTORS:
    public NewItemServlet() {
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

        // Get parts
        Part idPart = request.getPart("id");
        Part namePart = request.getPart("name");
        Part pricePart = request.getPart("price");
        Part descriptionPart = request.getPart("description");
        Part typePart = request.getPart("type");
        Part avatarPart = request.getPart("avatar");
        List<Part> imagesParts = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals("images")) {
                imagesParts.add(part);
            }
        }
        
        // Get parameters from parts
        String id = MultipartUtil.readPartAsString(idPart);
        String name = MultipartUtil.readPartAsString(namePart);
        String priceStr = MultipartUtil.readPartAsString(pricePart);
        double price = Double.parseDouble(priceStr);
        String description = MultipartUtil.readPartAsString(descriptionPart);
        String typeId = MultipartUtil.readPartAsString(typePart);
        byte[] avatarByte = MultipartUtil.readPartAsBytes(avatarPart);
        List<byte[]> imagesBytes = new ArrayList<>();
        for (Part imagesPart : imagesParts) {
            imagesBytes.add(
                MultipartUtil.readPartAsBytes(imagesPart)
            );
        }

        // Get dbHandler
        ServletContext context = request.getServletContext();
        IDBHandler dbHandler = (IDBHandler)context.getAttribute("dbHandler");

        // Try get item with given id from database
        Item item = dbHandler.get(
            Item.class,
            new HQLParameter("id", id)
        );

        // Item already exist case
        if (item != null) {
            request.setAttribute(
                "message",
                "Đã tồn tại vật phẩm với id '@id' !".replace("@id", id)
            );
            super.doGet(request, response);
            return;
        }

        // Create new Item
        item = new Item();

        // Create new avatar
        ItemImage avatar = new ItemImage();

        // Create a list of ItemImage named images
        Set<ItemImage> images = new HashSet<>();

        // Get item type
        ItemType type = dbHandler.get(
            ItemType.class,
            new HQLParameter("id", typeId)
        );

        // Item properties assigning
        item.setId(id);
        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
        item.setType(type);
        item.setAvatar(avatar);

        // Avatar properties assigning
        avatar.setContent(avatarByte);

        // Images properties assigning
        for (byte[] imageByte : imagesBytes) {
            ItemImage image = new ItemImage();
            image.setContent(imageByte);
            image.setItem(item);
            images.add(image);
        }

        // Save all related entities
        dbHandler.save(
            avatar, item
        );

        for (ItemImage image : images) {
            dbHandler.save(image);
        }

        // Send redirect to itemsmanagement page
        response.sendRedirect("itemsmanagement");
    }
}
