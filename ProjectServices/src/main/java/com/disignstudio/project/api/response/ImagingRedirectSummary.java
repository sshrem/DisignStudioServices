package com.disignstudio.project.api.response;

/**
 * Created by ohadbenporat on 5/2/16.
 */
public class ImagingRedirectSummary {

    private String url;
    private String title;

    public ImagingRedirectSummary(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

