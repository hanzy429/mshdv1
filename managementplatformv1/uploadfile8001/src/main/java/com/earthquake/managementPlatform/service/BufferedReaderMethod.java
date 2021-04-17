package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;


public class BufferedReaderMethod {
    public JSONObject BufferedReaderToJson(BufferedReader bufferedReader) throws IOException {
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = bufferedReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
        return jsonObject;
    }
}
