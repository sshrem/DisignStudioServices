package com.disignstudio.supplier.db.dao;

import com.disignstudio.supplier.db.bean.SupplierBranch;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public interface ISupplierBranchDao {

    public void saveOrUpdate(SupplierBranch supplierBranch);

    public SupplierBranch findById(long id);
}
