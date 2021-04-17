package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.DisasterPrediction;
import com.earthquake.managementPlatform.mapper.BasicEarthquakeInfoMapper;
import com.earthquake.managementPlatform.mapper.DisasterPredictionMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.SQLException;

@Slf4j
@Component
public class DisasterPredictionStorage implements DisasterInformationStorage{
    @Resource
    DisasterPredictionMapper disasterPredictionMapper;
    @Resource
    BasicEarthquakeInfoMapper basicEarthquakeInfoMapper;
    @Resource
    RestTemplate restTemplate;
    @Value("${uploadFile.url}")
    private String uploadFileUrl;

    private String source;
    private JSONObject data;

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONObject getData() {
        return data;
    }

    @Override
    public String storage(String code, String source, JSONObject data) {
//        setCode(code);
        setData(data);
        setSource(source);
        return storageForDisasterPrediction();
    }

    private String storageForDisasterPrediction()
    {
        DisasterPrediction disasterPrediction = new DisasterPrediction();
        String D_ID = basicEarthquakeInfoMapper.getLastDisasterInfo().getId();
        disasterPrediction.setD_ID(D_ID);
        disasterPrediction.setS_ID(source);
        try{
            disasterPrediction.setDate(data.getString("date"));
        }catch (Exception e){
            disasterPrediction.setDate(null);
        }

        try{
            disasterPrediction.setGrade(data.get("grade").toString());
        }catch (Exception e){
            disasterPrediction.setGrade(null);
        }

        try{
            disasterPrediction.setIntensity(data.get("intensity").toString());
        }catch (Exception e){
            disasterPrediction.setIntensity(null);
        }

        try{
            disasterPrediction.setType(data.get("type").toString());
        }catch (Exception e){
            disasterPrediction.setType(null);
        }

        try{
            if(data.getString("picture").split("/").length>1)
            {
                disasterPrediction.setPicture(data.getString("picture"));
            }
            else{
                String path = restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class);
                log.info(path);
                disasterPrediction.setPicture(path);
            }
        }catch (Exception e){
            disasterPrediction.setPicture(null);
        }
        try
        {
            disasterPredictionMapper.save(disasterPrediction);
        }catch (Exception e)
        {
            disasterPredictionMapper.update(disasterPrediction);
        }


        return source+"源数据入库成功！";
    }
}
