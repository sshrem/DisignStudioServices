package com.disignstudio.project.api;

import com.disignstudio.project.db.bean.*;
import com.disignstudio.project.db.dao.*;
import com.disignstudio.web.response.DesignStudioResponse;
import com.google.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by ohadbenporat on 1/15/16.
 */
@Path("/api/crud/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProjectsCRUDAPI {

    private IEntrepreneurDao entrepreneurDao;
    private IProjectDao projectDao;
    private IApartmentTemplateDao apartmentTemplateDao;
    private IDesignerDao designerDao;
    private IDesignDao designDao;
    private IProjectFeatureDao projectFeatureDao;
    private IContactDao contactDao;
    private ISupplierDao supplierDao;
    private ISupplierOfferingDao supplierOfferingDao;
    private IDesignItemDao designItemDao;

    @Inject
    public ProjectsCRUDAPI(IEntrepreneurDao entrepreneurDao, IProjectDao projectDao,
                           IApartmentTemplateDao apartmentTemplateDao,
                           IDesignerDao designerDao, IDesignDao designDao, IContactDao contactDao,
                           IProjectFeatureDao projectFeatureDao, ISupplierDao supplierDao,
                           ISupplierOfferingDao supplierOfferingDao, IDesignItemDao designItemDao) {

        this.entrepreneurDao = entrepreneurDao;
        this.projectDao = projectDao;
        this.apartmentTemplateDao = apartmentTemplateDao;
        this.designerDao = designerDao;
        this.designDao = designDao;
        this.projectFeatureDao = projectFeatureDao;
        this.contactDao = contactDao;
        this.supplierDao = supplierDao;
        this.supplierOfferingDao = supplierOfferingDao;
        this.designItemDao = designItemDao;
    }

    @POST
    @Path("/addEntrepreneur")
    public DesignStudioResponse addCompany(Entreprenuer entreprenuer) {
        entrepreneurDao.saveOrUpdate(entreprenuer);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addProject")
    public DesignStudioResponse addProject(Project project) {
        projectDao.saveOrUpdate(project);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addApartmentTemplate")
    public DesignStudioResponse addApartmentTemplate(ApartmentTemplate apartmentTemplate) {
        apartmentTemplateDao.saveOrUpdate(apartmentTemplate);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addDesigner")
    public DesignStudioResponse addDesigner(Designer designer) {
        designerDao.saveOrUpdate(designer);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addDesign")
    public DesignStudioResponse addDesign(Design design) {
        designDao.saveOrUpdate(design);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addProjectFeature")
    public DesignStudioResponse addDesign(ProjectFeature projectFeature) {
        projectFeatureDao.saveOrUpdate(projectFeature);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addContact")
    public DesignStudioResponse addContact(Contact contact) {
        contactDao.saveOrUpdate(contact);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addSupplier")
    public DesignStudioResponse addSupplier(Supplier supplier) {
        supplierDao.saveOrUpdate(supplier);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addSupplierOffering")
    public DesignStudioResponse addSupplier(SupplierOffering supplierOffering) {
        supplierOfferingDao.saveOrUpdate(supplierOffering);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }

    @POST
    @Path("/addDesignItem")
    public DesignStudioResponse addDesignItem(DesignItem designItem) {
        designItemDao.saveOrUpdate(designItem);
        return new DesignStudioResponse<>(true, DesignStudioResponse.SUCCESS_MSG, null);
    }
}
