package gdu.pm05.group1.pcshop.model;

public class CartItem {
    // FIELDS:
    private Cart cart;
    private Item item;
    private int amount;

    // CONSTRUCTORS:
    public CartItem() {
    }
    public CartItem(Cart cart, Item item, int amount) {
        this.cart = cart;
        this.item = item;
        this.amount = amount;
    }

    // METHODS:
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
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
