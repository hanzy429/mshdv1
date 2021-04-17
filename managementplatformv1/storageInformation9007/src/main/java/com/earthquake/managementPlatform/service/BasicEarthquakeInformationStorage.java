package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.mapper.BasicEarthquakeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@Service
public class BasicEarthquakeInformationStorage implements DisasterInformationStorage{
    @Resource
    BasicEarthquakeInfoMapper basicEarthquakeInfoMapper;
    @Resource
    RestTemplate restTemplate;
    @Value("${uploadFile.url}")
    private String uploadFileUrl;

    private String code;
    private JSONObject data;
    private String source;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String storage(String code, String source, JSONObject data) {
        setCode(code);
        setData(data);
        setSource(source);
        return storageForBasicEarthquakeInformation();
    }

    public String storageForBasicEarthquakeInformation(){

        BasicEarthquakeInfo basicEarthquakeInfo = new BasicEarthquakeInfo();

        basicEarthquakeInfo.setId(code);

        basicEarthquakeInfo.setDate(data.getString("date"));

        basicEarthquakeInfo.setLocation(data.getString("location"));

        basicEarthquakeInfo.setLongitude(data.getFloat("longitude"));

        basicEarthquakeInfo.setLatitude(data.getFloat("latitude"));

        basicEarthquakeInfo.setDepth(data.getFloat("depth"));

        basicEarthquakeInfo.setMagnitude(data.getFloat("magnitude"));

//        basicEarthquakeInfo.setPicture(null);

//        basicEarthquakeInfo.setPicture(data.getString("picture"));

        try{
            if(data.getString("picture").split("/").length>1)
            {
                basicEarthquakeInfo.setPicture(data.getString("picture"));
            }
            else{
                basicEarthquakeInfo.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            basicEarthquakeInfo.setPicture(null);
        }
        basicEarthquakeInfo.setReportingUnit(source+data.getString("reportingUnit"));

        basicEarthquakeInfoMapper.save(basicEarthquakeInfo);

        return code+"入库成功";

    }
}
