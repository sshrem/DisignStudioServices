package com.disignstudio.supplier.cache;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.supplier.db.bean.Supplier;
import com.disignstudio.supplier.db.dao.ISupplierDao;
import com.google.common.cache.CacheLoader;
import com.google.inject.Inject;

/**
 * Created by ohadbenporat on 1/8/16.
 */
public class SupplierCacheLoader extends CacheLoader<EntityCacheKey<Long>, Supplier> {

    private ISupplierDao supplierDao;

    @Inject
    public SupplierCacheLoader(ISupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    @Override
    public Supplier load(EntityCacheKey<Long> key) throws Exception {
        return supplierDao.findById(key.getEntityKey());
    }
}
