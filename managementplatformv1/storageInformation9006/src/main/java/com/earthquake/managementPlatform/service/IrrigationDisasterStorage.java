package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.IrrigationDisaster;
import com.earthquake.managementPlatform.entities.PowerDisaster;
import com.earthquake.managementPlatform.mapper.IrrigationDisasterMapper;
import com.earthquake.managementPlatform.mapper.PowerDisasterMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class IrrigationDisasterStorage implements DisasterInformationStorage{
    @Resource
    IrrigationDisasterMapper irrigationDisasterMapper;
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
        return storageForIrrigationDisaster();
    }

    public String storageForIrrigationDisaster(){

        IrrigationDisaster irrigationDisaster = new IrrigationDisaster();

        irrigationDisaster.setId(code);

        irrigationDisaster.setDate(data.getString("date"));

        irrigationDisaster.setLocation(data.getString("location"));

        irrigationDisaster.setType(data.get("type").toString());

        irrigationDisaster.setGrade(data.get("grade").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                irrigationDisaster.setPicture(data.getString("picture"));
            }
            else{
                irrigationDisaster.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            irrigationDisaster.setPicture(null);
        }

        irrigationDisaster.setNote(data.getString("note"));

        irrigationDisaster.setReportingUnit(source+data.getString("reportingUnit"));

        irrigationDisaster.setEarthquakeId(data.getString("earthquakeId"));

        irrigationDisasterMapper.save(irrigationDisaster);

        return code+"入库成功";

    }
}
