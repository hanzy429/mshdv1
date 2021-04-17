package com.earthquake.managementPlatform.entities;

public class MissingStatistics {
    private String id;
    private String location;
    private String date;
    private int number;
    private String reportingUnit;
    private String earthquakeId;

    public MissingStatistics(String id, String location, String date, int number, String reportingUnit, String earthquakeId) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.number = number;
        this.reportingUnit = reportingUnit;
        this.earthquakeId = earthquakeId;
    }

    public MissingStatistics() {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
        return "该编码为人员失踪统计编码：" + '\n' +
                "失踪地点：" + location + '，'+'\n' +
                "上报日期：" + date + '，'+'\n' +
                "失踪人数：" + number + '，'+'\n' +
                "上报单位：" + reportingUnit + '，'+'\n' +
                "严重程度评级共四级（特大4，大型3，中型2，一般1），\n此次为" + id.substring(18) ;
    }
}
