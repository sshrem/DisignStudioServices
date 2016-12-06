package com.disignstudio.project.cache.pojo;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 5/11/16.
 */
public class DesignItemCachedData implements Serializable {

    private long id;
    private int category;
    private String name;
    private long supplierId;
    private long offeringId;
    private boolean isStandard;
    private String supplierName;
    private String supplierLogo;
    private String supplierUrl;
    private int roomId;
    private String imgCode;

    public DesignItemCachedData(long id, int category, String name, long supplierId, boolean isStandard, String supplierName, String supplierLogo, String supplierUrl, int roomId, String imgCode, long offeringId) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.supplierId = supplierId;
        this.isStandard = isStandard;
        this.supplierName = supplierName;
        this.supplierLogo = supplierLogo;
        this.supplierUrl = supplierUrl;
        this.roomId = roomId;
        this.imgCode = imgCode;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
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

    public boolean isStandard() {
        return isStandard;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
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
