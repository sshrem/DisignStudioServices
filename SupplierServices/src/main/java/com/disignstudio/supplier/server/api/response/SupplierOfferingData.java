package com.disignstudio.supplier.server.api.response;

import java.math.BigDecimal;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierOfferingData {

    private long id;
    private String name;
    private String description;
    private boolean inStock;
    private String imageCode;
    private BigDecimal price;

    public SupplierOfferingData(long id, String name, String description, boolean inStock, String imageCode, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inStock = inStock;
        this.imageCode = imageCode;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
