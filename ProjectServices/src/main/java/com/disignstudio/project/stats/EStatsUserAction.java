package com.disignstudio.project.stats;

/**
 * Created by ohadbenporat on 4/17/16.
 */
public enum EStatsUserAction {

    VIEW_IMAGING_DETAILS(1),
    SHARE(2);

    private final int id;

    private EStatsUserAction(int id) {
        this.id = id;
    }
}