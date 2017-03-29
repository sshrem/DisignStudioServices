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
    private String facebookVideoUrl;

    public Design() {
    }

    public Design(long designerId, long apartmentTemplateId, String title, String imagingCode, String facebookVideoUrl) {
        this(0L, designerId, apartmentTemplateId, title, imagingCode, facebookVideoUrl);
    }

    public Design(long id, long designerId, long apartmentTemplateId, String title, String imagingCode, String facebookVideoUrl) {
        this.id = id;
        this.designerId = designerId;
        this.apartmentTemplateId = apartmentTemplateId;
        this.title = title;
        this.imagingCode = imagingCode;
        this.facebookVideoUrl = facebookVideoUrl;
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

    public String getFacebookVideoUrl() {
        return facebookVideoUrl;
    }

    public void setFacebookVideoUrl(String facebookVideoUrl) {
        this.facebookVideoUrl = facebookVideoUrl;
    }

    @Override
    public String toString() {
        return "Design{" +
                "id=" + id +
                ", designerId=" + designerId +
                ", apartmentTemplateId=" + apartmentTemplateId +
                ", title='" + title + '\'' +
                ", imagingCode='" + imagingCode + '\'' +
                ", facebookVideoUrl='" + facebookVideoUrl + '\'' +
                '}';
    }
}
