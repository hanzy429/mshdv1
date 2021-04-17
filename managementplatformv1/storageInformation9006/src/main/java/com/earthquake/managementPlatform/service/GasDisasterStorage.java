package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.OilDisaster;
import com.earthquake.managementPlatform.mapper.GasDisasterMapper;
import com.earthquake.managementPlatform.mapper.OilDisasterMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class GasDisasterStorage implements DisasterInformationStorage{
    @Resource
    GasDisasterMapper gasDisasterMapper;
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
        return storageForGasDisaster();
    }

    public String storageForGasDisaster(){

        GasDisaster gasDisaster = new GasDisaster();

        gasDisaster.setId(code);

        gasDisaster.setDate(data.getString("date"));

        gasDisaster.setLocation(data.getString("location"));

        gasDisaster.setType(data.get("type").toString());

        gasDisaster.setGrade(data.get("grade").toString());

        try{
            if(data.getString("picture").split("/").length>1)
            {
                gasDisaster.setPicture(data.getString("picture"));
            }
            else{
                gasDisaster.setPicture(restTemplate.postForObject(uploadFileUrl + "/v1/filePic"+"/"+source+"/"+data.getString("picture"), null, String.class));
            }
        }catch (Exception e){
            gasDisaster.setPicture(null);
        }

        gasDisaster.setNote(data.getString("note"));

        gasDisaster.setReportingUnit(source+data.getString("reportingUnit"));

        gasDisaster.setEarthquakeId(data.getString("earthquakeId"));

        gasDisasterMapper.save(gasDisaster);

        return code+"入库成功";

    }
}
