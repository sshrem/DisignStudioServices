package com.disignstudio.project.api.response;

import java.util.Set;

/**
 * Created by ohadbenporat on 4/14/16.
 */
public class MobileDesignSuppliersResponse {

    private Set<SupplierSummary> suppliers;

    public MobileDesignSuppliersResponse(Set<SupplierSummary> suppliers) {
        this.suppliers = suppliers;
    }

    public Set<SupplierSummary> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<SupplierSummary> suppliers) {
        this.suppliers = suppliers;
    }
}

