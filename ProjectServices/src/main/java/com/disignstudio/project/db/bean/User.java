package com.disignstudio.project.db.bean;

import org.joda.time.DateTime;

/**
 * Created by ohadbenporat on 2/15/16.
 */
public class User {

    private Long userId;
    private String email;
    private String name;
    private int loginMethod;
    private DateTime lastLogin;

    public User() {
    }

    public User(Long userId,String email, String name, int loginMethod, DateTime lastLogin) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.loginMethod = loginMethod;
        this.lastLogin = lastLogin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(int loginMethod) {
        this.loginMethod = loginMethod;
    }

    public DateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(DateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
