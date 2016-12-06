package com.disignstudio.project.stats;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.project.api.request.ImagingViewRequest;
import com.disignstudio.project.api.request.UserActionRequest;
import com.disignstudio.project.api.request.ViewSupplierRequest;
import com.disignstudio.project.cache.ProjectCacheLoader;
import com.disignstudio.project.cache.pojo.ApartmentTemplateCachedData;
import com.disignstudio.project.cache.pojo.ProjectCachedData;
import com.disignstudio.project.loader.data.ApartmentTemplateData;
import com.disignstudio.project.loader.data.ProjectDataWrapper;
import com.disignstudio.project.mongo.bean.ImagingView;
import com.disignstudio.project.mongo.bean.UserAction;
import com.disignstudio.project.mongo.bean.ViewSupplier;
import com.disignstudio.project.mongo.dao.IImagingViewDao;
import com.disignstudio.project.mongo.dao.IUserActionDao;
import com.disignstudio.project.mongo.dao.IViewSupplierDao;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ohadbenporat on 3/31/16.
 */
public class ProjectStatsHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProjectStatsHandler.class);
    private IImagingViewDao imagingViewDao;
    private IUserActionDao userActionDao;
    private IViewSupplierDao viewSupplierDao;
    private ICacheClient cacheClient;
    private ProjectCacheLoader projectCacheLoader;
    private ExecutorService executorService;

    @Inject
    public ProjectStatsHandler(IImagingViewDao imagingViewDao, IViewSupplierDao viewSupplierDao, IUserActionDao userActionDao, ICacheClient cacheClient, ProjectCacheLoader projectCacheLoader) {
        this.imagingViewDao = imagingViewDao;
        this.userActionDao = userActionDao;
        this.cacheClient = cacheClient;
        this.projectCacheLoader = projectCacheLoader;
        this.executorService = Executors.newFixedThreadPool(10);
        this.viewSupplierDao = viewSupplierDao;
    }

    public void recordImagingView(ImagingViewRequest req, String remoteAddr) {

        executorService.submit((Callable<Void>) () -> {
            try {
                ProjectCachedData projectData = loadProjectData(req.getProjectId());
                ApartmentTemplateCachedData aptTmpl = findApartmentTemplateData(projectData.getApartmentTemplateCachedData(), req.getApartmentTemplateId());
                int numOfRooms = aptTmpl != null ? aptTmpl.getNumOfRooms() : 0;
                int source = req.getSource() == null ? EViewImagingSource.MOBILE.getId() : req.getSource();

                imagingViewDao.insert(new ImagingView(System.currentTimeMillis(), req.getUserId(), req.getUuid(), projectData.getEntrepreneurCachedData().getId(),
                        req.getProjectId(), projectData.getCountryId(), projectData.getCityId(), req.getApartmentTemplateId(), numOfRooms,
                        remoteAddr, req.getDesignId(), req.getOs(), req.getDeviceModel(), req.getOsVersion(), source));
            } catch (Exception e) {
                logger.error("Failed to register imaging view", e);
            }

            return null;
        });
    }

    public void recordUserAction(UserActionRequest req, String remoteAddr) {

        executorService.submit((Callable<Void>) () -> {
            try {
                ProjectCachedData projectData = loadProjectData(req.getProjectId());
                ApartmentTemplateCachedData aptTmpl = findApartmentTemplateData(projectData.getApartmentTemplateCachedData(), req.getApartmentTemplateId());
                int numOfRooms = aptTmpl != null ? aptTmpl.getNumOfRooms() : 0;

                userActionDao.insert(new UserAction(System.currentTimeMillis(), req.getUserId(), req.getUuid(), projectData.getEntrepreneurCachedData().getId(),
                        req.getProjectId(), projectData.getCountryId(), projectData.getCityId(), req.getApartmentTemplateId(), numOfRooms, remoteAddr,
                        req.getDesignId(), req.getOs(), req.getDeviceModel(), req.getOsVersion(), req.getAction()));
            } catch (Exception e) {
                logger.error("Failed to register user action", e);
            }
            return null;
        });

    }

    public void recordViewSupplier(ViewSupplierRequest req, String remoteAddr) {

        executorService.submit((Callable<Void>) () -> {
            try {
                ProjectCachedData projectData = loadProjectData(req.getProjectId());
                ApartmentTemplateCachedData aptTmpl = findApartmentTemplateData(projectData.getApartmentTemplateCachedData(), req.getApartmentTemplateId());
                int numOfRooms = aptTmpl != null ? aptTmpl.getNumOfRooms() : 0;

                viewSupplierDao.insert(new ViewSupplier(System.currentTimeMillis(), req.getUserId(), req.getUuid(), projectData.getEntrepreneurCachedData().getId(),
                        req.getProjectId(), projectData.getCountryId(), projectData.getCityId(), req.getApartmentTemplateId(), numOfRooms, remoteAddr,
                        req.getDesignId(), req.getOs(), req.getDeviceModel(), req.getOsVersion(), req.getSupplierId()));
            } catch (Exception e) {
                logger.error("Failed to register user action", e);
            }
            return null;
        });

    }

    private ProjectCachedData loadProjectData(Long projectId) {
        return cacheClient.getOrLoad(projectId, projectCacheLoader, true);
    }

    private ApartmentTemplateCachedData findApartmentTemplateData(List<ApartmentTemplateCachedData> apartmentTemplates, long aptTmplId) {
        for (ApartmentTemplateCachedData data : apartmentTemplates) {
            if (data.getId() == aptTmplId) {
                return data;
            }
        }

        return null;
    }
}
