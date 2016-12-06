package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public class ProjectFeature {

    private long projectId;
    private int featureId;
    private String featureDescription;
    private String featureCode;

    public ProjectFeature() {
    }

    public ProjectFeature(long projectId, int featureId, String featureDescription, String featureCode) {
        this.projectId = projectId;
        this.featureId = featureId;
        this.featureDescription = featureDescription;
        this.featureCode = featureCode;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }
}
