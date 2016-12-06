package com.disignstudio.project.api.response;

import java.util.List;

/**
 * Created by ohadbenporat on 5/17/16.
 */
public class MobileDesignFiltersResponse {

    private String title;
    private List<SupplierSummary> suppliers;
    private List<RoomItemSummary> roomItems;

    public MobileDesignFiltersResponse(String title, List<SupplierSummary> suppliers, List<RoomItemSummary> roomItems) {
        this.title = title;
        this.suppliers = suppliers;
        this.roomItems = roomItems;
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
}
