package com.disignstudio.supplier.cache;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.supplier.db.bean.SupplierGroup;
import com.disignstudio.supplier.db.bean.SupplierOffering;
import com.disignstudio.supplier.db.dao.ISupplierGroupDao;
import com.google.common.cache.CacheLoader;

/**
 * Created by ohadbenporat on 1/8/16.
 */
public class SupplierGroupCacheLoader extends CacheLoader<EntityCacheKey<Long>, SupplierGroup> {

    private ISupplierGroupDao supplierGroupDao;

    public SupplierGroupCacheLoader(ISupplierGroupDao supplierGroupDao) {
        this.supplierGroupDao = supplierGroupDao;
    }

    public SupplierGroup load(EntityCacheKey<Long> key) throws Exception {
        return supplierGroupDao.findById(key.getEntityKey());
    }
}
