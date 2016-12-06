package com.disignstudio.supplier.server;

import com.disignstudio.common.configuration.ConfigurationProvider;
import com.disignstudio.supplier.db.dao.*;
import com.disignstudio.supplier.db.mapper.SupplierBranchRSExtractor;
import com.disignstudio.supplier.db.mapper.SupplierGroupRSExtractor;
import com.disignstudio.supplier.db.mapper.SupplierOfferingRSExtractor;
import com.disignstudio.supplier.db.mapper.SupplierRSExtractor;
import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Created by ohadbenporat on 1/9/16.
 */
public class SupplierServicesServerModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(SupplierRSExtractor.class).toInstance(new SupplierRSExtractor());
        binder.bind(SupplierGroupRSExtractor.class).toInstance(new SupplierGroupRSExtractor());
        binder.bind(SupplierBranchRSExtractor.class).toInstance(new SupplierBranchRSExtractor());
        binder.bind(SupplierOfferingRSExtractor.class).toInstance(new SupplierOfferingRSExtractor());

        binder.bind(ConfigurationProvider.class).toInstance(new ConfigurationProvider());
        binder.bind(ISupplierGroupDao.class).to(SupplierGroupDaoImpl.class);
        binder.bind(ISupplierDao.class).to(SupplierDaoImpl.class);
        binder.bind(ISupplierBranchDao.class).to(SupplierBranchDaoImpl.class);
        binder.bind(ISupplierOfferingDao.class).to(SupplierOfferingDaoImpl.class);
    }
}
