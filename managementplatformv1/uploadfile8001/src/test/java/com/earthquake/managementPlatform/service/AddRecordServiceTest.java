package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class AddRecordServiceTest {
    @Test
    public void addRecordTest(){
        String ss = "{\"province\":\"天津市\",\"city\":\"市辖区\",\"country\":\"河东区\",\"town\":\"大直沽街道\",\"village\":\"津塘村社区居委会\",\"category\":\"551\",\"grade\":\"3\",\"date\":\"2020-11-03 00:00:00\",\"location\":\"dd\",\"longitude\":\"45\",\"latitude\":\"67\",\"depth\":\"5\",\"magnitude\":\"4\",\"picture\":\"D:/localSave/20201103/picture/手机客户端.png\",\"source\":\"302\",\"reportingUnit\":\"yyy\"}";
        JSONObject jsonObject = new JSONObject(ss);
        log.info(jsonObject.getString("province"));
    }

}