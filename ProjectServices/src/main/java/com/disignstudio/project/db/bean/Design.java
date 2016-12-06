package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class Design {

    private long id;
    private long designerId;
    private long apartmentTemplateId;
    private String title;
    private String imagingCode;

    public Design() {
    }

    public Design(long designerId, long apartmentTemplateId, String title, String imagingCode) {
        this(0L, designerId, apartmentTemplateId, title, imagingCode);
    }

    public Design(long id, long designerId, long apartmentTemplateId, String title, String imagingCode) {
        this.id = id;
        this.designerId = designerId;
        this.apartmentTemplateId = apartmentTemplateId;
        this.title = title;
        this.imagingCode = imagingCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(long designerId) {
        this.designerId = designerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagingCode() {
        return imagingCode;
    }

    public void setImagingCode(String imagingCode) {
        this.imagingCode = imagingCode;
    }

    public long getApartmentTemplateId() {
        return apartmentTemplateId;
    }

    public void setApartmentTemplateId(long apartmentTemplateId) {
        this.apartmentTemplateId = apartmentTemplateId;
    }
}
