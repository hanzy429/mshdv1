package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.PowerDisaster;
import com.earthquake.managementPlatform.mapper.GasDisasterMapper;
import com.earthquake.managementPlatform.mapper.PowerDisasterMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class PowerDisasterStorage implements DisasterInformationStorage{
    @Resource
    PowerDisasterMapper powerDisasterMapper;
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
        return storageForPowerDisaster();
    }

    public String storageForPowerDisaster(){

        PowerDisaster powerDisaster = new PowerDisaster();

        powerDisaster.setId(code);

        powerDisaster.setDate(data.getString("date"));

        powerDisaster.setLocation(data.getString("location"));

        powerDisaster.setType(data.get("type").toString());

        powerDisaster.setGrade(data.get("grade").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                powerDisaster.setPicture(data.getString("picture"));
            }
            else{
                powerDisaster.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            powerDisaster.setPicture(null);
        }

        powerDisaster.setNote(data.getString("note"));

        powerDisaster.setReportingUnit(source+data.getString("reportingUnit"));

        powerDisaster.setEarthquakeId(data.getString("earthquakeId"));

        powerDisasterMapper.save(powerDisaster);

        return code+"入库成功";

    }
}
