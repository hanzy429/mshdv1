package com.earthquake.managementPlatform.entities;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class GradeEvaluationForMissingStatistics implements GradeEvaluation{
    @Override
    public String gradeEvaluation(JSONObject data) {
        int number = data.getInt("number");
        String gradeCode = "0";
        if(number < 20 && number >= 0)
        {
            gradeCode = "1";
        }
        else if (number >= 20 && number < 50)
        {
            gradeCode = "2";
        }
        else if (number >= 50 && number < 300)
        {
            gradeCode = "3";
        }
        else if (number >= 300)
        {
            gradeCode = "4";
        }
        return gradeCode;
    }
}
