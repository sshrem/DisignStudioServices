package com.disignstudio.project.admin;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ohadbenporat on 4/11/16.
 */
public class DesignsRawData {

    private Integer offeringId;
    private Long projectId;
    private String aptTmplCode;
    private String designName;
    private Long designerId;
    private Integer room;
    private String designImg;
    private String facebookVideoUrl;

    public DesignsRawData(String line) {

        String[] fields = line.split("\\t");
        this.projectId = convertLong(fields[0]);
        this.aptTmplCode = fields[1];
        this.designName = fields[2];
        this.designerId = convertLong(fields[3]);
        this.designImg = fields[4];
        this.offeringId = convertInt(fields[5]);
        this.room = convertInt(fields[6]);
        this.facebookVideoUrl = fields[7];
    }

    private Integer convertInt(String field) {
        if (StringUtils.isBlank(field)) {
            return null;
        }

        return Integer.parseInt(field);
    }

    private Long convertLong(String field) {
        if (StringUtils.isBlank(field)) {
            return null;
        }

        return Long.parseLong(field);
    }

    public Integer getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(Integer offeringId) {
        this.offeringId = offeringId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getAptTmplCode() {
        return aptTmplCode;
    }

    public void setAptTmplCode(String aptTmplCode) {
        this.aptTmplCode = aptTmplCode;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    public Long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Long designerId) {
        this.designerId = designerId;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getDesignImg() {
        return designImg;
    }

    public void setDesignImg(String designImg) {
        this.designImg = designImg;
    }

    public String getFacebookVideoUrl() {
        return facebookVideoUrl;
    }

    public void setFacebookVideoUrl(String facebookVideoUrl) {
        this.facebookVideoUrl = facebookVideoUrl;
    }
}
