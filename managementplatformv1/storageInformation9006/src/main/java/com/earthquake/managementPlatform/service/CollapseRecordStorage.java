package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.CollapseRecord;
import com.earthquake.managementPlatform.entities.IrrigationDisaster;
import com.earthquake.managementPlatform.mapper.CollapseRecordMapper;
import com.earthquake.managementPlatform.mapper.IrrigationDisasterMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class CollapseRecordStorage implements DisasterInformationStorage{
    @Resource
    CollapseRecordMapper collapseRecordMapper;
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
        return storageForCollapseRecord();
    }

    public String storageForCollapseRecord(){

        CollapseRecord collapseRecord = new CollapseRecord();

        collapseRecord.setId(code);

        collapseRecord.setDate(data.getString("date"));

        collapseRecord.setLocation(data.getString("location"));

        collapseRecord.setType(data.get("type").toString());

        collapseRecord.setStatus(data.get("status").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                collapseRecord.setPicture(data.getString("picture"));
            }
            else{
                collapseRecord.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            collapseRecord.setPicture(null);
        }

        collapseRecord.setNote(data.getString("note"));

        collapseRecord.setReportingUnit(source+data.getString("reportingUnit"));

        collapseRecord.setEarthquakeId(data.getString("earthquakeId"));

        collapseRecordMapper.save(collapseRecord);

        return code+"入库成功";

    }
}
