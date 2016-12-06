package com.disignstudio.project.db.bean;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class Project {

    private long id;
    private String code;
    private long entrepreneur;
    private String name;
    private String address;
    private int cityId;
    private int countryId;
    private double lon;
    private double lat;
    private boolean isActive;
    private String logo;
    private String about;
    private Long salesContactId;

    public Project() {
    }

    public Project(long id,String code, long entrepreneur, String name, String address, int cityId,
                   int countryId, double lon, double lat, boolean isActive, String logo, String about, Long salesContactId) {
        this.id = id;
        this.code = code;
        this.entrepreneur = entrepreneur;
        this.name = name;
        this.address = address;
        this.cityId = cityId;
        this.countryId = countryId;
        this.lon = lon;
        this.lat = lat;
        this.isActive = isActive;
        this.logo = logo;
        this.about = about;
        this.salesContactId = salesContactId;
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

    public long getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(long entrepreneur) {
        this.entrepreneur = entrepreneur;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
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

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getSalesContactId() {
        return salesContactId;
    }

    public void setSalesContactId(Long salesContactId) {
        this.salesContactId = salesContactId;
    }
}
