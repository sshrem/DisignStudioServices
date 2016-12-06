package com.disignstudio.project.api.response;

import java.util.List;

/**
 * Created by ohadbenporat on 5/22/16.
 */
public class RoomItemSummary {

    private String title;
    private int roomId;
    private List<DesignItemSummary> items;

    public RoomItemSummary(String title, int roomId, List<DesignItemSummary> items) {
        this.title = title;
        this.roomId = roomId;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<DesignItemSummary> getItems() {
        return items;
    }

    public void setItems(List<DesignItemSummary> items) {
        this.items = items;
    }
}
