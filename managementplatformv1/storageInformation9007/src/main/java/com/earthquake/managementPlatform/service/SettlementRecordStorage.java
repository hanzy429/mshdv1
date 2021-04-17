package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.CrackRecord;
import com.earthquake.managementPlatform.entities.SettlementRecord;
import com.earthquake.managementPlatform.mapper.CrackRecordMapper;
import com.earthquake.managementPlatform.mapper.SettlementRecordMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class SettlementRecordStorage implements DisasterInformationStorage{
    @Resource
    SettlementRecordMapper settlementRecordMapper;
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
        return storageForSettlementRecord();
    }

    public String storageForSettlementRecord(){

        SettlementRecord settlementRecord = new SettlementRecord();

        settlementRecord.setId(code);

        settlementRecord.setDate(data.getString("date"));

        settlementRecord.setLocation(data.getString("location"));

        settlementRecord.setType(data.get("type").toString());

        settlementRecord.setStatus(data.get("status").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                settlementRecord.setPicture(data.getString("picture"));
            }
            else{
                settlementRecord.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            settlementRecord.setPicture(null);
        }

        settlementRecord.setNote(data.getString("note"));

        settlementRecord.setReportingUnit(source+data.getString("reportingUnit"));

        settlementRecord.setEarthquakeId(data.getString("earthquakeId"));

        settlementRecordMapper.save(settlementRecord);

        return code+"入库成功";

    }
}
