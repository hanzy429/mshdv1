package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.entities.InjuredStatistics;
import com.earthquake.managementPlatform.mapper.DeathStatisticsMapper;
import com.earthquake.managementPlatform.mapper.InjuredStatisticsMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class InjuredStatisticsStorage implements DisasterInformationStorage{
    @Resource
    InjuredStatisticsMapper injuredStatisticsMapper;
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
        return storageForInjuredStatistics();
    }

    public String storageForInjuredStatistics(){

        InjuredStatistics injuredStatistics = new InjuredStatistics();

        injuredStatistics.setId(code);

        injuredStatistics.setDate(data.getString("date"));

        injuredStatistics.setLocation(data.getString("location"));

        injuredStatistics.setNumber(data.getInt("number"));

        injuredStatistics.setReportingUnit(source+data.getString("reportingUnit"));

        injuredStatistics.setEarthquakeId(data.getString("earthquakeId"));

        injuredStatisticsMapper.save(injuredStatistics);

        return code+"入库成功";

    }
}
