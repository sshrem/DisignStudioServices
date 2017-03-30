package com.disignstudio.project.api.response;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class DesignSummary implements Serializable {

    private long id;
    private String title;
    private String imaging;
    private String designerName;
    private String designerImage;
    private String redirectUrl;
    private String facebookVideoUrl;

    public DesignSummary(long id, String title, String designerName, String designerImage, String imaging, String redirectUrl, String facebookVideoUrl) {
        this.id = id;
        this.title = title;
        this.designerName = designerName;
        this.designerImage = designerImage;
        this.imaging = imaging;
        this.redirectUrl = redirectUrl;
        this.facebookVideoUrl = facebookVideoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getImaging() {
        return imaging;
    }

    public void setImaging(String imaging) {
        this.imaging = imaging;
    }

    public String getDesignerImage() {
        return designerImage;
    }

    public void setDesignerImage(String designerImage) {
        this.designerImage = designerImage;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getFacebookVideoUrl() {
        return facebookVideoUrl;
    }

    public void setFacebookVideoUrl(String facebookVideoUrl) {
        this.facebookVideoUrl = facebookVideoUrl;
    }
}
