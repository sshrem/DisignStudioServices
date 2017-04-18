package com.disignstudio.project.stats;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.project.api.request.*;
import com.disignstudio.project.cache.ProjectCacheLoader;
import com.disignstudio.project.cache.pojo.ApartmentTemplateCachedData;
import com.disignstudio.project.cache.pojo.ProjectCachedData;
import com.disignstudio.project.mongo.bean.*;
import com.disignstudio.project.mongo.dao.*;
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
    private IFacebookShareDao facebookShareDao;
    private IVideoViewDao videoViewDao;
    private IVisitDao visitDao;
    private ICacheClient cacheClient;
    private ProjectCacheLoader projectCacheLoader;
    private ExecutorService executorService;

    @Inject
    public ProjectStatsHandler(IImagingViewDao imagingViewDao, IViewSupplierDao viewSupplierDao, IUserActionDao userActionDao, IFacebookShareDao facebookShareDao, IVideoViewDao videoViewDao, IVisitDao visitDao, ICacheClient cacheClient, ProjectCacheLoader projectCacheLoader) {
        this.imagingViewDao = imagingViewDao;
        this.userActionDao = userActionDao;
        this.facebookShareDao = facebookShareDao;
        this.videoViewDao = videoViewDao;
        this.visitDao = visitDao;
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
                        req.getProjectId(), projectData.getCountryId(), projectData.getCityId(), req.getApartmentTemplateId(), req.getRoomId(), numOfRooms, remoteAddr,
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

    public void recordVideoView(VideoViewRequest req, String remoteAddr, String userAgent) {
        executorService.submit((Callable<Void>) () -> {
            try {
                ProjectCachedData projectData = loadProjectData(req.getProjectId());
                ApartmentTemplateCachedData aptTmpl = findApartmentTemplateData(projectData.getApartmentTemplateCachedData(), req.getApartmentTemplateId());
                int numOfRooms = aptTmpl != null ? aptTmpl.getNumOfRooms() : 0;

                videoViewDao.insert(new VideoView(System.currentTimeMillis(), req.getUserId(), req.getEntrepreneurUserId(), projectData.getEntrepreneurCachedData().getId(),
                        req.getProjectId(), projectData.getCountryId(), projectData.getCityId(), req.getApartmentTemplateId(), req.getRoomId(), numOfRooms, remoteAddr,
                        req.getDesignId(), userAgent));
            } catch (Exception e) {
                logger.error("Failed to register user action", e);
            }
            return null;
        });


    }

    public void recordFacebookShare(FacebookShareRequest req, String remoteAddr, String userAgent) {
        executorService.submit((Callable<Void>) () -> {
            try {
                ProjectCachedData projectData = loadProjectData(req.getProjectId());
                ApartmentTemplateCachedData aptTmpl = findApartmentTemplateData(projectData.getApartmentTemplateCachedData(), req.getApartmentTemplateId());
                int numOfRooms = aptTmpl != null ? aptTmpl.getNumOfRooms() : 0;

                facebookShareDao.insert(new FacebookShare(System.currentTimeMillis(), req.getUserId(), req.getEntrepreneurUserId(), projectData.getEntrepreneurCachedData().getId(),
                        req.getProjectId(), projectData.getCountryId(), projectData.getCityId(), req.getApartmentTemplateId(), req.getRoomId(), numOfRooms, remoteAddr,
                        req.getDesignId(), userAgent));
            } catch (Exception e) {
                logger.error("Failed to register user action", e);
            }
            return null;
        });


    }

    public void recordVisit(VisitRequest req, String remoteAddr, String userAgent) {
        executorService.submit((Callable<Void>) () -> {
            try {
                ProjectCachedData projectData = loadProjectData(req.getProjectId());

                visitDao.insert(new Visit(System.currentTimeMillis(), req.getPage(), req.getUserId(), req.getEntrepreneurUserId(), projectData.getEntrepreneurCachedData().getId(),
                        req.getProjectId(), projectData.getCountryId(), projectData.getCityId(), remoteAddr,
                         userAgent));
            } catch (Exception e) {
                logger.error("Failed to register user action", e);
            }
            return null;
        });


    }
}
