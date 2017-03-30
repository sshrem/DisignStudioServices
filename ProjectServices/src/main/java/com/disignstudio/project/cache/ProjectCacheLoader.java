package com.disignstudio.project.cache;

import com.disignstudio.common.cache.EntityCacheKey;
import com.disignstudio.common.cache.ICacheLoader;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.cache.pojo.ApartmentTemplateCachedData;
import com.disignstudio.project.cache.pojo.EntrepreneurCachedData;
import com.disignstudio.project.cache.pojo.ProjectCachedData;
import com.disignstudio.project.loader.ProjectDataLoader;
import com.disignstudio.project.loader.data.ApartmentTemplateData;
import com.disignstudio.project.loader.data.ProjectDataWrapper;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.List;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class ProjectCacheLoader implements ICacheLoader {

    private final static String CACHE_PREFIX_KEY = "PROJECT_CACHE_KEY";
    private final int cacheExpiration;
    private ProjectDataLoader projectDataLoader;
    private CloudinaryUtils cloudinaryUtils;

    @Inject
    public ProjectCacheLoader(ProjectDataLoader projectDataLoader, CloudinaryUtils cloudinaryUtils, @Named("projectCacheExpiration") int cacheExpiration) {
        this.cacheExpiration = cacheExpiration;
        this.projectDataLoader = projectDataLoader;
        this.cloudinaryUtils = cloudinaryUtils;
    }

    @Override
    public ProjectCachedData load(Object key) {

        ProjectDataWrapper projectData = projectDataLoader.loadProjectData((Long) key);

        String projectImage = cloudinaryUtils.buildProjectImagesPath(projectData.getProject().getCode(), "background");
        String projectLogo = cloudinaryUtils.buildProjectImagesPath(projectData.getProject().getCode(), projectData.getProject().getLogo());

        String entrepreneurLogo = cloudinaryUtils.buildEntreprenuerImagesPath(projectData.getEntrepreneur().getLogo());
        EntrepreneurCachedData entrepreneurCachedData = new EntrepreneurCachedData(projectData.getEntrepreneur().getId(), projectData.getEntrepreneur().getName(), entrepreneurLogo, projectData.getEntrepreneur().getAbout());
        List<ApartmentTemplateCachedData> apartmentTemplatesCachedData = buildApartmentTemplateCachedData(projectData.getProject().getCode(), projectData.getApartmentTemplates());

        return new ProjectCachedData(projectData.getProject().getId(), projectData.getProject().getCode(), projectData.getProject().getName(), projectData.getProject().getAbout()
                , projectData.getProject().getAddress(), projectData.getProject().getLon(), projectData.getProject().getLat(), projectLogo, projectImage,
                projectData.getProject().getSalesContact().getPhone(), projectData.getProject().getCountryId(), projectData.getProject().getCityId(),
            entrepreneurCachedData, projectData.getProjectFeatures(), apartmentTemplatesCachedData, projectData.getVideoDetails());
    }

    private List<ApartmentTemplateCachedData> buildApartmentTemplateCachedData(String projectCode, List<ApartmentTemplateData> apartmentTemplates) {

        List<ApartmentTemplateCachedData> cachedData = Lists.newArrayList();
        apartmentTemplates.forEach(aptTmpl -> {
            String image = cloudinaryUtils.buildApartmentTemplateImagesPath(projectCode, aptTmpl.getCode(), aptTmpl.getImage());
            cachedData.add(new ApartmentTemplateCachedData(aptTmpl.getId(), aptTmpl.getName(), image, aptTmpl.getCode(), aptTmpl.getNumOfRooms(), aptTmpl.getDefaultFacebookVideoUrl()));
        });

        return cachedData;
    }

    @Override
    public int getCacheExpiration() {
        return cacheExpiration;
    }

    @Override
    public TypeToken<ProjectCachedData> getCacheValueType() {
        return TypeToken.of(ProjectCachedData.class);
    }

    @Override
    public EntityCacheKey<Long> generateKey(Object keyEntity) {
        return new EntityCacheKey<>((Long) keyEntity, CACHE_PREFIX_KEY);
    }
}
