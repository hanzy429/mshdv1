package com.earthquake.managementPlatform.entities;

public class DisasterRequest {
    private int id;
    private String date;
    private String disasterType;
    private String status;
    private String oURL;
    private String requestUnit;

    public DisasterRequest() {
    }

    public DisasterRequest(String date, String disasterType, String status, String oURL, String requestUnit) {
        this.id = id;
        this.date = date;
        this.disasterType = disasterType;
        this.status = status;
        this.oURL = oURL;
        this.requestUnit = requestUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getoURL() {
        return oURL;
    }

    public void setoURL(String oURL) {
        this.oURL = oURL;
    }

    public String getRequestUnit() {
        return requestUnit;
    }

    public void setRequestUnit(String requestUnit) {
        this.requestUnit = requestUnit;
    }
}
