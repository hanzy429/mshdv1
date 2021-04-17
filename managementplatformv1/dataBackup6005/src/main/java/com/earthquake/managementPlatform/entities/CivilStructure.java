package com.earthquake.managementPlatform.entities;

public class CivilStructure {
    private String id;
    private String date;
    private String location;
    private Double basicallyIntactSquare;
    private Double damagedSquare;
    private Double destroyedSquare;
    private String note;
    private String reportingUnit;
    private String earthquakeId;

    public CivilStructure() {
    }

    public CivilStructure(String id, String date, String location, Double basicallyIntactSquare, Double damagedSquare, Double destroyedSquare, String note, String reportingUnit, String earthquakeId) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.basicallyIntactSquare = basicallyIntactSquare;
        this.damagedSquare = damagedSquare;
        this.destroyedSquare = destroyedSquare;
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

    public Double getBasicallyIntactSquare() {
        return basicallyIntactSquare;
    }

    public void setBasicallyIntactSquare(Double basicallyIntactSquare) {
        this.basicallyIntactSquare = basicallyIntactSquare;
    }

    public Double getDamagedSquare() {
        return damagedSquare;
    }

    public void setDamagedSquare(Double damagedSquare) {
        this.damagedSquare = damagedSquare;
    }

    public Double getDestroyedSquare() {
        return destroyedSquare;
    }

    public void setDestroyedSquare(Double destroyedSquare) {
        this.destroyedSquare = destroyedSquare;
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
        return "该编码为土木结构房屋破坏统计编码：" + '\n' +
                "地点：" + location + '，'+'\n' +
                "上报日期：" + date + '，'+'\n' +
                "基本完好面积：" + basicallyIntactSquare + '，'+'\n' +
                "破坏面积：" + damagedSquare + '，'+'\n' +
                "毁坏面积：" + destroyedSquare + '，'+'\n' +
                "破坏情况描述：" + note + '，'+'\n' +
                "上报单位：" + reportingUnit + '，'+'\n' +
                "严重程度评级共四级（特大4，大型3，中型2，一般1），\n此次为" + id.substring(18) ;
    }
}
