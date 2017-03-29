package com.disignstudio.project.loader.data;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 2/5/16.
 */
public class ApartmentTemplateData implements Serializable {

    private long id;
    private String code;
    private String name;
    private String image;
    private int numOfRooms;
    private String defaultFacebookVideoUrl;

    public ApartmentTemplateData(long id, String code, String name, String image, int numOfRooms, String defaultFacebookVideoUrl) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.image = image;
        this.numOfRooms = numOfRooms;
        this.defaultFacebookVideoUrl = defaultFacebookVideoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public String getDefaultFacebookVideoUrl() {
        return defaultFacebookVideoUrl;
    }

    public void setDefaultFacebookVideoUrl(String defaultFacebookVideoUrl) {
        this.defaultFacebookVideoUrl = defaultFacebookVideoUrl;
    }
}
