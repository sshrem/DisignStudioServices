package com.disignstudio.supplier.server.api.response;

import java.util.List;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierData {

    private long id;
    private String name;
    private String address;
    private List<SupplierBranchData> branches;
    private List<SupplierOfferingData> offers;

    public SupplierData(long id, String name, String address, List<SupplierBranchData> branches, List<SupplierOfferingData> offers) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.branches = branches;
        this.offers = offers;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SupplierBranchData> getBranches() {
        return branches;
    }

    public void setBranches(List<SupplierBranchData> branches) {
        this.branches = branches;
    }

    public List<SupplierOfferingData> getOffers() {
        return offers;
    }

    public void setOffers(List<SupplierOfferingData> offers) {
        this.offers = offers;
    }
}
