package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.TrafficDisaster;
import com.earthquake.managementPlatform.entities.WaterDisaster;
import com.earthquake.managementPlatform.mapper.TrafficDisasterMapper;
import com.earthquake.managementPlatform.mapper.WaterDisasterMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class WaterDisasterStorage implements DisasterInformationStorage{
    @Resource
    WaterDisasterMapper waterDisasterMapper;
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
        return storageForWaterDisaster();
    }

    public String storageForWaterDisaster(){

        WaterDisaster waterDisaster = new WaterDisaster();

        waterDisaster.setId(code);

        waterDisaster.setDate(data.getString("date"));

        waterDisaster.setLocation(data.getString("location"));

        waterDisaster.setType(data.get("type").toString());

        waterDisaster.setGrade(data.get("grade").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                waterDisaster.setPicture(data.getString("picture"));
            }
            else{
                waterDisaster.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            waterDisaster.setPicture(null);
        }

        waterDisaster.setNote(data.getString("note"));

        waterDisaster.setReportingUnit(source+data.getString("reportingUnit"));

        waterDisaster.setEarthquakeId(data.getString("earthquakeId"));

        waterDisasterMapper.save(waterDisaster);

        return code+"入库成功";

    }
}
