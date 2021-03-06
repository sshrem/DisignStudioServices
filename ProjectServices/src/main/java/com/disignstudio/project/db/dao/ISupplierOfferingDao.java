package com.disignstudio.project.db.dao;

import com.disignstudio.project.db.bean.SupplierOffering;

import java.util.List;

/**
 * Created by ohadbenporat on 1/3/16.
 */
public interface ISupplierOfferingDao {

    public long saveOrUpdate(SupplierOffering offering);

    public SupplierOffering findById(long id);

    List<SupplierOffering> findByIds(List<Long> offeringIds);
}
