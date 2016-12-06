package com.disignstudio.supplier.cache;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.supplier.db.bean.SupplierBranch;
import com.disignstudio.supplier.db.dao.ISupplierBranchDao;
import com.google.common.cache.CacheLoader;

/**
 * Created by ohadbenporat on 1/8/16.
 */
public class SupplierBranchCacheLoader extends CacheLoader<EntityCacheKey<Long>, SupplierBranch> {

    private ISupplierBranchDao supplierBranchDao;

    public SupplierBranchCacheLoader(ISupplierBranchDao supplierBranchDao) {
        this.supplierBranchDao = supplierBranchDao;
    }

    @Override
    public SupplierBranch load(EntityCacheKey<Long> key) throws Exception {
        return supplierBranchDao.findById(key.getEntityKey());
    }
}
