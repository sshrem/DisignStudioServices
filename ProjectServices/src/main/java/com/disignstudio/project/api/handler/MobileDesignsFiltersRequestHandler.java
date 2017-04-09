package com.disignstudio.project.api.handler;

import com.disignstudio.common.cache.ICacheClient;
import com.disignstudio.project.api.request.DesignsFiltersRequest;
import com.disignstudio.project.api.response.*;
import com.disignstudio.project.cache.DesignsCacheLoader;
import com.disignstudio.project.cache.ProjectCacheLoader;
import com.disignstudio.project.cache.pojo.*;
import com.disignstudio.project.db.bean.VideoDetails;
import com.disignstudio.project.db.bean.helper.EDesignFilter;
import com.disignstudio.project.db.bean.helper.ERoom;
import com.disignstudio.project.loader.data.VideoDetailsData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ohadbenporat on 5/17/16.
 */
public class MobileDesignsFiltersRequestHandler {

    private static final int FLOORING_CATEGORY = 1000;
    private static final int CADDLING_CATEGORY = 2000;

    private ICacheClient cacheClient;
    private DesignsCacheLoader designsCacheLoader;
    private ProjectCacheLoader projectCacheLoader;

    @Inject
    public MobileDesignsFiltersRequestHandler(ICacheClient cacheClient, DesignsCacheLoader designsCacheLoader, ProjectCacheLoader projectCacheLoader) {
        this.cacheClient = cacheClient;
        this.designsCacheLoader = designsCacheLoader;
        this.projectCacheLoader = projectCacheLoader;
    }

    public MobileDesignFiltersResponse execute(DesignsFiltersRequest request) {
        ProjectCachedData projectData = cacheClient.getOrLoad(request.getProjId(), projectCacheLoader, request.isUseCache());
        ApartmentTemplateCachedData apartmentTemplateCachedData = loadApartmentTemplate(projectData.getApartmentTemplateCachedData(), request.getAtId());
        List<VideoDetailsSummary> videosDetails = extractVideoDetails(projectData.getVideoDetails(), request.getAtId()) ;

        DesignsCachedData allDesignsData = cacheClient.getOrLoad(request.getAtId(), designsCacheLoader, request.isUseCache());
        Pair<List<SupplierSummary>, List<RoomItemSummary>> designsData = extractDataFromDesigns(allDesignsData.getDesigns());

        return new MobileDesignFiltersResponse(apartmentTemplateCachedData.getName(), designsData.getLeft(),
            designsData.getRight(), videosDetails, apartmentTemplateCachedData.getDefaultFacebookVideoUrl());
    }

    private List<VideoDetailsSummary> extractVideoDetails(List<VideoDetailsData> videosDetails, long apartmentTemplateId){
        Map<Long, VideoDetailsData> filteredVideoByRoomId = videosDetails.stream()
                .filter(v -> v.getApartmentTemplateId() == apartmentTemplateId).collect(Collectors.toMap(VideoDetailsData::getRoomId, x->x));
        List<VideoDetailsSummary> videoDetailsSummary = Lists.newArrayList();

        List<ERoom> videoOrder = Lists.newArrayList(ERoom.LIVINGROOM, ERoom.BEDROOM, ERoom.BATHROOM);

        long startTime = 0;
        long endTime = -1;
        int videoOrdinal = 0;
        for (ERoom eRoom : videoOrder) {
            int roomId = eRoom.getId();
            VideoDetailsData v = filteredVideoByRoomId.get((long) roomId);
            endTime = Math.max(startTime + v.getVideoLength(), 0);
            VideoDetailsSummary summary = new VideoDetailsSummary(v.getRoomId(), v.getVideoLength(), startTime, endTime, videoOrdinal);
            videoDetailsSummary.add(summary);
            startTime = endTime;
            videoOrdinal++;
        }
        return videoDetailsSummary;
    }

    private Pair<List<SupplierSummary>, List<RoomItemSummary>> extractDataFromDesigns(List<DesignCachedData> designs) {

        Map<Long, SupplierSummary> mapSupplierIdToSummary = Maps.newHashMap();
        Map<EDesignFilter, Map<Long, DesignItemSummary>> mapFilterToItems = Maps.newLinkedHashMap();
        for (EDesignFilter filter : EDesignFilter.values()) {
            mapFilterToItems.put(filter, Maps.newHashMap());
        }

        designs.forEach(design -> {
            for (DesignItemCachedData item : design.getItems()) {

                // Update Items per room
                EDesignFilter filter = EDesignFilter.findByRoomAndCategory(item.getRoomId(), item.getCategory());
                if (filter == null) {
                    continue;
                }

                Map<Long, DesignItemSummary> currentItemsPerRoom = mapFilterToItems.get(filter);

                if (!currentItemsPerRoom.containsKey(item.getOfferingId())) {
                    currentItemsPerRoom.put(item.getOfferingId(), new DesignItemSummary(item.getOfferingId(), item.getName(), item.getImgCode(), item.getSupplierId()));
                }

                mapFilterToItems.put(filter, currentItemsPerRoom);

                // Update suppliers
                if (mapSupplierIdToSummary.containsKey(item.getSupplierId())) {
                    continue;
                }

                if (item.getCategory() != CADDLING_CATEGORY && item.getCategory() != FLOORING_CATEGORY) {
                    continue;
                }

                mapSupplierIdToSummary.put(item.getSupplierId(), new SupplierSummary(item.getSupplierId(), item.getSupplierName() , item.getSupplierLogo(), item.getSupplierUrl()));
            }
            ;
        });

        List<RoomItemSummary> roomItems = buildRoomItemsSummary(mapFilterToItems);
        List<SupplierSummary> suppliers = new ArrayList<>(mapSupplierIdToSummary.values());

        return Pair.of(suppliers, roomItems);
    }

    private List<RoomItemSummary> buildRoomItemsSummary(Map<EDesignFilter, Map<Long, DesignItemSummary>> mapRoomToItems) {

        List<RoomItemSummary> roomItemsSummaries = Lists.newArrayList();
        mapRoomToItems.forEach((filter, items) -> {

            if (items.size() > 0) {
                List<DesignItemSummary> designItems = new ArrayList(items.values());
                roomItemsSummaries.add(new RoomItemSummary(filter.getTitle(), filter.getRoom().getId(), designItems));
            }

        });

        return roomItemsSummaries;
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
