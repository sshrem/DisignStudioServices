package com.disignstudio.project.api.response;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 2/2/16.
 */
public class ApartmentSummary implements Serializable{

    private long id;
    private String name;
    private String imgCode;

    public ApartmentSummary(long id, String name, String imgCode) {
        this.id = id;
        this.name = name;
        this.imgCode = imgCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }
}
