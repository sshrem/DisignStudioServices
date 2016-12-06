package com.disignstudio.project.cache.pojo;

import java.util.List;

/**
 * Created by ohadbenporat on 5/11/16.
 */
public class DesignCachedData {

    private long id;
    private String title;
    private String designerName;
    private String designerLogo;
    private String imagingCode;
    private List<DesignItemCachedData> items;

    public DesignCachedData(long id, String title, String designerName, String designerLogo, String imagingCode, List<DesignItemCachedData> items) {
        this.id = id;
        this.title = title;
        this.designerName = designerName;
        this.designerLogo = designerLogo;
        this.imagingCode = imagingCode;
        this.items = items;
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

    public String getDesignerLogo() {
        return designerLogo;
    }

    public void setDesignerLogo(String designerLogo) {
        this.designerLogo = designerLogo;
    }

    public String getImagingCode() {
        return imagingCode;
    }

    public void setImagingCode(String imagingCode) {
        this.imagingCode = imagingCode;
    }

    public List<DesignItemCachedData> getItems() {
        return items;
    }

    public void setItems(List<DesignItemCachedData> items) {
        this.items = items;
    }
}
