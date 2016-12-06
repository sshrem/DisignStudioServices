package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public class SupplierOffering {

    private long id;
    private long supplierId;
    private int category;
    private String name;
    private String description;
    private boolean inStock;
    private boolean standard;
    private String imageCode;
    private double price;

    public SupplierOffering() {
    }

    public SupplierOffering(long supplierId, int category, String name, String description, boolean standard, boolean inStock, String imageCode, double price) {
        this(0L, supplierId, category, name, description, standard, inStock, imageCode, price);
    }

    public SupplierOffering(long id, long supplierId, int category, String name, String description, boolean standard, boolean inStock, String imageCode, double price) {
        this.id = id;
        this.supplierId = supplierId;
        this.category = category;
        this.name = name;
        this.description = description;
        this.standard = standard;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }
}
