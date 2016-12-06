package com.disignstudio.project.api.response;

import java.util.List;

/**
 * Created by ohadbenporat on 3/22/16.
 */
public class MobileDesignItemsResponse {

    private List<DesignItemSummary> items;

    public MobileDesignItemsResponse(List<DesignItemSummary> items) {
        this.items = items;
    }

    public List<DesignItemSummary> getItems() {
        return items;
    }

    public void setItems(List<DesignItemSummary> items) {
        this.items = items;
    }
}
