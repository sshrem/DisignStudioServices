package com.disignstudio.project.db.bean;

/**
 * Created by ohadbenporat on 2/8/16.
 */
public class RFFeature {

    private long id;
    private String code;
    private String nameEN;
    private String nameHE;

    public RFFeature(long id, String code, String nameEN, String nameHE) {
        this.id = id;
        this.code = code;
        this.nameEN = nameEN;
        this.nameHE = nameHE;
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

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameHE() {
        return nameHE;
    }

    public void setNameHE(String nameHE) {
        this.nameHE = nameHE;
    }
}
