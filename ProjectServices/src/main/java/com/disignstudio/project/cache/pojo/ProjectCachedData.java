package com.disignstudio.project.cache.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ohadbenporat on 5/10/16.
 */
public class ProjectCachedData implements Serializable {

    private long projectId;
    private String code;
    private String name;
    private String about;
    private String location;
    private double lon;
    private double lat;
    private String logo;
    private String imageUrl;
    private String salesPhone;
    private int countryId;
    private int cityId;
    private EntrepreneurCachedData entrepreneurCachedData;
    private List<String> features;
    private List<ApartmentTemplateCachedData> apartmentTemplateCachedData;

    public ProjectCachedData(long projectId, String code, String name, String about, String location, double lon, double lat, String logo, String imageUrl, String salesPhone, int countryId, int cityId, EntrepreneurCachedData entrepreneurCachedData, List<String> features, List<ApartmentTemplateCachedData> apartmentTemplateCachedData) {
        this.projectId = projectId;
        this.code = code;
        this.name = name;
        this.about = about;
        this.location = location;
        this.lon = lon;
        this.lat = lat;
        this.logo = logo;
        this.imageUrl = imageUrl;
        this.salesPhone = salesPhone;
        this.countryId = countryId;
        this.cityId = cityId;
        this.entrepreneurCachedData = entrepreneurCachedData;
        this.features = features;
        this.apartmentTemplateCachedData = apartmentTemplateCachedData;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSalesPhone() {
        return salesPhone;
    }

    public void setSalesPhone(String salesPhone) {
        this.salesPhone = salesPhone;
    }

    public EntrepreneurCachedData getEntrepreneurCachedData() {
        return entrepreneurCachedData;
    }

    public void setEntrepreneurCachedData(EntrepreneurCachedData entrepreneurCachedData) {
        this.entrepreneurCachedData = entrepreneurCachedData;
    }

    public List<ApartmentTemplateCachedData> getApartmentTemplateCachedData() {
        return apartmentTemplateCachedData;
    }

    public void setApartmentTemplateCachedData(List<ApartmentTemplateCachedData> apartmentTemplateCachedData) {
        this.apartmentTemplateCachedData = apartmentTemplateCachedData;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
