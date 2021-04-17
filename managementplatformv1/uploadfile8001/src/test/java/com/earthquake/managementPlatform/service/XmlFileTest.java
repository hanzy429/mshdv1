package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class XmlFileTest {

    @Test
    public void fileToJsonTest() throws IOException, JSONException {
        DisasterFile disasterFile = new XmlFile("D:\\localSave\\地震信息.xml");
        log.info(disasterFile.fileToJson().get(1).toString());
    }

}