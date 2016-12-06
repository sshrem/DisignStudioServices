package com.disignstudio.project.api.request;

/**
 * Created by ohadbenporat on 5/11/16.
 */
public class DesignSuppliersRequest {

    private long atId; // ApartmentTemplateId
    private long dId; // DesignId
    private boolean useCache = true;

    public long getAtId() {
        return atId;
    }

    public void setAtId(long atId) {
        this.atId = atId;
    }

    public long getdId() {
        return dId;
    }

    public void setdId(long dId) {
        this.dId = dId;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }
}
