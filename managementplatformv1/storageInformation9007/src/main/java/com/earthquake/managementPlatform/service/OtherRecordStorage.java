package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.OtherRecord;
import com.earthquake.managementPlatform.entities.SettlementRecord;
import com.earthquake.managementPlatform.mapper.OtherRecordMapper;
import com.earthquake.managementPlatform.mapper.SettlementRecordMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OtherRecordStorage implements DisasterInformationStorage{
    @Resource
    OtherRecordMapper otherRecordMapper;
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
        return storageForOtherRecord();
    }

    public String storageForOtherRecord(){

        OtherRecord otherRecord = new OtherRecord();

        otherRecord.setId(code);

        otherRecord.setDate(data.getString("date"));

        otherRecord.setLocation(data.getString("location"));

        otherRecord.setType(data.get("type").toString());

        otherRecord.setStatus(data.get("status").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                otherRecord.setPicture(data.getString("picture"));
            }
            else{
                otherRecord.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            otherRecord.setPicture(null);
        }

        otherRecord.setNote(data.getString("note"));

        otherRecord.setReportingUnit(source+data.getString("reportingUnit"));

        otherRecord.setEarthquakeId(data.getString("earthquakeId"));

        otherRecordMapper.save(otherRecord);

        return code+"入库成功";

    }
}
