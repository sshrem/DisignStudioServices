package com.disignstudio.supplier.cache;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.supplier.db.bean.SupplierOffering;
import com.disignstudio.supplier.db.dao.ISupplierOfferingDao;
import com.google.common.cache.CacheLoader;

/**
 * Created by ohadbenporat on 1/8/16.
 */
public class SupplierOfferingCacheLoader extends CacheLoader<EntityCacheKey<Long>, SupplierOffering> {

    private ISupplierOfferingDao supplierOfferingDaoDao;

    public SupplierOfferingCacheLoader(ISupplierOfferingDao supplierOfferingDaoDao) {
        this.supplierOfferingDaoDao = supplierOfferingDaoDao;
    }

    @Override
    public SupplierOffering load(EntityCacheKey<Long> key) throws Exception {
        return supplierOfferingDaoDao.findById(key.getEntityKey());
    }
}
