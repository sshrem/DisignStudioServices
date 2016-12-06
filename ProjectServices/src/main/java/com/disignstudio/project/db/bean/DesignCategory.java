package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 5/9/16.
 */
public class DesignCategory {

    private long id;
    private long designId;
    private int categoryId;
    private long supplierId;
    private boolean isStandard;

    public DesignCategory(long id, long designId, int categoryId, long supplierId, boolean isStandard) {
        this.id = id;
        this.designId = designId;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.isStandard = isStandard;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public boolean isStandard() {
        return isStandard;
    }

    public void setStandard(boolean standard) {
        isStandard = standard;
    }
}
