package com.earthquake.managementPlatform.entities;

import javax.annotation.Resource;

public class EarthquakeCodeByTime implements EarthquakeCode{

    private String administrativeRegionCode;
    private String time;

    public EarthquakeCodeByTime(String  administrativeRegionCode,String time) {
        this.administrativeRegionCode = administrativeRegionCode;
        this.time = time;
    }
    @Override
    public String codeForEarthquakeCode() {
        String time = this.time.replace("-","").replace(" ","").replace(":","");
        return administrativeRegionCode+time;
    }
}
