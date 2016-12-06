package com.disignstudio.project.api.response;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignItemSummary {

    private long offeringId;
    private String name;
    private String imgCode;
    private long supplierId;

    public DesignItemSummary(long offeringId, String name, String imgCode, long supplierId) {
        this.offeringId = offeringId;
        this.name = name;
        this.imgCode = imgCode;
        this.supplierId = supplierId;
    }

    public long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(long offeringId) {
        this.offeringId = offeringId;
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

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
}
