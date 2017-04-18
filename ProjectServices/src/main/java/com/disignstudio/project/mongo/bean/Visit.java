package com.disignstudio.project.mongo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by shrem on 4/18/17.
 */
@Document(collection = "visits")
public class Visit {
    @Id
    private String id;
    private long timestamp;
    private String page;
    private String userId;
    private String entrepreneurUserId;
    private Long entrepreneurId;
    private Long projectId;
    private Integer countryId;
    private Integer cityId;
    private String ipAddress;
    private String userAgent;

    public Visit(long timestamp, String page, String userId, String entrepreneurUserId, Long entrepreneurId, Long projectId, Integer countryId, Integer cityId, String ipAddress, String userAgent) {
        this.timestamp = timestamp;
        this.page = page;
        this.userId = userId;
        this.entrepreneurUserId = entrepreneurUserId;
        this.entrepreneurId = entrepreneurId;
        this.projectId = projectId;
        this.countryId = countryId;
        this.cityId = cityId;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEntrepreneurUserId() {
        return entrepreneurUserId;
    }

    public void setEntrepreneurUserId(String entrepreneurUserId) {
        this.entrepreneurUserId = entrepreneurUserId;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
