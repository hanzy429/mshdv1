package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.TrafficDisaster;
import com.earthquake.managementPlatform.mapper.TrafficDisasterMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class TrafficDisasterStorage implements DisasterInformationStorage{
    @Resource
    TrafficDisasterMapper trafficDisasterMapper;
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
        return storageForTrafficDisaster();
    }

    public String storageForTrafficDisaster(){

        TrafficDisaster trafficDisaster = new TrafficDisaster();

        trafficDisaster.setId(code);

        trafficDisaster.setDate(data.getString("date"));

        trafficDisaster.setLocation(data.getString("location"));

        trafficDisaster.setType(data.get("type").toString());

        trafficDisaster.setGrade(data.get("grade").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                trafficDisaster.setPicture(data.getString("picture"));
            }
            else{
                trafficDisaster.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            trafficDisaster.setPicture(null);
        }

        trafficDisaster.setNote(data.getString("note"));

        trafficDisaster.setReportingUnit(source+data.getString("reportingUnit"));

        trafficDisaster.setEarthquakeId(data.getString("earthquakeId"));

        trafficDisasterMapper.save(trafficDisaster);

        return code+"入库成功";

    }
}
