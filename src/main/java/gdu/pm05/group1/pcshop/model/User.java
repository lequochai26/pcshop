package gdu.pm05.group1.pcshop.model;

import java.util.List;

import gdu.pm05.group1.pcshop.model.enums.UserPermission;

public class User {
    // FIELDS:
    private String username;
    private String password;
    private UserPermission permission;
    private UserInfo userInfo;
    private List<UserNotification> notifications;
    private Cart cart;
    private List<Order> orders;
    
    // CONSTRUCTORS:
    public User() {

    }

    public User(String username, String password, UserPermission permission, UserInfo userInfo,
            List<UserNotification> notifications, Cart cart, List<Order> orders) {
        this.username = username;
        this.password = password;
        this.permission = permission;
        this.userInfo = userInfo;
        this.notifications = notifications;
        this.cart = cart;
        this.orders = orders;
    }

    // METHODS:
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserPermission getPermission() {
        return permission;
    }
    public void setPermission(UserPermission permission) {
        this.permission = permission;
    }
    public UserInfo getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    public List<UserNotification> getNotifications() {
        return notifications;
    }
    public void setNotifications(List<UserNotification> notifications) {
        this.notifications = notifications;
    }
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
