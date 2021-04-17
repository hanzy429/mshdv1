package com.earthquake.managementPlatform.entities;

public class SquareStatistics {
    private String date;
    private double square;
    public SquareStatistics()
    {}
    public SquareStatistics(String date,double square)
    {
        this.date = date;
        this.square = square;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getSquare() {
        return square;
    }
}
