package com.disignstudio.project.api.request;

/**
 * Created by ohadbenporat on 5/17/16.
 */
public class DesignsFiltersRequest {

    private long projId;
    private long atId;
    private boolean useCache = true;

    public long getProjId() {
        return projId;
    }

    public void setProjId(long projId) {
        this.projId = projId;
    }

    public long getAtId() {
        return atId;
    }

    public void setAtId(long atId) {
        this.atId = atId;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }
}
