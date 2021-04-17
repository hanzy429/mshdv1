package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.InjuredStatistics;
import com.earthquake.managementPlatform.entities.MissingStatistics;
import com.earthquake.managementPlatform.mapper.InjuredStatisticsMapper;
import com.earthquake.managementPlatform.mapper.MissingStatisticsMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class MissingStatisticsStorage implements DisasterInformationStorage{
    @Resource
    MissingStatisticsMapper missingStatisticsMapper;
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
        return storageForMissingStatistics();
    }

    public String storageForMissingStatistics(){

        MissingStatistics missingStatistics = new MissingStatistics();

        missingStatistics.setId(code);

        missingStatistics.setDate(data.getString("date"));

        missingStatistics.setLocation(data.getString("location"));

        missingStatistics.setNumber(data.getInt("number"));

        missingStatistics.setReportingUnit(source+data.getString("reportingUnit"));

        missingStatistics.setEarthquakeId(data.getString("earthquakeId"));

        missingStatisticsMapper.save(missingStatistics);

        return code+"入库成功";

    }
}
