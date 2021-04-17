package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.CivilStructure;
import com.earthquake.managementPlatform.entities.MissingStatistics;
import com.earthquake.managementPlatform.mapper.CivilStructureMapper;
import com.earthquake.managementPlatform.mapper.MissingStatisticsMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class CivilStructureStorage implements DisasterInformationStorage{
    @Resource
    CivilStructureMapper civilStructureMapper;
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
        return storageForCivilStructure();
    }

    public String storageForCivilStructure(){

        CivilStructure civilStructure = new CivilStructure();

        civilStructure.setId(code);

        civilStructure.setDate(data.getString("date"));

        civilStructure.setLocation(data.getString("location"));

        civilStructure.setBasicallyIntactSquare(data.getDouble("basicallyIntactSquare"));

        civilStructure.setDamagedSquare(data.getDouble("damagedSquare"));

        civilStructure.setDestroyedSquare(data.getDouble("destroyedSquare"));

        civilStructure.setNote(data.getString("note"));

        civilStructure.setReportingUnit(source+data.getString("reportingUnit"));

        civilStructure.setEarthquakeId(data.getString("earthquakeId"));

        civilStructureMapper.save(civilStructure);

        return code+"入库成功";

    }
}
