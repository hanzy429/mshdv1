package com.earthquake.managementPlatform.entities;

public class WaterDisaster {
    private String id;
    private String date;
    private String location;
    private String type;
    private String grade;
    private String picture;
    private String note;
    private String reportingUnit;
    private String earthquakeId;

    public WaterDisaster() {
    }

    public WaterDisaster(String id, String date, String location, String type, String grade, String picture, String note, String reportingUnit, String earthquakeId) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.type = type;
        this.grade = grade;
        this.picture = picture;
        this.note = note;
        this.reportingUnit = reportingUnit;
        this.earthquakeId = earthquakeId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        return "该编码为供水系统灾情统计编码：" + '\n' +
                "地点：" + location + '，'+'\n' +
                "上报日期：" + date + '，'+'\n' +
                "类型共四类（水池或水处理池1，水处理厂2，取水井站及供水泵站3，供水管网4），\n此次为" + type + '，'+'\n' +
                "灾情描述：" + note + '，'+'\n' +
                "上报单位：" + reportingUnit + '，'+'\n' +
                "严重程度评级共五级（毁坏5，严重破坏4，中等破坏3，轻微破坏2，基本完好1），\n此次为" + id.substring(18) ;
    }
}
