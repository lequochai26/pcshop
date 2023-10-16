package gdu.pm05.group1.pcshop.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notification {
    // STATIC FIELDS:
    private static int curId = 0;
    private static List<Notification> all = new ArrayList<>();

    // STATIC METHODS:
    public static Notification getNotification(int id) {
        // Target declaration
        Notification target = null;

        // Getting
        for (Notification notification : all) {
            if (notification.getId() == id) {
                target = notification;
                break;
            }
        }

        // Return target
        return target;
    }

    // FIELDS:
    private int id;
    private Date date;
    private String title;
    private String content;
    private boolean seen;

    // CONSTRUCTORS:
    public Notification() {
        this.id = curId;
        all.add(this);

        date = new Date();

        curId++;
    }

    public Notification(int id, Date date, String title, String content, boolean seen) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.seen = seen;
        this.date = date;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}