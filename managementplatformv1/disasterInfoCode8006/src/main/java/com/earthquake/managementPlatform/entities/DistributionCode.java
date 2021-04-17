package com.earthquake.managementPlatform.entities;

public class DistributionCode {
    private String id;
    private int number;

    public DistributionCode(String id) {
        this.id = id;
    }

    public DistributionCode(int number) {
        this.number = number;
    }

    public DistributionCode() {
    }

    public DistributionCode(String id, int number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
