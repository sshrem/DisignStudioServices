package com.disignstudio.project.loader.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ohadbenporat on 2/5/16.
 */
public class ApartmentTemplateData implements Serializable {

    private long id;
    private String code;
    private String name;
    private String image;
    private int numOfRooms;

    public ApartmentTemplateData(long id, String code, String name, String image, int numOfRooms) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.image = image;
        this.numOfRooms = numOfRooms;
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
}
