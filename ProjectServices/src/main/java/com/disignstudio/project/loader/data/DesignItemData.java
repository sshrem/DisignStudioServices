package com.disignstudio.project.loader.data;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignItemData implements Serializable {


    private long id;
    private String name;
    private String imgCode;
    private int category;
    private double price;
    private Long supplierId;
    private long offeringId;
    private String supplierName;
    private String supplierDisplayName;
    private String supplierLogo;
    private String supplierUrl;
    private boolean isStandard;
    private int roomId;

    public DesignItemData(long id, String name, String imgCode, int category, double price, String supplierName, String supplierLogo, String supplierUrl,
                          Long supplierId, String supplierDisplayName, boolean isStandard, int roomId, long offeringId) {
        this.id = id;
        this.name = name;
        this.supplierDisplayName = supplierDisplayName;
        this.imgCode = imgCode;
        this.category = category;
        this.price = price;
        this.supplierName = supplierName;
        this.supplierLogo = supplierLogo;
        this.supplierUrl = supplierUrl;
        this.supplierId = supplierId;
        this.isStandard = isStandard;
        this.roomId = roomId;
        this.offeringId = offeringId;
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

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierLogo() {
        return supplierLogo;
    }

    public void setSupplierLogo(String supplierLogo) {
        this.supplierLogo = supplierLogo;
    }

    public String getSupplierUrl() {
        return supplierUrl;
    }

    public void setSupplierUrl(String supplierUrl) {
        this.supplierUrl = supplierUrl;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierDisplayName() {
        return supplierDisplayName;
    }

    public void setSupplierDisplayName(String supplierDisplayName) {
        this.supplierDisplayName = supplierDisplayName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isStandard() {
        return isStandard;
    }

    public void setStandard(boolean standard) {
        isStandard = standard;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(long offeringId) {
        this.offeringId = offeringId;
    }
}
