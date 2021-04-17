package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UploadFileServiceTest {
    @Autowired
    UploadFileService uploadFileService;
    @Resource
    FtpFileMethod ftpFileMethod;

    //@Test
    public void uploadFilesTest() throws Exception {
        log.info(uploadFileService.uploadFiles(ftpFileMethod).toString());
    }

    @Test
    public void uploadRealTimeFilesTest() throws Exception {
        Map<JSONArray,String> map= uploadFileService.uploadRealTimeFiles("501");
        if(map == null)
        {
            log.info("目前无文件");
        }
        else
            log.info(map.toString());
    }

}