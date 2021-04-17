package com.earthquake.managementPlatform.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class DisasterInformationStorageService {
    @Resource
    DisasterInfoFactory disasterInfoFactory;

    @Transactional
    public String disasterInformationStorage(String code, String source, JSONObject data){
        DisasterInformationStorage disasterInformationStorage = disasterInfoFactory.createDisasterInformationStorage(code);
        return disasterInformationStorage.storage(code, source, data);
    }

    @Transactional
    public String disasterPredictionInformationStorage(String source, JSONObject data){
        DisasterInformationStorage disasterInformationStorage = disasterInfoFactory.createDisasterInformationStorage(source);
        return disasterInformationStorage.storage(null, source, data);
    }

}
