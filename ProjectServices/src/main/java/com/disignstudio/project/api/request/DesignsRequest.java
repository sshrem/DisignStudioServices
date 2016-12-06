package com.disignstudio.project.api.request;

import java.util.List;

/**
 * Created by ohadbenporat on 5/10/16.
 */
public class DesignsRequest {

    private long projId; // ProjectId
    private long atId; // Apartment Template Id
    private DesignSupplierFilter supplierFilter;
    private List<DesignItemFilter> itemFilters;
    private boolean useCache = true;

    public DesignsRequest() {
    }

    public DesignsRequest(long projId, long atId, DesignSupplierFilter supplierFilter, List<DesignItemFilter> itemFilters, boolean useCache) {
        this.projId = projId;
        this.atId = atId;
        this.supplierFilter = supplierFilter;
        this.itemFilters = itemFilters;
        this.useCache = useCache;
    }

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

    public List<DesignItemFilter> getItemFilters() {
        return itemFilters;
    }

    public void setItemFilters(List<DesignItemFilter> itemFilters) {
        this.itemFilters = itemFilters;
    }

    public DesignSupplierFilter getSupplierFilter() {

        return supplierFilter;
    }

    public void setSupplierFilter(DesignSupplierFilter supplierFilter) {
        this.supplierFilter = supplierFilter;
    }
}
