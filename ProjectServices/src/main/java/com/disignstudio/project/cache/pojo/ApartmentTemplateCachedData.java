package com.disignstudio.project.cache.pojo;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 5/10/16.
 */
public class ApartmentTemplateCachedData implements Serializable {

    private long id;
    private String name;
    private String image;
    private String code;
    private int numOfRooms;

    public ApartmentTemplateCachedData(long id, String name, String image, String code, int numOfRooms) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.code = code;
        this.numOfRooms = numOfRooms;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
}

