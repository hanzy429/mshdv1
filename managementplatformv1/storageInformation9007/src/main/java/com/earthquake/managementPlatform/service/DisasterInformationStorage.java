package com.earthquake.managementPlatform.service;

import org.json.JSONObject;

public interface  DisasterInformationStorage {
    String storage(String code,String source,JSONObject data);
}
