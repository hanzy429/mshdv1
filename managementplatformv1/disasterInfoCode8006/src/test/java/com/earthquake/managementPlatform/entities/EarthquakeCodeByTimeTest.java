package com.earthquake.managementPlatform.entities;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class EarthquakeCodeByTimeTest {
    @Resource
    AdministrativeRegionCode12 administrativeRegionCode12;

    @Test
    public void codeForEarthquakeCodeTest(){
        administrativeRegionCode12.setAdministrativeRegionCode12("山西省","太原市","尖草坪区",null,null);
        EarthquakeCodeByTime earthquakeCodeByTime = new EarthquakeCodeByTime(administrativeRegionCode12.codeForAdministrativeRegion(),"2020-11-14 00:42:22");
        log.info(earthquakeCodeByTime.codeForEarthquakeCode());

    }

}