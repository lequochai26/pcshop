package gdu.pm05.group1.pcshop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity (name = "Cart")
@Table (name = "Cart")
public class Cart {
    // FIELDS:
    @Id
    @Column (name = "username")
    private String username;

    @OneToOne (optional = false)
    @JoinColumn (name = "username")
    private User user;

    @OneToMany (mappedBy = "cart")
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
