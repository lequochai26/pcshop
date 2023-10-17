package gdu.pm05.group1.pcshop.model;

public class OrderItem {
    // FIELDS:
    private Order order;
    private Item item;
    private int amount;

    // CONSTRUCTORS:
    public OrderItem() {
    }
    public OrderItem(Order order, Item item, int amount) {
        this.order = order;
        this.item = item;
        this.amount = amount;
    }

    // METHODS:
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
