package com.disignstudio.project.db.bean;

/**
 * Created by shrem on 20/03/2017.
 */
public class VideoDetails {
    private long id;
    private long projectId;
    private long apartmentTemplateId;
    private long roomId;
    private long videoLength;

    public VideoDetails() {
    }

    public VideoDetails(long id, long projectId, long apartmentTemplateId, long roomId, long videoLength) {
        this.id = id;
        this.projectId = projectId;
        this.apartmentTemplateId = apartmentTemplateId;
        this.roomId = roomId;
        this.videoLength = videoLength;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getApartmentTemplateId() {
        return apartmentTemplateId;
    }

    public void setApartmentTemplateId(long apartmentTemplateId) {
        this.apartmentTemplateId = apartmentTemplateId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(long videoLength) {
        this.videoLength = videoLength;
    }

    @Override
    public String toString() {
        return "VideoDetails{" +
            "id=" + id +
            ", projectId=" + projectId +
            ", apartmentTemplateId=" + apartmentTemplateId +
            ", roomId=" + roomId +
            ", videoLength=" + videoLength +
            '}';
    }
}
