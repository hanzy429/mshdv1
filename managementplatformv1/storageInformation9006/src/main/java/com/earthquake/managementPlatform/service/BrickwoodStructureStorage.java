package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import com.earthquake.managementPlatform.entities.CivilStructure;
import com.earthquake.managementPlatform.mapper.BrickwoodStructureMapper;
import com.earthquake.managementPlatform.mapper.CivilStructureMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class BrickwoodStructureStorage implements DisasterInformationStorage{
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;
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
        return storageForBrickwoodStructure();
    }

    public String storageForBrickwoodStructure(){

        BrickwoodStructure brickwoodStructure = new BrickwoodStructure();

        brickwoodStructure.setId(code);

        brickwoodStructure.setDate(data.getString("date"));

        brickwoodStructure.setLocation(data.getString("location"));

        brickwoodStructure.setBasicallyIntactSquare(data.getDouble("basicallyIntactSquare"));

        brickwoodStructure.setDamagedSquare(data.getDouble("damagedSquare"));

        brickwoodStructure.setDestroyedSquare(data.getDouble("destroyedSquare"));

        brickwoodStructure.setNote(data.getString("note"));

        brickwoodStructure.setReportingUnit(source+data.getString("reportingUnit"));

        brickwoodStructure.setEarthquakeId(data.getString("earthquakeId"));

        brickwoodStructureMapper.save(brickwoodStructure);

        return code+"入库成功";

    }
}
