package com.earthquake.managementPlatform.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
class CodeResourceTest {
    @Resource
    RestTemplate restTemplate;

    @Value("${storageInformation.url}")
    private String storageInformationUrl;

    @Test
    public void resTemplateTest(){
        Map<String,String> map = new HashMap<String,String>();
        String string = "{\"date\":\"2020-08-30 18:05:02\",\"country\":\"东城区\",\"town\":\"东华门街道办事处\",\"city\":\"市辖区\",\"latitude\":19.57,\"picture\":\"Hydrangeas.jpg\",\"depth\":10,\"province\":\"北京市\",\"grade\":4,\"location\":\"海南文昌市\",\"magnitude\":2.9,\"village\":\"多福巷社区居委会\",\"category\":\"基本灾情\",\"reportingUnit\":\"中国电信公司\",\"longitude\":110.68}";
        map.put("data",string);
        map.put("code","1101010010015510708");
        map.put("source","101");
//        DisasterInfoCodeWithStorageInformation disasterInfoCodeWithStorageInformation = new DisasterInfoCodeWithStorageInformation("1101010010015510708",jsonObject, "101");
        log.info(restTemplate.postForObject(storageInformationUrl+"/v1/informationStorage",map,String.class));
//        log.info(restTemplate.getForObject(storageInformationUrl+"/v1/hello",String.class));
    }


}