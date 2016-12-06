package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 1/13/16.
 */
public class ApartmentTemplate {

    private long id;
    private String code;
    private long projectId;
    private int numOfRooms;
    private String name;
    private String image;

    public ApartmentTemplate() {
    }

    public ApartmentTemplate(String code, long projectId, String name, String image, int numOfRooms) {
        this(0L, code, projectId, name, image, numOfRooms);
    }

    public ApartmentTemplate(long id, String code, long projectId, String name, String image, int numOfRooms) {
        this.id = id;
        this.code = code;
        this.projectId = projectId;
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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
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
