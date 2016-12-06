package com.disignstudio.project.api.request;

/**
 * Created by ohadbenporat on 3/31/16.
 */
public class ImagingViewRequest {

    private String userId;
    private String uuid;
    private Long projectId;
    private Long apartmentTemplateId;
    private Long designId;
    private String deviceModel;
    private String os;
    private String osVersion;
    private Integer source; // Mobile / Facebook etc

    public ImagingViewRequest() {
    }

    public ImagingViewRequest(String userId, String uuid, Long projectId, Long apartmentTemplateId, Long designId, String deviceModel, String os, String osVersion, Integer source) {
        this.userId = userId;
        this.uuid = uuid;
        this.projectId = projectId;
        this.apartmentTemplateId = apartmentTemplateId;
        this.designId = designId;
        this.deviceModel = deviceModel;
        this.os = os;
        this.osVersion = osVersion;
        this.source = source;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
