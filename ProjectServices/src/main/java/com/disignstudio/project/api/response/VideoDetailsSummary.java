package com.disignstudio.project.api.response;

/**
 * Created by shrem on 4/4/17.
 */
public class VideoDetailsSummary {
    private long roomId;
    private long videoLength;
    private long startTime;
    private long endTime;
    private int videoOrdinal;

    public VideoDetailsSummary(long roomId, long videoLength, long startTime, long endTime, int videoOrdinal) {
        this.roomId = roomId;
        this.videoLength = videoLength;
        this.startTime = startTime;
        this.endTime = endTime;
        this.videoOrdinal = videoOrdinal;
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getVideoOrdinal() {
        return videoOrdinal;
    }

    public void setVideoOrdinal(int videoOrdinal) {
        this.videoOrdinal = videoOrdinal;
    }


    @Override
    public String toString() {
        return "VideoDetailsSummary{" +
                "roomId=" + roomId +
                ", videoLength=" + videoLength +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", videoOrdinal=" + videoOrdinal +
                '}';
    }
}
