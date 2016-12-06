package com.disignstudio.supplier.server.api.response;

import java.util.List;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierDataResponse {

    private List<SupplierGroupData> groups;

    public SupplierDataResponse(List<SupplierGroupData> groups) {
        this.groups = groups;
    }

    public List<SupplierGroupData> getGroups() {
        return groups;
    }

    public void setGroups(List<SupplierGroupData> groups) {
        this.groups = groups;
    }
}
