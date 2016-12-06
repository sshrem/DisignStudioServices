package com.disignstudio.project.loader.data;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 2/5/16.
 */
public class ProjectData implements Serializable {

    private long id;
    private String code;
    private String name;
    private String address;
    private String city;
    private String country;
    private Integer cityId;
    private Integer countryId;
    private double lon;
    private double lat;
    private boolean isActive;
    private String logo;
    private String about;
    private ContactData salesContact;

    public ProjectData(long id, String code, String name, String address, String city, String country, Integer cityId, Integer countryId, double lon, double lat, boolean isActive, String logo, String about, ContactData salesContact) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.cityId = cityId;
        this.countryId = countryId;
        this.lon = lon;
        this.lat = lat;
        this.isActive = isActive;
        this.logo = logo;
        this.about = about;
        this.salesContact = salesContact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public ContactData getSalesContact() {
        return salesContact;
    }

    public void setSalesContact(ContactData salesContact) {
        this.salesContact = salesContact;
    }
}
