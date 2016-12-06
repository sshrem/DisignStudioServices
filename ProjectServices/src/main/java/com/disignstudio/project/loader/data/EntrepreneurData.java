package com.disignstudio.project.loader.data;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 2/5/16.
 */
public class EntrepreneurData implements Serializable {

    private long id;
    private String name;
    private String logo;
    private String about;

    public EntrepreneurData(long id, String name, String logo, String about) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.about = about;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
