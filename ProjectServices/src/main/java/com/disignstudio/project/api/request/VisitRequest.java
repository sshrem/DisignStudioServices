package com.disignstudio.project.api.request;

/**
 * Created by shrem on 4/18/17.
 */
public class VisitRequest {
    private String userId;
    private String entrepreneurUserId;
    private Long projectId;
    private String page;

    public VisitRequest() {
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
