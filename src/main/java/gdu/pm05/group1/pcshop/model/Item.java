package gdu.pm05.group1.pcshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity (name = "Item")
@Table (name = "Item")
public class Item {
    // FIELDS:
    @Id
    @Column (name = "id")
    private String id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "description", nullable = false)
    private String description;

    @Column (name = "price", nullable = false)
    private double price;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "itemTypeId", referencedColumnName = "id")
    private ItemType type;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "item")
    private List<ItemImage> images;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "item")
    private List<CartItem> carts;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "item")
    private List<OrderItem> orders;

    // CONSTRUCTORS:
    public Item() {
    }

    public Item(String id, String name, String description, double price, ItemType type, List<ItemImage> images,
            List<CartItem> carts, List<OrderItem> orders) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.images = images;
        this.carts = carts;
        this.orders = orders;
    }

    // METHODS:
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public ItemType getType() {
        return type;
    }
    public void setType(ItemType type) {
        this.type = type;
    }
    public List<ItemImage> getImages() {
        return images;
    }
    public void setImages(List<ItemImage> images) {
        this.images = images;
    }
    public List<CartItem> getCarts() {
        return carts;
    }
    public void setCarts(List<CartItem> carts) {
        this.carts = carts;
    }
    public List<OrderItem> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
