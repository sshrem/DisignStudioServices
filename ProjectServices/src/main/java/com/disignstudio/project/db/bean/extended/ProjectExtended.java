package com.disignstudio.project.db.bean.extended;

import com.disignstudio.project.db.bean.Project;

/**
 * Created by ohadbenporat on 2/7/16.
 */
public class ProjectExtended extends Project {

    private String city;
    private String country;

    public ProjectExtended(long id, String code, long entrepreneur, String name, String address, int cityId, int countryId,
                           double lon, double lat, boolean isActive, String city, String country, String logo, String about, Long salesContact) {
        super(id, code, entrepreneur, name, address, cityId, countryId, lon, lat, isActive, logo, about, salesContact);
        this.city = city;
        this.country = country;
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
}
