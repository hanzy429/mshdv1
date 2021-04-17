package com.earthquake.managementPlatform.entities;

public class LandslideRecord {
    private String id;
    private String location;
    private String date;
    private String type;
    private String status;
    private String note;
    private String picture;
    private String reportingUnit;
    private String earthquakeId;

    public LandslideRecord() {
    }

    public LandslideRecord(String id, String location, String date, String type, String status, String note, String picture, String reportingUnit, String earthquakeId) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.type = type;
        this.status = status;
        this.note = note;
        this.picture = picture;
        this.reportingUnit = reportingUnit;
        this.earthquakeId = earthquakeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getEarthquakeId() {
        return earthquakeId;
    }

    public void setEarthquakeId(String earthquakeId) {
        this.earthquakeId = earthquakeId;
    }

    @Override
    public String toString() {
        return "该编码为滑坡记录编码：" + '\n' +
                "地点：" + location + '，'+'\n' +
                "上报日期：" + date + '，'+'\n' +
                "类型共四类（特大型1，大型2，中型3，小型4），\n此次为" + type + '，'+'\n' +
                "灾情描述：" + note + '，'+'\n' +
                "上报单位：" + reportingUnit + '，'+'\n' +
                "严重程度评级共四级（一般4，较大3，.重大2，特大1），\n此次为" + id.substring(18) ;
    }
}
