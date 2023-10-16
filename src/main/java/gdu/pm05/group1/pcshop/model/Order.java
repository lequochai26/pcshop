package gdu.pm05.group1.pcshop.model;

import java.util.List;

import gdu.pm05.group1.pcshop.model.enums.OrderStatus;

public class Order {
    // FIELDS:
    private int id;
    private double totalPrice;
    private OrderStatus status;
    private List<OrderItem> items;
    private User user;

    // CONSTRUCTORS:
    public Order() {
    }

    public Order(int id, double totalPrice, OrderStatus status, List<OrderItem> items, User user) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = items;
        this.user = user;
    }

    // METHODS:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
