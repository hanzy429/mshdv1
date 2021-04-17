package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.DebrisRecord;
import com.earthquake.managementPlatform.entities.LandslideRecord;
import com.earthquake.managementPlatform.mapper.DebrisRecordMapper;
import com.earthquake.managementPlatform.mapper.LandslideRecordMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class DebrisRecordStorage implements DisasterInformationStorage{
    @Resource
    DebrisRecordMapper debrisRecordMapper;
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
        return storageForDebrisRecord();
    }

    public String storageForDebrisRecord(){

        DebrisRecord debrisRecord = new DebrisRecord();

        debrisRecord.setId(code);

        debrisRecord.setDate(data.getString("date"));

        debrisRecord.setLocation(data.getString("location"));

        debrisRecord.setType(data.get("type").toString());

        debrisRecord.setStatus(data.get("status").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                debrisRecord.setPicture(data.getString("picture"));
            }
            else{
                debrisRecord.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            debrisRecord.setPicture(null);
        }

        debrisRecord.setNote(data.getString("note"));

        debrisRecord.setReportingUnit(source+data.getString("reportingUnit"));

        debrisRecord.setEarthquakeId(data.getString("earthquakeId"));

        debrisRecordMapper.save(debrisRecord);

        return code+"入库成功";

    }
}
