package com.disignstudio.project.api.response;

/**
 * Created by shrem on 4/18/17.
 */
public class ProjectStats {
    private CollectionStats videoViewStats;
    private CollectionStats visitStats;
    private CollectionStats facebookShareStats;

    public ProjectStats(CollectionStats videoViewStats, CollectionStats visitStats, CollectionStats facebookShareStats) {
        this.videoViewStats = videoViewStats;
        this.visitStats = visitStats;
        this.facebookShareStats = facebookShareStats;
    }

    public CollectionStats getVideoViewStats() {
        return videoViewStats;
    }

    public void setVideoViewStats(CollectionStats videoViewStats) {
        this.videoViewStats = videoViewStats;
    }

    public CollectionStats getVisitStats() {
        return visitStats;
    }

    public void setVisitStats(CollectionStats visitStats) {
        this.visitStats = visitStats;
    }

    public CollectionStats getFacebookShareStats() {
        return facebookShareStats;
    }

    public void setFacebookShareStats(CollectionStats facebookShareStats) {
        this.facebookShareStats = facebookShareStats;
    }
}
