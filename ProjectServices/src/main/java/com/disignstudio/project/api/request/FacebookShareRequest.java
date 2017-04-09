package com.disignstudio.project.api.request;

/**
 * Created by shrem on 4/9/17.
 */
public class FacebookShareRequest {
    private String userId;
    private String entrepreneurUserId;
    private Long projectId;
    private Long apartmentTemplateId;
    private Integer roomId;
    private Long designId;

    public FacebookShareRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public String getEntrepreneurUserId() {
        return entrepreneurUserId;
    }

    public void setEntrepreneurUserId(String entrepreneurUserId) {
        this.entrepreneurUserId = entrepreneurUserId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getApartmentTemplateId() {
        return apartmentTemplateId;
    }

    public void setApartmentTemplateId(Long apartmentTemplateId) {
        this.apartmentTemplateId = apartmentTemplateId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Long getDesignId() {
        return designId;
    }

    public void setDesignId(Long designId) {
        this.designId = designId;
    }
}
