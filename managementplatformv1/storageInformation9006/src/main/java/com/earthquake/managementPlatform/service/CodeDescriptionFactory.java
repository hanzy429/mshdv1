package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class CodeDescriptionFactory {
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

    public Object createCodeDescription(String categoryId){

        Object object = new Object() ;

        if(categoryId.equals("111")){
            object = deathStatisticsMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("112")){
            object = injuredStatisticsMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("113")){
            object = missingStatisticsMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("221")){
            object = civilStructureMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("222")){
            object = brickwoodStructureMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("223")){
            object = masonryStructureMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("224")){
            object = frameworkStructureMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("225")){
            object = otherStructureMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("331")){
            object = trafficDisasterMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("332")){
            object = waterDisasterMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("333")){
            object = oilDisasterMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("334")){
            object = gasDisasterMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("335")){
            object = powerDisasterMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("336")){
            object = commDisasterMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("337")){
            object = irrigationDisasterMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("441")){
            object = collapseRecordMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("442")){
            object = landslideRecordMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("443")){
            object = debrisRecordMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("444")){
            object = karstRecordMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("445")){
            object = crackRecordMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("446")){
            object = settlementRecordMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("447")){
            object = otherRecordMapper.getNewCodeDescription();
        }
        else if(categoryId.equals("551")){
            object = basicEarthquakeInfoMapper.getNewCodeDescription();
        }
        return object;

    }
}
