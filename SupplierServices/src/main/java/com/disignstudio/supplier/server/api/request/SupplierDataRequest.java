package com.disignstudio.supplier.server.api.request;

import com.disignstudio.supplier.server.api.ESupplierData;

/**
 * Created by ohadbenporat on 1/11/16.
 */
public class SupplierDataRequest {

    private long id;
    private ESupplierData[] fields;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ESupplierData[] getFields() {
        return fields;
    }

    public void setFields(ESupplierData[] fields) {
        this.fields = fields;
    }
}
