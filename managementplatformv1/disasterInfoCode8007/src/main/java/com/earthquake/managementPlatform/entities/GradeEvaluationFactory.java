package com.earthquake.managementPlatform.entities;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GradeEvaluationFactory {
    @Resource
    private GradeEvaluationForDeathStatistics gradeEvaluationForDeathStatistics;
    @Resource
    private GradeEvaluationForInjuredStatistics gradeEvaluationForInjuredStatistics;
    @Resource
    private GradeEvaluationForMissingStatistics gradeEvaluationForMissingStatistics;
    @Resource
    private GradeEvaluationForBrickwoodStructure gradeEvaluationForBrickwoodStructure;
    @Resource
    private GradeEvaluationForCivilStructure gradeEvaluationForCivilStructure;
    @Resource
    private GradeEvaluationForFrameworkStructure gradeEvaluationForFrameworkStructure;
    @Resource
    private GradeEvaluationForMasonryStructure gradeEvaluationForMasonryStructure;
    @Resource
    private GradeEvaluationForOtherStructure gradeEvaluationForOtherStructure;
    private GradeEvaluation gradeEvaluation;
    public GradeEvaluation createGradeEvaluation(String categoryCode)
    {
        if(categoryCode.equals("111"))
        {
            gradeEvaluation = gradeEvaluationForDeathStatistics;
        }
        else if(categoryCode.equals("112"))
        {
            gradeEvaluation = gradeEvaluationForInjuredStatistics;
        }
        else if(categoryCode.equals("113"))
        {
            gradeEvaluation = gradeEvaluationForMissingStatistics;
        }
        else if(categoryCode.equals("221"))
        {
            gradeEvaluation = gradeEvaluationForCivilStructure;
        }
        else if(categoryCode.equals("222"))
        {
            gradeEvaluation = gradeEvaluationForBrickwoodStructure;
        }
        else if(categoryCode.equals("223"))
        {
            gradeEvaluation = gradeEvaluationForMasonryStructure;
        }
        else if(categoryCode.equals("224"))
        {
            gradeEvaluation = gradeEvaluationForFrameworkStructure;
        }
        else if(categoryCode.equals("225"))
        {
            gradeEvaluation = gradeEvaluationForOtherStructure;
        }
        return gradeEvaluation;
    }
}
