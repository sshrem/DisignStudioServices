package com.disignstudio.supplier.db.bean;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class SupplierBranch {

    private long id;
    private long supplierId;
    private String name;
    private String address;
    private Integer city;
    private Double lon;
    private Double lat;

    public SupplierBranch() {
    }

    public SupplierBranch(long id, long supplierId, String name, String address, Integer city, Double lon, Double lat) {
        this.id = id;
        this.supplierId = supplierId;
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

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
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

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
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
