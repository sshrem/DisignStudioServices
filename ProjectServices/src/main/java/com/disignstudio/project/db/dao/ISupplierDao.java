package com.disignstudio.project.db.dao;


import com.disignstudio.project.db.bean.Supplier;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public interface ISupplierDao {

    public long saveOrUpdate(Supplier supplier);

    public Supplier findById(long id);
}
