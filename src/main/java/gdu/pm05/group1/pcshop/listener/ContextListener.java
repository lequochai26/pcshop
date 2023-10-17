package gdu.pm05.group1.pcshop.listener;

import gdu.pm05.group1.pcshop.interfaces.Destroyable;
import gdu.pm05.group1.pcshop.model.dbhandler.DBHandler;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    // CONSTRUCTORS:
    public ContextListener() {

    }

    // METHODS:
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Initialize DBHandler object
        DBHandler dbHandler = DBHandler.getInstance();

        // Get servlet context
        ServletContext context = event.getServletContext();

        // Set attribute for context
        context.setAttribute("dbHandler", dbHandler);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Get servlet context
        ServletContext context = event.getServletContext();

        // Get destroyable dbHandler
        Destroyable dbHandlerDestroyable = (Destroyable)context.getAttribute("dbHandler");

        // Destroy dbHandler
        dbHandlerDestroyable.destroy();
    }
}
