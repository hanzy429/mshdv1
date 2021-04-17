package com.earthquake.managementPlatform.entities;

import org.springframework.stereotype.Repository;

@Repository
public class GradeCode {
    String gradeInfo;

    public GradeCode(String gradeInfo) {
        this.gradeInfo = gradeInfo;
    }

    public GradeCode() {
    }

    public String getGradeInfo() {
        return gradeInfo;
    }

    public void setGradeInfo(String gradeInfo) {
        this.gradeInfo = gradeInfo;
    }

    public String codeForGrade(){
        String gradeCode;
        if(gradeInfo!=null){
            gradeCode = gradeInfo;
        }
        else gradeCode = "0";
        return gradeCode;

    }
}
