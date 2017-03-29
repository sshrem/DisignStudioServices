package com.disignstudio.project.loader.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ohadbenporat on 2/6/16.
 */
public class DesignData implements Serializable {

    private long id;
    private String designerName;
    private String designerImage;
    private String title;
    private String imagingCode;
    private List<DesignItemData> designItems;
    private String facebookVideoUrl;

    public DesignData(long id, String designerName, String designerImage, String title, String imagingCode, String facebookVideoUrl, List<DesignItemData> designItems) {
        this.id = id;
        this.designerName = designerName;
        this.designerImage = designerImage;
        this.title = title;
        this.imagingCode = imagingCode;
        this.designItems = designItems;
        this.facebookVideoUrl = facebookVideoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getDesignerImage() {
        return designerImage;
    }

    public void setDesignerImage(String designerImage) {
        this.designerImage = designerImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagingCode() {
        return imagingCode;
    }

    public void setImagingCode(String imagingCode) {
        this.imagingCode = imagingCode;
    }

    public List<DesignItemData> getDesignItems() {
        return designItems;
    }

    public void setDesignItems(List<DesignItemData> designItems) {
        this.designItems = designItems;
    }

    public String getFacebookVideoUrl() {
        return facebookVideoUrl;
    }

    public void setFacebookVideoUrl(String facebookVideoUrl) {
        this.facebookVideoUrl = facebookVideoUrl;
    }

    @Override
    public String toString() {
        return "DesignData{" +
                "id=" + id +
                ", designerName='" + designerName + '\'' +
                ", designerImage='" + designerImage + '\'' +
                ", title='" + title + '\'' +
                ", imagingCode='" + imagingCode + '\'' +
                ", designItems=" + designItems +
                ", facebookVideoUrl='" + facebookVideoUrl + '\'' +
                '}';
    }
}
