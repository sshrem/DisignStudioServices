package com.disignstudio.supplier.db.dao;

import com.disignstudio.supplier.db.bean.SupplierGroup;

/**
 * Created by ohadbenporat on 1/7/16.
 */
public interface ISupplierGroupDao {

    public void saveOrUpdate(SupplierGroup supplier);

    public SupplierGroup findById(long id);
}
