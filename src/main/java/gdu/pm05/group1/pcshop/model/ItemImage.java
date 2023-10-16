package gdu.pm05.group1.pcshop.model;

public class ItemImage {
    // FIELDS:
    private int id;
    private byte[] content;
    private Item item;

    // CONSTRUCTORS:
    public ItemImage() {
    }
    public ItemImage(int id, byte[] content, Item item) {
        this.id = id;
        this.content = content;
        this.item = item;
    }

    // METHODS:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
}
