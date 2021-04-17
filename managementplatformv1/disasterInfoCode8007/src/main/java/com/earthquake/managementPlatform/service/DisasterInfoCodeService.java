package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class DisasterInfoCodeService {
    @Resource
    DisasterInfoCode7 disasterInfoCode7;
    @Resource
    AdministrativeRegionCode12 administrativeRegionCode12;
    @Resource
    CategoryCode categoryCode;
    @Resource
    GradeEvaluationFactory gradeEvaluationFactory;

    private AdministrativeRegionCode administrativeRegionCode;
    private DisasterInfoCode disasterInfoCode;

    private JSONObject jsonObject;

    public String assignDisasterInfoCode(JSONObject data){
        String province;
        String city;
        String country;
        String town;
        String village;
        String grade;
        String category;

        try{
            province = data.getString("province");
        }catch (JSONException e){
            province = null;
        }

        try{
            city = data.getString("city");
        }catch (JSONException e){
            city = null;
        }

        try{
            country = data.getString("country");
        }catch (JSONException e){
            country = null;
        }

        try{
            town = data.getString("town");
        }catch (JSONException e){
            town = null;
        }

        try{
            village = data.getString("village");
        }catch (JSONException e){
            village = null;
        }

        administrativeRegionCode12.setAdministrativeRegionCode12(province,city,country,town,village);
        administrativeRegionCode = administrativeRegionCode12;
        String aAdministrativeRegionCode = administrativeRegionCode.codeForAdministrativeRegion();
        categoryCode.setCategoryInfo(data.getString("category"));
        category = categoryCode.codeForCategory();
        if(category.equals("551"))
        {
            EarthquakeCode earthquakeCode = new EarthquakeCodeByTime(aAdministrativeRegionCode,data.getString("date"));
            return earthquakeCode.codeForEarthquakeCode();
        }
        else {

            try{
                grade = data.get("grade").toString();
            }catch (JSONException e){
                try {
                    grade = data.get("status").toString();
                }catch (JSONException E){
                GradeEvaluation gradeEvaluation = gradeEvaluationFactory.createGradeEvaluation(category);
                grade = gradeEvaluation.gradeEvaluation(data);
                }
            }
            disasterInfoCode = disasterInfoCode7;
            return aAdministrativeRegionCode + disasterInfoCode.codeForDisasterInfo(aAdministrativeRegionCode, category, grade );
        }
    }
}
