package com.disignstudio.project.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ohadbenporat on 2/2/16.
 */
public class MobileProjectResponse implements Serializable {

    private String title;
    private String about;
    private String location;
    private double lon;
    private double lat;
    private String logo;
    private String image;
    private String salesPhone;
    private EntrepreneurSummary entrepreneur;
    private List<String> features;
    private List<ApartmentSummary> apartments;

    public MobileProjectResponse(String title, String about, String location, double lon, double lat,
                                 String logo, String image, String salesPhone, EntrepreneurSummary entrepreneur, List<String> features, List<ApartmentSummary> apartments) {
        this.title = title;
        this.about = about;
        this.location = location;
        this.lon = lon;
        this.lat = lat;
        this.logo = logo;
        this.image = image;
        this.salesPhone = salesPhone;
        this.entrepreneur = entrepreneur;
        this.features = features;
        this.apartments = apartments;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EntrepreneurSummary getEntrepreneur() {
        return entrepreneur;
    }

    public void setEntrepreneur(EntrepreneurSummary entrepreneur) {
        this.entrepreneur = entrepreneur;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<ApartmentSummary> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentSummary> apartments) {
        this.apartments = apartments;
    }

    public String getSalesPhone() {
        return salesPhone;
    }

    public void setSalesPhone(String salesPhone) {
        this.salesPhone = salesPhone;
    }
}
