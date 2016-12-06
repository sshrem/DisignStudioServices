package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 5/4/16.
 */
public class DesignSupplier {

    private long id;
    private long designId;
    private long supplierId;

    public DesignSupplier(long id, long designId, long supplierId) {
        this.id = id;
        this.designId = designId;
        this.supplierId = supplierId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDesignId() {
        return designId;
    }

    public void setDesignId(long designId) {
        this.designId = designId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
}
