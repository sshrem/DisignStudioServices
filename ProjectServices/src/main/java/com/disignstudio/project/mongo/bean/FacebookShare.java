package com.disignstudio.project.mongo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by shrem on 4/9/17.
 */
@Document(collection = "facebook_share")
public class FacebookShare {
    @Id
    private String id;
    private long timestamp;
    private String userId;
    private String uuid;
    private Long entrepreneurId;
    private Long projectId;
    private Integer countryId;
    private Integer cityId;
    private Long apartmentTemplateId;
    private Integer roomId;
    private Integer numOfRooms;
    private String ipAddress;
    private Long designId;
    private String os;
    private String deviceModel;
    private String osVersion;

    public FacebookShare(long timestamp, String userId, String uuid, Long entrepreneurId, Long projectId, Integer countryId, Integer cityId, Long apartmentTemplateId, Integer roomId, Integer numOfRooms, String ipAddress, Long designId, String os, String deviceModel, String osVersion) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.uuid = uuid;
        this.entrepreneurId = entrepreneurId;
        this.projectId = projectId;
        this.countryId = countryId;
        this.cityId = cityId;
        this.apartmentTemplateId = apartmentTemplateId;
        this.roomId = roomId;
        this.numOfRooms = numOfRooms;
        this.ipAddress = ipAddress;
        this.designId = designId;
        this.os = os;
        this.deviceModel = deviceModel;
        this.osVersion = osVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public Long getEntrepreneurId() {
        return entrepreneurId;
    }

    public void setEntrepreneurId(Long entrepreneurId) {
        this.entrepreneurId = entrepreneurId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public Integer getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(Integer numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
}
