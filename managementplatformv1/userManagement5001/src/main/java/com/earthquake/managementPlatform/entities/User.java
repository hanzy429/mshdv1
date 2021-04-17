package com.earthquake.managementPlatform.entities;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String registerDate;
    private String loginDate;
    private String lastLoginDate;
    private int loginCount;
    private String userType;

    public User(String username, String password, String registerDate, String loginDate, String lastLoginDate, int loginCount, String userType) {
        this.username = username;
        this.password = password;
        this.registerDate = registerDate;
        this.loginDate = loginDate;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
        this.userType = userType;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
