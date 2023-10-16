package gdu.pm05.group1.pcshop.model;

import java.util.List;

/*- id: String

- name: String

- items: List<Item> */
public class ItemType {
    // FIELDS:
    private String id;
    private String name;
    private List<Item> items;

    // CONSTRUCTORS:
    public ItemType(List<Item> items) {
        this.items = items;
    }
    public ItemType(String id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
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
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
