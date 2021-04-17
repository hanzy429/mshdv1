package com.earthquake.managementPlatform.entities;

public class PersonStatistics {
    private String date;
    private int number;
    public PersonStatistics()
    {}
    public PersonStatistics(String date,int number)
    {
        this.date = date;
        this.number = number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
