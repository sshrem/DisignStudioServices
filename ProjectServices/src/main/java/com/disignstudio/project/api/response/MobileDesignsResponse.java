package com.disignstudio.project.api.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ohadbenporat on 2/15/16.
 */
public class MobileDesignsResponse implements Serializable {

    private int totalNumOfDesigns;
    private String title;
    private List<DesignSummary> designs;

    public MobileDesignsResponse(int totalNumOfDesigns, String title, List<DesignSummary> designs) {
        this.totalNumOfDesigns = totalNumOfDesigns;
        this.title = title;
        this.designs = designs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalNumOfDesigns() {
        return totalNumOfDesigns;
    }

    public void setTotalNumOfDesigns(int totalNumOfDesigns) {
        this.totalNumOfDesigns = totalNumOfDesigns;
    }

    public List<DesignSummary> getDesigns() {
        return designs;
    }

    public void setDesigns(List<DesignSummary> designs) {
        this.designs = designs;
    }
}
