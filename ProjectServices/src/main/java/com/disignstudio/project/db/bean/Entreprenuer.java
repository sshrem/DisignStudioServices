package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class Entreprenuer {

    private long id;
    private String name;
    private String address;
    private Long contactId;
    private String logo;
    private String about;

    public Entreprenuer() {
    }

    public Entreprenuer(long id, String name, String address, Long contactId, String logo, String about) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactId = contactId;
        this.logo = logo;
        this.about = about;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
