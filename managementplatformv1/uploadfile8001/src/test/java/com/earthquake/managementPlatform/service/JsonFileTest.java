package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class JsonFileTest {

    @Test
    public void fileToJsonTest() throws IOException, JSONException {
        DisasterFile disasterFile = new JsonFile("D:\\localSave\\地震了.json");
        log.info(disasterFile.fileToJson().get(0).toString());
    }

}