package com.earthquake.managementPlatform.entities;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class DisasterInfoCode7 implements DisasterInfoCode{

    @Resource
    CategoryCode categoryCode;
    @Resource
    SequenceCode sequenceCode;
    @Resource
    GradeCode gradeCode;




    public String performCategory(String categoryInfo){
        categoryCode.setCategoryInfo(categoryInfo);
        return categoryCode.codeForCategory();
    }

    public String performSequence(String administrativeRegionCode,String categoryCode){
        return sequenceCode.CodeForSequence(administrativeRegionCode,categoryCode);
    }

    public String performGrade(String gradeInfo){
        gradeCode.setGradeInfo(gradeInfo);
        return gradeCode.codeForGrade();
    }

    @Override
    public String codeForDisasterInfo(String administrativeRegionCode,String categoryInfo,String gradeInfo) {
//        administrativeRegionCode12.setAdministrativeRegionCode12(province,city,country,town,village);
//        AdministrativeRegionCode administrativeRegionCode = administrativeRegionCode12;
        return performCategory(categoryInfo)+performSequence(administrativeRegionCode,performCategory(categoryInfo))+performGrade(gradeInfo);
    }
}
