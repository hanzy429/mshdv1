package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.CrackRecord;
import com.earthquake.managementPlatform.entities.KarstRecord;
import com.earthquake.managementPlatform.mapper.CrackRecordMapper;
import com.earthquake.managementPlatform.mapper.KarstRecordMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class CrackRecordStorage implements DisasterInformationStorage{
    @Resource
    CrackRecordMapper crackRecordMapper;
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
        return storageForCrackRecord();
    }

    public String storageForCrackRecord(){

        CrackRecord crackRecord = new CrackRecord();

        crackRecord.setId(code);

        crackRecord.setDate(data.getString("date"));

        crackRecord.setLocation(data.getString("location"));

        crackRecord.setType(data.get("type").toString());

        crackRecord.setStatus(data.get("status").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                crackRecord.setPicture(data.getString("picture"));
            }
            else{
                crackRecord.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            crackRecord.setPicture(null);
        }

        crackRecord.setNote(data.getString("note"));

        crackRecord.setReportingUnit(source+data.getString("reportingUnit"));

        crackRecord.setEarthquakeId(data.getString("earthquakeId"));

        crackRecordMapper.save(crackRecord);

        return code+"入库成功";

    }
}
