package com.earthquake.managementPlatform.entities;

public class LifeLineStatistics {
    private String grade;
    private int count;

    public LifeLineStatistics()
    {}

    public LifeLineStatistics(String grade,int count)
    {
        this.grade = grade;
        this.count = count;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
