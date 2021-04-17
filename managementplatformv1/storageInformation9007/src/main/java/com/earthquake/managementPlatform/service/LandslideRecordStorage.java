package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.CollapseRecord;
import com.earthquake.managementPlatform.entities.LandslideRecord;
import com.earthquake.managementPlatform.mapper.CollapseRecordMapper;
import com.earthquake.managementPlatform.mapper.LandslideRecordMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class LandslideRecordStorage implements DisasterInformationStorage{
    @Resource
    LandslideRecordMapper landslideRecordMapper;
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
        return storageForLandslideRecord();
    }

    public String storageForLandslideRecord(){

        LandslideRecord landslideRecord = new LandslideRecord();

        landslideRecord.setId(code);

        landslideRecord.setDate(data.getString("date"));

        landslideRecord.setLocation(data.getString("location"));

        landslideRecord.setType(data.get("type").toString());

        landslideRecord.setStatus(data.get("status").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                landslideRecord.setPicture(data.getString("picture"));
            }
            else{
                landslideRecord.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            landslideRecord.setPicture(null);
        }

        landslideRecord.setNote(data.getString("note"));

        landslideRecord.setReportingUnit(source+data.getString("reportingUnit"));

        landslideRecord.setEarthquakeId(data.getString("earthquakeId"));

        landslideRecordMapper.save(landslideRecord);

        return code+"入库成功";

    }
}
