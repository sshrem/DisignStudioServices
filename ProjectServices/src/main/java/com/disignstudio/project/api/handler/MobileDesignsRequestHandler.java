package com.disignstudio.project.api.handler;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.common.utils.CloudinaryUtils;
import com.disignstudio.project.api.request.DesignItemFilter;
import com.disignstudio.project.api.request.DesignSupplierFilter;
import com.disignstudio.project.api.request.DesignsRequest;
import com.disignstudio.project.api.response.DesignSummary;
import com.disignstudio.project.api.response.MobileDesignsResponse;
import com.disignstudio.project.cache.DesignsCacheLoader;
import com.disignstudio.project.cache.ProjectCacheLoader;
import com.disignstudio.project.cache.pojo.*;
import com.disignstudio.project.db.bean.helper.ERoom;
import com.disignstudio.project.redirect.RedirectUrlBuilder;
import com.disignstudio.project.stats.EViewImagingSource;
import com.google.common.base.Function;
import com.google.common.collect.*;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by ohadbenporat on 3/16/16.
 */
public class MobileDesignsRequestHandler {

    private final int numOfDesignsToServe;
    private ICacheClient cacheClient;
    private DesignsCacheLoader designsCacheLoader;
    private ProjectCacheLoader projectCacheLoader;
    private CloudinaryUtils cloudinaryUtils;
    private RedirectUrlBuilder redirectUrlBuilder;

    @Inject
    public MobileDesignsRequestHandler(ICacheClient cacheClient, DesignsCacheLoader designsCacheLoader, ProjectCacheLoader projectCacheLoader,
                                       CloudinaryUtils cloudinaryUtils, RedirectUrlBuilder redirectUrlBuilder, @Named("numberOfDesignsToServe") int numOfDesignsToServe) {
        this.cacheClient = cacheClient;
        this.designsCacheLoader = designsCacheLoader;
        this.projectCacheLoader = projectCacheLoader;
        this.cloudinaryUtils = cloudinaryUtils;
        this.redirectUrlBuilder = redirectUrlBuilder;
        this.numOfDesignsToServe = numOfDesignsToServe;
    }

    public MobileDesignsResponse build(DesignsRequest request) {

        ProjectCachedData projectData = cacheClient.getOrLoad(request.getProjId(), projectCacheLoader, request.isUseCache());
        ApartmentTemplateCachedData apartmentTemplateCachedData = loadApartmentTemplate(projectData.getApartmentTemplateCachedData(), request.getAtId());

        DesignsCachedData allDesignsData = cacheClient.getOrLoad(request.getAtId(), designsCacheLoader, request.isUseCache());
        List<DesignCachedData> designsToServe = selectDesigns(allDesignsData.getDesigns(), request.getItemFilters(), request.getSupplierFilter());
        List<DesignSummary> designSummaries = Lists.newArrayList();
        designsToServe.forEach(design -> {

            String designImaging = cloudinaryUtils.buildApartmentTemplateImagesPath(projectData.getCode(), apartmentTemplateCachedData.getCode(), design.getImagingCode());
            String redirectUrl = redirectUrlBuilder.build(request.getProjId(), request.getAtId(), design.getId(), EViewImagingSource.SOCIAL);
            DesignSummary ds = new DesignSummary(design.getId(), design.getTitle(), design.getDesignerName(), design.getDesignerLogo(),
                    designImaging, redirectUrl);
            designSummaries.add(ds);
        });

        return new MobileDesignsResponse(allDesignsData.getDesigns().size(), apartmentTemplateCachedData.getName(), designSummaries);
    }

    private List<DesignCachedData> selectDesigns(List<DesignCachedData> designs, List<DesignItemFilter> itemFilters, DesignSupplierFilter supplierFilter) {

        designs = filterDesigns(designs, itemFilters, supplierFilter);
        Collections.shuffle(designs);
        return designs.size() <= numOfDesignsToServe ? designs : designs.subList(0, numOfDesignsToServe);
    }

    private List<DesignCachedData> filterDesigns(List<DesignCachedData> allDesignsData, List<DesignItemFilter> itemsFilters, DesignSupplierFilter supplierFilter) {

        if (CollectionUtils.isEmpty(itemsFilters) && supplierFilter == null) {
            return allDesignsData;
        } else if (CollectionUtils.isEmpty(itemsFilters)) { // Filter just by supplier Filter
            return filterDesignsBySupplier(allDesignsData, supplierFilter);
        } else { // Filter by design items
            return filterDesignsByItems(allDesignsData, itemsFilters);
        }
    }

    private List<DesignCachedData> filterDesignsBySupplier(List<DesignCachedData> allDesignsData, DesignSupplierFilter supplierFilter) {
        List<DesignCachedData> validDesigns = Lists.newArrayList();
        for (DesignCachedData design : allDesignsData) {
            for (DesignItemCachedData item : design.getItems()) {
                if (item.getSupplierId() == supplierFilter.getSupplier()) {
                    validDesigns.add(design);
                    break;
                }
            }
        }

        return validDesigns;
    }

    private List<DesignCachedData> filterDesignsByItems(List<DesignCachedData> allDesignsData, List<DesignItemFilter> itemsFilters) {
        List<DesignCachedData> validDesigns = Lists.newArrayList();
        for (DesignCachedData design : allDesignsData) {

            Set<DesignItemFilter> passedFilters = Sets.newHashSet();
            for (DesignItemFilter filter : itemsFilters) {
                for (DesignItemCachedData item : design.getItems()) {
                    if ((item.getRoomId() == filter.getRoom()) && (item.getOfferingId() == filter.getOffer())) {
                        passedFilters.add(filter);
                        break;
                    }
                }
            }

            if (isValidDesign(itemsFilters, passedFilters)) {
                validDesigns.add(design);
            }
        }

        return validDesigns;
    }

    private boolean isValidDesign(List<DesignItemFilter> filters, Set<DesignItemFilter> passedFilters) {

        ImmutableMap<ERoom, Collection<DesignItemFilter>> mapRoomToFilter = Multimaps.index(filters, new Function<DesignItemFilter, ERoom>() {
            @Nullable
            @Override
            public ERoom apply(@Nullable DesignItemFilter input) {
                return ERoom.getRoomById(input.getRoom());
            }
        }).asMap();

        for (Map.Entry<ERoom, Collection<DesignItemFilter>> entry : mapRoomToFilter.entrySet()) {
            boolean isRoomValid = false;
            for (DesignItemFilter df : entry.getValue()) {
                if (passedFilters.contains(df)) {
                    isRoomValid = true;
                    break;
                }
            }

            if (isRoomValid == false) {
                return false;
            }
        }

        return true;
    }

    private ApartmentTemplateCachedData loadApartmentTemplate(List<ApartmentTemplateCachedData> apartmentTemplates, long apartmentTemplateId) {

        for (ApartmentTemplateCachedData apt : apartmentTemplates) {
            if (apt.getId() == apartmentTemplateId) {
                return apt;
            }
        }

        return null;
    }
}