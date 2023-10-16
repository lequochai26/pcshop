package gdu.pm05.group1.pcshop.model;

public class UserNotification {
    // FIELDS:
    private User user;
    private Notification notification;
    private boolean seen;

    // CONSTRUCTORS:
    public UserNotification() {
    }
    public UserNotification(User user, Notification notification, boolean seen) {
        this.user = user;
        this.notification = notification;
        this.seen = seen;
    }

    // METHODS:
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Notification getNotification() {
        return notification;
    }
    public void setNotification(Notification notification) {
        this.notification = notification;
    }
    public boolean isSeen() {
        return seen;
    }
    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    
}
