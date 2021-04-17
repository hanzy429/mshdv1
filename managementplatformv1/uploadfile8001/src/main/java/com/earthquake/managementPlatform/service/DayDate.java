package com.earthquake.managementPlatform.service;

import java.util.Calendar;
import java.util.Date;

public class DayDate {
    private Date date;

    public DayDate(){
        this.date = new Date();
    }

    public DayDate(Date date) {
        this.date = date;
    }

    public String getYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public String getMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH)+1;
        return String.valueOf(month < 10 ? "0" + month : month);
    }

    public String getDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        return String.valueOf(day < 10 ? "0" + day : day);
    }

}
