package com.disignstudio.project.api.handler;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.api.request.ProjectRequest;
import com.disignstudio.project.api.response.ApartmentSummary;
import com.disignstudio.project.api.response.EntrepreneurSummary;
import com.disignstudio.project.api.response.MobileProjectResponse;
import com.disignstudio.project.cache.ProjectCacheLoader;
import com.disignstudio.project.cache.pojo.ApartmentTemplateCachedData;
import com.disignstudio.project.cache.pojo.ProjectCachedData;
import com.disignstudio.project.loader.data.ApartmentTemplateData;
import com.disignstudio.project.loader.data.EntrepreneurData;
import com.disignstudio.project.loader.data.ProjectData;
import com.disignstudio.project.loader.data.ProjectDataWrapper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import java.util.List;

/**
 * Created by ohadbenporat on 3/16/16.
 */
public class MobileProjectRequestHandler {

    private ICacheClient cacheClient;
    private ProjectCacheLoader projectCacheLoader;

    @Inject
    public MobileProjectRequestHandler(ICacheClient cacheClient, ProjectCacheLoader projectCacheLoader) {
        this.cacheClient = cacheClient;
        this.projectCacheLoader = projectCacheLoader;
    }

    public MobileProjectResponse build(ProjectRequest request) {

        ProjectCachedData projectData = cacheClient.getOrLoad(request.getProjId(), projectCacheLoader, request.isUseCache());

        EntrepreneurSummary entrepreneur = new EntrepreneurSummary(projectData.getEntrepreneurCachedData().getName(), projectData.getEntrepreneurCachedData().getLogo(),
                projectData.getEntrepreneurCachedData().getAbout());
        List<ApartmentSummary> apartmentsSummary = buildApartmentSummary(projectData.getApartmentTemplateCachedData());

        return new MobileProjectResponse(projectData.getName(), projectData.getAbout(), projectData.getLocation(), projectData.getLon(),
                projectData.getLat(), projectData.getLogo(), projectData.getImageUrl(), projectData.getSalesPhone(),
                entrepreneur, projectData.getFeatures(), apartmentsSummary);
    }

    private List<ApartmentSummary> buildApartmentSummary(List<ApartmentTemplateCachedData> apartmentTemplates) {

        List<ApartmentSummary> summary = Lists.newArrayList();

        apartmentTemplates.forEach(aptTmpl -> {
            summary.add(new ApartmentSummary(aptTmpl.getId(), aptTmpl.getName(), aptTmpl.getImage()));
        });

        return summary;
    }
}
