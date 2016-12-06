package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class Supplier {

    private long id;
    private String name;
    private String address;
    private int countryId;
    private long contactId;
    private String logo;
    private String url;
    private String displayName;

    public Supplier() {
    }

    public Supplier(long id, String name, String displayName, String address, int countryId, long contactId, String logo, String url) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.address = address;
        this.countryId = countryId;
        this.contactId = contactId;
        this.logo = logo;
        this.url = url;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
