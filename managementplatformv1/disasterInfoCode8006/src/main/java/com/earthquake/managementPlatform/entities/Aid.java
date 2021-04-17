package com.earthquake.managementPlatform.entities;

public class Aid {
    private String code;
    private String name;

    public Aid(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Aid() {
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
}
