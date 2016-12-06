package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class DesignItem {

    private long id;
    private long designId;
    private long offeringId;
    private int roomId;

    public DesignItem() {
    }

    public DesignItem(long designId, long offeringId, int roomId) {
        this(0L, designId, offeringId, roomId);
    }

    public DesignItem(long id, long designId, long offeringId, int roomId) {
        this.id = id;
        this.designId = designId;
        this.offeringId = offeringId;
        this.roomId = roomId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDesignId() {
        return designId;
    }

    public void setDesignId(long designId) {
        this.designId = designId;
    }

    public long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(long offeringId) {
        this.offeringId = offeringId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
