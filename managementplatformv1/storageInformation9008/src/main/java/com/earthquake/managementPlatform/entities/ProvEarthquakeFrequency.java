package com.earthquake.managementPlatform.entities;

public class ProvEarthquakeFrequency {
    private String proCode;
    private int count;

    public ProvEarthquakeFrequency()
    {

    }

    public ProvEarthquakeFrequency(String proCode,int count)
    {
        this.proCode = proCode;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProCode() {
        return proCode;
    }
}
