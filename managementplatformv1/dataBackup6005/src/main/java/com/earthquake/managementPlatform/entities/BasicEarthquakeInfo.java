package com.earthquake.managementPlatform.entities;

import java.io.Serializable;

public class BasicEarthquakeInfo implements Serializable {
    private String id;
    private String date;
    private String location;
    private float longitude;
    private float latitude;
    private float depth;
    private float magnitude;
    private String picture;
    private String reportingUnit;

    public BasicEarthquakeInfo(String id, String date, String location, float longitude, float latitude, float depth, float magnitude, String picture, String reportingUnit) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.depth = depth;
        this.magnitude = magnitude;
        this.picture = picture;
        this.reportingUnit = reportingUnit;
    }

    public BasicEarthquakeInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getReportingUnit() {
        return reportingUnit;
    }

    public void setReportingUnit(String reportingUnit) {
        this.reportingUnit = reportingUnit;
    }

    @Override
    public String toString() {
        return "???????????????????????????????????????" + '\n' +
                "?????????" + location + '???'+'\n' +
                "?????????" + date + '???'+'\n' +
                "?????????" + longitude + '???'+'\n' +
                "?????????" + latitude + '???'+'\n' +
                "?????????" + depth + '???'+'\n' +
                "?????????" + magnitude + '???'+'\n' +
                "???????????????" + reportingUnit ;
    }
}
