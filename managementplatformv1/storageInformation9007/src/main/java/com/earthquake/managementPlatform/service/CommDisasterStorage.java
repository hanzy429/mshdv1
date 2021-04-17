package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.CommDisaster;
import com.earthquake.managementPlatform.entities.OtherStructure;
import com.earthquake.managementPlatform.mapper.CommDisasterMapper;
import com.earthquake.managementPlatform.mapper.OtherStructureMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class CommDisasterStorage implements DisasterInformationStorage{
    @Resource
    CommDisasterMapper commDisasterMapper;
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
        return storageForCommDisaster();
    }

    public String storageForCommDisaster(){

        CommDisaster commDisaster = new CommDisaster();

        commDisaster.setId(code);

        commDisaster.setDate(data.getString("date"));

        commDisaster.setLocation(data.getString("location"));

        commDisaster.setType(data.get("type").toString());

        commDisaster.setGrade(data.get("grade").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                commDisaster.setPicture(data.getString("picture"));
            }
            else{
                commDisaster.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            commDisaster.setPicture(null);
        }

        commDisaster.setNote(data.getString("note"));

        commDisaster.setReportingUnit(source+data.getString("reportingUnit"));

        commDisaster.setEarthquakeId(data.getString("earthquakeId"));

        commDisasterMapper.save(commDisaster);

        return code+"入库成功";

    }
}
