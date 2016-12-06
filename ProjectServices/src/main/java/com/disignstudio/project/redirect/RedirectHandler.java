package com.disignstudio.project.redirect;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.error.InvalidRequestException;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.api.request.ImagingViewRequest;
import com.disignstudio.project.api.response.ImagingRedirectSummary;
import com.disignstudio.project.cache.DesignsCacheLoader;
import com.disignstudio.project.cache.ProjectCacheLoader;
import com.disignstudio.project.cache.pojo.ApartmentTemplateCachedData;
import com.disignstudio.project.cache.pojo.DesignCachedData;
import com.disignstudio.project.cache.pojo.DesignsCachedData;
import com.disignstudio.project.cache.pojo.ProjectCachedData;
import com.disignstudio.project.loader.data.ApartmentTemplateData;
import com.disignstudio.project.loader.data.DesignData;
import com.disignstudio.project.loader.data.ProjectDataWrapper;
import com.disignstudio.project.stats.EViewImagingSource;
import com.disignstudio.project.stats.ProjectStatsHandler;
import com.google.inject.Inject;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Created by ohadbenporat on 4/13/16.
 */
public class RedirectHandler {

    private ICacheClient cacheClient;
    private ProjectCacheLoader projectCacheLoader;
    private DesignsCacheLoader designsCacheLoader;
    private ProjectStatsHandler statsHandler;
    private CloudinaryUtils cloudinaryUtils;

    @Inject
    public RedirectHandler(ICacheClient cacheClient, ProjectCacheLoader projectCacheLoader, DesignsCacheLoader designsCacheLoader, ProjectStatsHandler statsHandler, CloudinaryUtils cloudinaryUtils) {
        this.cacheClient = cacheClient;
        this.projectCacheLoader = projectCacheLoader;
        this.designsCacheLoader = designsCacheLoader;
        this.statsHandler = statsHandler;
        this.cloudinaryUtils = cloudinaryUtils;
    }

    public ImagingRedirectSummary getRedirectedInfo(String src, long projectId, long aptTmplId, long designId, String ipAddress, String userAgentId, String uuid) {

        ProjectCachedData projectData = cacheClient.getOrLoad(projectId, projectCacheLoader, true);
        Pair<ApartmentTemplateCachedData, DesignCachedData> aptTmplAndDesignData = findDesign(projectData.getApartmentTemplateCachedData(), aptTmplId, designId);
        if (aptTmplAndDesignData == null) {
            throw new InvalidRequestException("Design wasn't found");
        }

        int srcId = EViewImagingSource.getStatsSrcBySourceName(src).getId();
        registerStats(projectId, aptTmplAndDesignData.getKey().getId(), designId, srcId, ipAddress, userAgentId, uuid);

        String designVideoUrl = cloudinaryUtils.buildApartmentTemplateImagesPath(projectData.getCode(), aptTmplAndDesignData.getKey().getCode(),
                aptTmplAndDesignData.getValue().getImagingCode());
        String redirectUrl = cloudinaryUtils.generateUrl(designVideoUrl, true);
        String redirectTitle = aptTmplAndDesignData.getValue().getTitle() + ", " + aptTmplAndDesignData.getKey().getName() + ", " + projectData.getName();
        return new ImagingRedirectSummary(redirectUrl, redirectTitle);
    }

    private Pair<ApartmentTemplateCachedData, DesignCachedData> findDesign(List<ApartmentTemplateCachedData> apartments, long aptTmplId, long designId) {

        ApartmentTemplateCachedData requestedApartment = null;
        for (ApartmentTemplateCachedData apt : apartments) {
            if (apt.getId() == aptTmplId) {
                requestedApartment = apt;
            }
        }

        if (requestedApartment == null) {
            throw new InvalidRequestException("Wrong apartmentTemplate Id");
        }

        DesignCachedData requestedDesign = null;
        DesignsCachedData designs = cacheClient.getOrLoad(aptTmplId, designsCacheLoader, true);
        for (DesignCachedData design : designs.getDesigns()) {
            if (design.getId() == designId) {
                requestedDesign = design;
            }
        }

        return Pair.of(requestedApartment, requestedDesign);
    }


    private void registerStats(Long projectId, Long aptTmplId, Long designId, int src, String ipAddress, String userAgentId, String uuid) {

        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentId);
        OperatingSystem os = userAgent.getOperatingSystem();
        ImagingViewRequest req = new ImagingViewRequest(null, uuid, projectId, aptTmplId, designId, null, os.getName(), null, src);
        statsHandler.recordImagingView(req, ipAddress);
    }
}
