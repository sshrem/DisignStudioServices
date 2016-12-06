package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class Designer {

    private long id;
    private long contactId;
    private String image;

    public Designer() {
    }

    public Designer(long id, long contactId, String image) {
        this.id = id;
        this.contactId = contactId;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
