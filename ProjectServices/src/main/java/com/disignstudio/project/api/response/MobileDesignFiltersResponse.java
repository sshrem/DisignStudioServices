package com.disignstudio.project.api.response;

import com.disignstudio.project.loader.data.VideoDetailsData;

import java.util.List;

/**
 * Created by ohadbenporat on 5/17/16.
 */
public class MobileDesignFiltersResponse {

    private String title;
    private List<SupplierSummary> suppliers;
    private List<RoomItemSummary> roomItems;
    private List<VideoDetailsSummary> videosDetails;
    private String defaultFacebookVideoUrl;

    public MobileDesignFiltersResponse() {
    }

    public MobileDesignFiltersResponse(String title, List<SupplierSummary> suppliers, List<RoomItemSummary> roomItems, List<VideoDetailsSummary> videosDetails, String defaultFacebookVideoUrl) {
        this.title = title;
        this.suppliers = suppliers;
        this.roomItems = roomItems;
        this.videosDetails = videosDetails;
        this.defaultFacebookVideoUrl = defaultFacebookVideoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SupplierSummary> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierSummary> suppliers) {
        this.suppliers = suppliers;
    }

    public List<RoomItemSummary> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(List<RoomItemSummary> roomItems) {
        this.roomItems = roomItems;
    }

    public List<VideoDetailsSummary> getVideosDetails() {
        return videosDetails;
    }

    public void setVideosDetails(List<VideoDetailsSummary> videosDetails) {
        this.videosDetails = videosDetails;
    }

    public String getDefaultFacebookVideoUrl() {
        return defaultFacebookVideoUrl;
    }

    public void setDefaultFacebookVideoUrl(String defaultFacebookVideoUrl) {
        this.defaultFacebookVideoUrl = defaultFacebookVideoUrl;
    }
}
