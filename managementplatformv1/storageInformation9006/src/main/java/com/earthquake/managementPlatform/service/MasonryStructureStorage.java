package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import com.earthquake.managementPlatform.entities.MasonryStructure;
import com.earthquake.managementPlatform.mapper.BrickwoodStructureMapper;
import com.earthquake.managementPlatform.mapper.MasonryStructureMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class MasonryStructureStorage implements DisasterInformationStorage{
    @Resource
    MasonryStructureMapper masonryStructureMapper;
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
        return storageForMasonryStructure();
    }

    public String storageForMasonryStructure(){

        MasonryStructure masonryStructure = new MasonryStructure();

        masonryStructure.setId(code);

        masonryStructure.setDate(data.getString("date"));

        masonryStructure.setLocation(data.getString("location"));

        masonryStructure.setBasicallyIntactSquare(data.getDouble("basicallyIntactSquare"));

        masonryStructure.setSlightDamagedSquare(data.getDouble("slightDamagedSquare"));

        masonryStructure.setModerateDamagedSquare(data.getDouble("moderateDamagedSquare"));

        masonryStructure.setSeriousDamagedSquare(data.getDouble("seriousDamagedSquare"));

        masonryStructure.setDestroyedSquare(data.getDouble("destroyedSquare"));

        masonryStructure.setNote(data.getString("note"));

        masonryStructure.setReportingUnit(source+data.getString("reportingUnit"));

        masonryStructure.setEarthquakeId(data.getString("earthquakeId"));

        masonryStructureMapper.save(masonryStructure);

        return code+"入库成功";

    }
}
