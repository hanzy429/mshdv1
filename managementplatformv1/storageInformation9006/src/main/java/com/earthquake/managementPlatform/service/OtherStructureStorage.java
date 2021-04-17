package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.MasonryStructure;
import com.earthquake.managementPlatform.entities.OtherStructure;
import com.earthquake.managementPlatform.mapper.MasonryStructureMapper;
import com.earthquake.managementPlatform.mapper.OtherStructureMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OtherStructureStorage implements DisasterInformationStorage{
    @Resource
    OtherStructureMapper otherStructureMapper;
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
        return storageForOtherStructure();
    }

    public String storageForOtherStructure(){

        OtherStructure otherStructure = new OtherStructure();

        otherStructure.setId(code);

        otherStructure.setDate(data.getString("date"));

        otherStructure.setLocation(data.getString("location"));

        otherStructure.setBasicallyIntactSquare(data.getDouble("basicallyIntactSquare"));

        otherStructure.setSlightDamagedSquare(data.getDouble("slightDamagedSquare"));

        otherStructure.setModerateDamagedSquare(data.getDouble("moderateDamagedSquare"));

        otherStructure.setSeriousDamagedSquare(data.getDouble("seriousDamagedSquare"));

        otherStructure.setDestroyedSquare(data.getDouble("destroyedSquare"));

        otherStructure.setNote(data.getString("note"));

        otherStructure.setReportingUnit(source+data.getString("reportingUnit"));

        otherStructure.setEarthquakeId(data.getString("earthquakeId"));

        otherStructureMapper.save(otherStructure);

        return code+"入库成功";

    }
}
