package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class DisasterInfoCodeServiceTest {
    @Resource
    DisasterInfoCodeService disasterInfoCodeService;

    @Test
    public void assignDisasterInfoCodeTest(){
        String string = "{\"date\":\"2020-08-30 18:05:02\",\"country\":\"东城区\",\"town\":\"东华门街道办事处\",\"city\":\"市辖区\",\"latitude\":19.57,\"picture\":\"Hydrangeas.jpg\",\"depth\":10,\"province\":\"北京市\",\"grade\":4,\"location\":\"海南文昌市\",\"magnitude\":2.9,\"category\":\"基本灾情\",\"reportingUnit\":\"中国电信公司\",\"longitude\":110.68}";
//        String string = "{\"date\":\"2020-08-30 18:05:02\",\"country\":\"东城区\",\"town\":\"东华门街道办事处\",\"city\":\"市辖区\",\"latitude\":19.57,\"picture\":\"Hydrangeas.jpg\",\"depth\":10,\"province\":\"北京市\",\"grade\":4,\"location\":\"海南文昌市\",\"magnitude\":2.9,\"village\":\"多福巷社区居委会\",\"category\":\"基本灾情\",\"reportingUnit\":\"中国电信公司\",\"longitude\":110.68}";
        JSONObject jsonObject = new JSONObject(string);
        log.info(disasterInfoCodeService.assignDisasterInfoCode(jsonObject));

    }


}