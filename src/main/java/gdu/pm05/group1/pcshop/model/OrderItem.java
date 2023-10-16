package gdu.pm05.group1.pcshop.model;

public class OrderItem {
    // FIELDS:
    private int orderId;
    private String itemId;
    private int amount;

    // CONSTRUCTORS:
    public OrderItem() {

    }

    public OrderItem(int orderId, String itemId, int amount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.amount = amount;
    }

    // METHODS:
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
