package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MultiMediaMethodServiceTest {
    @Resource
    MultiMediaMethodService multiMediaMethodService;
    @Test
    public void uploadMultiMediaTest() throws IOException {
        String strUrl = "C:\\Users\\chens\\Pictures\\Saved Pictures\\nnn.png";
        File file = new File(strUrl);
        FileInputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile2 = new MockMultipartFile(file.getName(), inputStream);
        log.info(multiMediaMethodService.uploadMultiMedia(multipartFile2));
    }

}