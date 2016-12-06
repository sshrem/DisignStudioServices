package com.disignstudio.supplier.db.bean;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public class SupplierGroup {

    private long id;
    private String name;

    public SupplierGroup() {
    }

    public SupplierGroup(long id, String name) {
        this.id = id;
        this.name = name;
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
}
