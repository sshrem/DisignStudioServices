package com.disignstudio.project.api.response;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 2/11/16.
 */
public class EntrepreneurSummary implements Serializable {

    private String name;
    private String logo;
    private String about;

    public EntrepreneurSummary(String name, String logo, String about) {
        this.name = name;
        this.logo = logo;
        this.about = about;
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
