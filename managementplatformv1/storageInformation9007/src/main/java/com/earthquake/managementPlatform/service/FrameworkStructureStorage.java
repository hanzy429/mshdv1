package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.FrameworkStructure;
import com.earthquake.managementPlatform.entities.MasonryStructure;
import com.earthquake.managementPlatform.mapper.FrameworkStructureMapper;
import com.earthquake.managementPlatform.mapper.MasonryStructureMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class FrameworkStructureStorage implements DisasterInformationStorage{
    @Resource
    FrameworkStructureMapper frameworkStructureMapper;
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
        return storageForFrameworkStructure();
    }

    public String storageForFrameworkStructure(){

        FrameworkStructure frameworkStructure = new FrameworkStructure();

        frameworkStructure.setId(code);

        frameworkStructure.setDate(data.getString("date"));

        frameworkStructure.setLocation(data.getString("location"));

        frameworkStructure.setBasicallyIntactSquare(data.getDouble("basicallyIntactSquare"));

        frameworkStructure.setSlightDamagedSquare(data.getDouble("slightDamagedSquare"));

        frameworkStructure.setModerateDamagedSquare(data.getDouble("moderateDamagedSquare"));

        frameworkStructure.setSeriousDamagedSquare(data.getDouble("seriousDamagedSquare"));

        frameworkStructure.setDestroyedSquare(data.getDouble("destroyedSquare"));

        frameworkStructure.setNote(data.getString("note"));

        frameworkStructure.setReportingUnit(source+data.getString("reportingUnit"));

        frameworkStructure.setEarthquakeId(data.getString("earthquakeId"));

        frameworkStructureMapper.save(frameworkStructure);

        return code+"入库成功";

    }
}
