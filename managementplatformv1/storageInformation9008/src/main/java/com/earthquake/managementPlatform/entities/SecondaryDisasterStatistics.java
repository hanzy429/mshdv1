package com.earthquake.managementPlatform.entities;

public class SecondaryDisasterStatistics {
    private String status;
    private int count;
    public SecondaryDisasterStatistics()
    {}

    public SecondaryDisasterStatistics(String status,int count)
    {
        this.status = status;
        this.count = count;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
