package com.disignstudio.project.loader.data;

import java.io.Serializable;

/**
 * Created by ohadbenporat on 2/14/16.
 */
public class ContactData implements Serializable{

    private String phone;

    public ContactData(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
