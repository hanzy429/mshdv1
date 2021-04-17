package com.earthquake.managementPlatform.entities;

import org.json.JSONObject;

public interface DisasterInfoCode {
    String codeForDisasterInfo(String administrativeRegionCode,String categoryInfo,String gradeInfo);
}
