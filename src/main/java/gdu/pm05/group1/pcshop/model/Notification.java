package gdu.pm05.group1.pcshop.model;

import java.util.Date;
import java.util.List;

// - id: int

// - date: Date

// - title: String

// - content: String

// - users: List<User>

public class Notification {
    // FIELDS:
    private int id;
    private Date date;
    private String title;
    private String content;
    private List<UserNotification> users;

    // CONSTRUCTORS:
    public Notification() {
    }

    public Notification(int id, Date date, String title, String content, List<UserNotification> users) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.users = users;
    }

    // METHODS:
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public List<UserNotification> getUsers() {
        return users;
    }
    public void setUsers(List<UserNotification> users) {
        this.users = users;
    }
}
