package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.mapper.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CodeFactory {
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;
    @Resource
    CivilStructureMapper civilStructureMapper;
    @Resource
    CrackRecordMapper crackRecordMapper;
    @Resource
    DeathStatisticsMapper deathStatisticsMapper;
    @Resource
    DebrisRecordMapper debrisRecordMapper;
    @Resource
    FrameworkStructureMapper frameworkStructureMapper;
    @Resource
    GasDisasterMapper gasDisasterMapper;
    @Resource
    InjuredStatisticsMapper injuredStatisticsMapper;
    @Resource
    IrrigationDisasterMapper irrigationDisasterMapper;
    @Resource
    KarstRecordMapper karstRecordMapper;
    @Resource
    LandslideRecordMapper landslideRecordMapper;
    @Resource
    MasonryStructureMapper masonryStructureMapper;
    @Resource
    MissingStatisticsMapper missingStatisticsMapper;
    @Resource
    OilDisasterMapper oilDisasterMapper;
    @Resource
    OtherStructureMapper otherStructureMapper;
    @Resource
    OtherRecordMapper otherRecordMapper;
    @Resource
    PowerDisasterMapper powerDisasterMapper;
    @Resource
    SettlementRecordMapper settlementRecordMapper;
    @Resource
    TrafficDisasterMapper trafficDisasterMapper;
    @Resource
    WaterDisasterMapper waterDisasterMapper;
    @Resource
    CommDisasterMapper commDisasterMapper;
    @Resource
    CollapseRecordMapper collapseRecordMapper;
    @Resource
    BasicEarthquakeInfoMapper basicEarthquakeInfoMapper;
    @Resource
    DisasterPredictionMapper disasterPredictionMapper;

    public String createCode(String categoryId){

        String code = "";

        if(categoryId.equals("111")){
            code = deathStatisticsMapper.getNewCode();
        }
        else if(categoryId.equals("112")){
            code = injuredStatisticsMapper.getNewCode();
        }
        else if(categoryId.equals("113")){
            code = missingStatisticsMapper.getNewCode();
        }
        else if(categoryId.equals("221")){
            code = civilStructureMapper.getNewCode();
        }
        else if(categoryId.equals("222")){
            code = brickwoodStructureMapper.getNewCode();
        }
        else if(categoryId.equals("223")){
            code = masonryStructureMapper.getNewCode();
        }
        else if(categoryId.equals("224")){
            code = frameworkStructureMapper.getNewCode();
        }
        else if(categoryId.equals("225")){
            code = otherStructureMapper.getNewCode();
        }
        else if(categoryId.equals("331")){
            code = trafficDisasterMapper.getNewCode();
        }
        else if(categoryId.equals("332")){
            code = waterDisasterMapper.getNewCode();
        }
        else if(categoryId.equals("333")){
            code = oilDisasterMapper.getNewCode();
        }
        else if(categoryId.equals("334")){
            code = gasDisasterMapper.getNewCode();
        }
        else if(categoryId.equals("335")){
            code = powerDisasterMapper.getNewCode();
        }
        else if(categoryId.equals("336")){
            code = commDisasterMapper.getNewCode();
        }
        else if(categoryId.equals("337")){
            code = irrigationDisasterMapper.getNewCode();
        }
        else if(categoryId.equals("441")){
            code = collapseRecordMapper.getNewCode();
        }
        else if(categoryId.equals("442")){
            code = landslideRecordMapper.getNewCode();
        }
        else if(categoryId.equals("443")){
            code = debrisRecordMapper.getNewCode();
        }
        else if(categoryId.equals("444")){
            code = karstRecordMapper.getNewCode();
        }
        else if(categoryId.equals("445")){
            code = crackRecordMapper.getNewCode();
        }
        else if(categoryId.equals("446")){
            code = settlementRecordMapper.getNewCode();
        }
        else if(categoryId.equals("447")){
            code = otherRecordMapper.getNewCode();
        }
        else if(categoryId.equals("551")){
            code = basicEarthquakeInfoMapper.getNewCode();
        }
        return code;

    }


}
