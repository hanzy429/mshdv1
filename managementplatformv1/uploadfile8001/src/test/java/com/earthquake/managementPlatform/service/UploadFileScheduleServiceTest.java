package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UploadFileScheduleServiceTest {

    @Resource
    RestTemplate restTemplate;
    @Resource
    UploadFileService uploadFileService;
    @Resource
    FtpFileMethod ftpFileMethod;

    @Test
    public void TemplateTest() throws Exception {
        Map<JSONArray,String> map = uploadFileService.uploadFiles(ftpFileMethod);
//        log.info(restTemplate.getForObject("http://CLOUD-DISASTERINFOCODE-SEVICE/v1/hello",String.class));
        if(map!=null)
//            log.info(restTemplate.getForObject("http://CLOUD-DISASTERINFOCODE-SEVICE/v1/hello",String.class));
        log.info(restTemplate.postForObject("http://CLOUD-DISASTERINFOCODE-SEVICE/v1/disasterInfoCode",map,ArrayList.class).toString());
        else
            log.info("空");

    }

}