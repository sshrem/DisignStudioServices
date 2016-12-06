package com.disignstudio.supplier.server.api;

import com.disignstudio.supplier.db.bean.Supplier;
import com.disignstudio.supplier.db.bean.SupplierBranch;
import com.disignstudio.supplier.db.bean.SupplierGroup;
import com.disignstudio.supplier.db.bean.SupplierOffering;
import com.disignstudio.supplier.db.dao.ISupplierBranchDao;
import com.disignstudio.supplier.db.dao.ISupplierDao;
import com.disignstudio.supplier.db.dao.ISupplierGroupDao;
import com.disignstudio.supplier.db.dao.ISupplierOfferingDao;
import com.disignstudio.web.response.DesignStudioResponse;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by ohadbenporat on 1/9/16.
 */

@Path("/api/crud")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SupplierServerCRUDAPI {

    private ISupplierGroupDao supplierGroupDao;
    private ISupplierDao supplierDao;
    private ISupplierBranchDao supplierBranchDao;
    private ISupplierOfferingDao supplierOfferingDao;

    @Inject
    public SupplierServerCRUDAPI(ISupplierGroupDao supplierGroupDao, ISupplierDao supplierDao, ISupplierBranchDao supplierBranchDao, ISupplierOfferingDao supplierOfferingDao) {
        this.supplierGroupDao = supplierGroupDao;
        this.supplierDao = supplierDao;
        this.supplierBranchDao = supplierBranchDao;
        this.supplierOfferingDao = supplierOfferingDao;
    }

    @POST
    @Path("/addSupplierGroup")
    public DesignStudioResponse addSupplierGroup(SupplierGroup group) {
        supplierGroupDao.saveOrUpdate(group);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addSupplier")
    public DesignStudioResponse addSupplier(Supplier supplier) {
        supplierDao.saveOrUpdate(supplier);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addSupplierBranch")
    public DesignStudioResponse addSupplierBranch(SupplierBranch branch) {
        supplierBranchDao.saveOrUpdate(branch);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addSupplierOffering")
    public DesignStudioResponse addSupplierOffering(SupplierOffering offering) {
        supplierOfferingDao.saveOrUpdate(offering);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }
}