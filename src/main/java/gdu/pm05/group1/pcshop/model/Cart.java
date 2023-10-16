package gdu.pm05.group1.pcshop.model;

import java.util.List;

public class Cart {
    // FIELDS:
    private String username;
    private User user;
    private List<CartItem> items;

    // CONSTRUCTORS:
    public Cart() {
    }
    public Cart(String username, User user, List<CartItem> items) {
        this.username = username;
        this.user = user;
        this.items = items;
    }

    // METHODS:
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<CartItem> getItems() {
        return items;
    }
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
