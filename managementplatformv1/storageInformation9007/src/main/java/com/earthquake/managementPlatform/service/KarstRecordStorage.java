package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.DebrisRecord;
import com.earthquake.managementPlatform.entities.KarstRecord;
import com.earthquake.managementPlatform.mapper.DebrisRecordMapper;
import com.earthquake.managementPlatform.mapper.KarstRecordMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class KarstRecordStorage implements DisasterInformationStorage{
    @Resource
    KarstRecordMapper karstRecordMapper;
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
        return storageForKarstRecord();
    }

    public String storageForKarstRecord(){

        KarstRecord karstRecord = new KarstRecord();

        karstRecord.setId(code);

        karstRecord.setDate(data.getString("date"));

        karstRecord.setLocation(data.getString("location"));

        karstRecord.setType(data.get("type").toString());

        karstRecord.setStatus(data.get("status").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                karstRecord.setPicture(data.getString("picture"));
            }
            else{
                karstRecord.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            karstRecord.setPicture(null);
        }

        karstRecord.setNote(data.getString("note"));

        karstRecord.setReportingUnit(source+data.getString("reportingUnit"));

        karstRecord.setEarthquakeId(data.getString("earthquakeId"));

        karstRecordMapper.save(karstRecord);

        return code+"入库成功";

    }
}
