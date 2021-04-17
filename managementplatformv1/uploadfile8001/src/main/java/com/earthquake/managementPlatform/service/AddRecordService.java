package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Service
public class AddRecordService {
    @Resource
    RestTemplate restTemplate;
    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;

    public PostVo AddRecord(BufferedReader bufferedReader) throws IOException {
        BufferedReaderMethod bufferedReaderMethod = new BufferedReaderMethod();
        JSONObject jsonObject = bufferedReaderMethod.BufferedReaderToJson(bufferedReader);
        String ss = URLEncoder.encode(jsonObject.toString(), "utf-8");
        return restTemplate.postForObject(disasterInfoCodeUrl+"/v1/disasterInfoCodeToRecode",ss, PostVo.class);
    }
}
