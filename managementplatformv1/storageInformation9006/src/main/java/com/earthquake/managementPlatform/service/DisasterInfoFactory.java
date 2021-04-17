package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.MissingStatistics;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DisasterInfoFactory {
    @Resource
    BasicEarthquakeInformationStorage basicEarthquakeInformationStorage;
    @Resource
    DeathStatisticsStorage deathStatisticsStorage;
    @Resource
    InjuredStatisticsStorage injuredStatisticsStorage;
    @Resource
    MissingStatisticsStorage missingStatisticsStorage;
    @Resource
    CivilStructureStorage civilStructureStorage;
    @Resource
    BrickwoodStructureStorage brickwoodStructureStorage;
    @Resource
    MasonryStructureStorage masonryStructureStorage;
    @Resource
    FrameworkStructureStorage frameworkStructureStorage;
    @Resource
    OtherStructureStorage otherStructureStorage;
    @Resource
    CommDisasterStorage commDisasterStorage;
    @Resource
    TrafficDisasterStorage trafficDisasterStorage;
    @Resource
    WaterDisasterStorage waterDisasterStorage;
    @Resource
    OilDisasterStorage oilDisasterStorage;
    @Resource
    GasDisasterStorage gasDisasterStorage;
    @Resource
    PowerDisasterStorage powerDisasterStorage;
    @Resource
    IrrigationDisasterStorage irrigationDisasterStorage;
    @Resource
    CollapseRecordStorage collapseRecordStorage;
    @Resource
    LandslideRecordStorage landslideRecordStorage;
    @Resource
    DebrisRecordStorage debrisRecordStorage;
    @Resource
    KarstRecordStorage karstRecordStorage;
    @Resource
    CrackRecordStorage crackRecordStorage;
    @Resource
    SettlementRecordStorage settlementRecordStorage;
    @Resource
    OtherRecordStorage otherRecordStorage;
    @Resource
    DisasterPredictionStorage disasterPredictionStorage;

    public DisasterInformationStorage createDisasterInformationStorage(String type){

        DisasterInformationStorage disasterInformationStorage = null;

        if(type.length() == 26)
        {
            disasterInformationStorage = basicEarthquakeInformationStorage;
        }

        else if(type.length() == 3 && (type.equals("202")||type.equals("203")||type.equals("301")))
        {
            disasterInformationStorage = disasterPredictionStorage;
        }

        else if(type.substring(12, 15).equals("551")){
            disasterInformationStorage = basicEarthquakeInformationStorage;
        }
        else if(type.substring(12, 15).equals("111")){
            disasterInformationStorage = deathStatisticsStorage;
        }
        else if(type.substring(12, 15).equals("112")){
            disasterInformationStorage = injuredStatisticsStorage;
        }
        else if(type.substring(12, 15).equals("113")){
            disasterInformationStorage = missingStatisticsStorage;
        }
        else if(type.substring(12, 15).equals("221")){
            disasterInformationStorage = civilStructureStorage;
        }
        else if(type.substring(12, 15).equals("222")){
            disasterInformationStorage = brickwoodStructureStorage;
        }
        else if(type.substring(12, 15).equals("223")){
            disasterInformationStorage = masonryStructureStorage;
        }
        else if(type.substring(12, 15).equals("224")){
            disasterInformationStorage = frameworkStructureStorage;
        }
        else if(type.substring(12, 15).equals("225")){
            disasterInformationStorage = otherStructureStorage;
        }
        else if(type.substring(12, 15).equals("331")){
            disasterInformationStorage = trafficDisasterStorage;
        }
        else if(type.substring(12, 15).equals("332")){
            disasterInformationStorage = waterDisasterStorage;
        }
        else if(type.substring(12, 15).equals("333")){
            disasterInformationStorage = oilDisasterStorage;
        }
        else if(type.substring(12, 15).equals("334")){
            disasterInformationStorage = gasDisasterStorage;
        }
        else if(type.substring(12, 15).equals("335")){
            disasterInformationStorage = powerDisasterStorage;
        }
        else if(type.substring(12, 15).equals("336")){
            disasterInformationStorage = commDisasterStorage;
        }
        else if(type.substring(12, 15).equals("337")){
            disasterInformationStorage = irrigationDisasterStorage;
        }
        else if(type.substring(12, 15).equals("441")){
            disasterInformationStorage = collapseRecordStorage;
        }
        else if(type.substring(12, 15).equals("442")){
            disasterInformationStorage = landslideRecordStorage;
        }
        else if(type.substring(12, 15).equals("443")){
            disasterInformationStorage = debrisRecordStorage;
        }
        else if(type.substring(12, 15).equals("444")){
            disasterInformationStorage = karstRecordStorage;
        }
        else if(type.substring(12, 15).equals("445")){
            disasterInformationStorage = crackRecordStorage;
        }
        else if(type.substring(12, 15).equals("446")){
            disasterInformationStorage = settlementRecordStorage;
        }
        else if(type.substring(12, 15).equals("447")){
            disasterInformationStorage = otherRecordStorage;
        }
        return disasterInformationStorage;
    }
}
