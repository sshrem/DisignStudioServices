package com.disignstudio.project.api.request;

/**
 * Created by ohadbenporat on 5/10/16.
 */
public class ProjectRequest {

    private long projId;
    private boolean useCache = true;

    public ProjectRequest() {
    }

    public ProjectRequest(long projId, boolean useCache) {
        this.projId = projId;
        this.useCache = useCache;
    }

    public long getProjId() {
        return projId;
    }

    public void setProjId(long projId) {
        this.projId = projId;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }
}
