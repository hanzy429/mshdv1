package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.mapper.BasicEarthquakeInfoMapper;
import com.earthquake.managementPlatform.mapper.DeathStatisticsMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class DeathStatisticsStorage implements DisasterInformationStorage{
    @Resource
    DeathStatisticsMapper deathStatisticsMapper;
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
        return storageForDeathStatistics();
    }

    public String storageForDeathStatistics(){

        DeathStatistics deathStatistics = new DeathStatistics();

        deathStatistics.setId(code);

        deathStatistics.setDate(data.getString("date"));

        deathStatistics.setLocation(data.getString("location"));

        deathStatistics.setNumber(data.getInt("number"));

        deathStatistics.setReportingUnit(source+data.getString("reportingUnit"));

        deathStatistics.setEarthquakeId(data.getString("earthquakeId"));

        deathStatisticsMapper.save(deathStatistics);

        return code+"入库成功";

    }
}
