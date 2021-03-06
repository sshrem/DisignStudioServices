package com.disignstudio.project.api.request;

/**
 * Created by ohadbenporat on 3/31/16.
 */
public class UserActionRequest {

    private String userId;
    private String uuid;
    private Long projectId;
    private Long apartmentTemplateId;
    private Integer roomId;
    private Long designId;
    private String deviceModel;
    private String os;
    private String osVersion;
    private int action;

    public UserActionRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}
