package com.disignstudio.supplier.server.api.response;

import java.util.List;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierGroupData {

    private long id;
    private String name;
    private List<SupplierData> suppliers;

    public SupplierGroupData(long id, String name, List<SupplierData> suppliers) {
        this.id = id;
        this.name = name;
        this.suppliers = suppliers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SupplierData> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierData> suppliers) {
        this.suppliers = suppliers;
    }
}
