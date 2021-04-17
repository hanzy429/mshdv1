package com.earthquake.managementPlatform.entities;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class DisasterInfoCode7Test {
    @Resource
    DisasterInfoCode7 disasterInfoCode7;

    @Test
    public void codeForDisasterInfoTest(){
//        String string = "{\"date\":\"2020-08-30 18:05:02\",\"country\":\"东城区\",\"town\":\"东华门街道办事处\",\"city\":\"市辖区\",\"latitude\":19.57,\"picture\":\"Hydrangeas.jpg\",\"depth\":10,\"province\":\"北京市\",\"grade\":4,\"location\":\"海南文昌市\",\"magnitude\":2.9,\"village\":\"多福巷社区居委会\",\"category\":\"基本灾情\",\"reportingUnit\":\"中国电信公司\",\"longitude\":110.68}";
//        JSONObject jsonObject = new JSONObject(string);
        log.info(disasterInfoCode7.codeForDisasterInfo("140108000000","551","2"));

    }

}