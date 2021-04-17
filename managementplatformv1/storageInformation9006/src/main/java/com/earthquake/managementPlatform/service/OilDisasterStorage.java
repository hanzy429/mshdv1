package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.OilDisaster;
import com.earthquake.managementPlatform.entities.WaterDisaster;
import com.earthquake.managementPlatform.mapper.OilDisasterMapper;
import com.earthquake.managementPlatform.mapper.WaterDisasterMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OilDisasterStorage implements DisasterInformationStorage{
    @Resource
    OilDisasterMapper oilDisasterMapper;
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
        return storageForOilDisaster();
    }

    public String storageForOilDisaster(){

        OilDisaster oilDisaster = new OilDisaster();

        oilDisaster.setId(code);

        oilDisaster.setDate(data.getString("date"));

        oilDisaster.setLocation(data.getString("location"));

        oilDisaster.setType(data.get("type").toString());

        oilDisaster.setGrade(data.get("grade").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                oilDisaster.setPicture(data.getString("picture"));
            }
            else{
                oilDisaster.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            oilDisaster.setPicture(null);
        }

        oilDisaster.setNote(data.getString("note"));

        oilDisaster.setReportingUnit(source+data.getString("reportingUnit"));

        oilDisaster.setEarthquakeId(data.getString("earthquakeId"));

        oilDisasterMapper.save(oilDisaster);

        return code+"入库成功";

    }
}
