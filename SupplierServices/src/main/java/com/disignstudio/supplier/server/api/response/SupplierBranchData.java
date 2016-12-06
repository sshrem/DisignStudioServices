package com.disignstudio.supplier.server.api.response;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierBranchData {

    private long id;
    private String name;
    private String address;
    private String city;
    private Double lon;
    private Double lat;

    public SupplierBranchData(long id, String name, String address, String city, Double lon, Double lat) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.lon = lon;
        this.lat = lat;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
