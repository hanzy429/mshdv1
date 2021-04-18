package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.ObjectInputStream;
import java.util.List;


@Slf4j
@Service
public class BackupService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${storageInformation.url}")
    private String storageInformationUrl;

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

    @Transactional
    public boolean backUp(int time){
        try {

        List<DeathStatistics> deathStatisticses = restTemplate.exchange(storageInformationUrl+"/v1/deathStatisticsCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<DeathStatistics>>(){}).getBody();
        if(deathStatisticses.size() > 0) {
            for (DeathStatistics deathStatistics : deathStatisticses) {
                deathStatisticsMapper.save(deathStatistics);
                restTemplate.delete(storageInformationUrl + "/v1/deathStatistics/" + deathStatistics.getId());
            }
        }

            List<InjuredStatistics> injuredStatisticses = restTemplate.exchange(storageInformationUrl+"/v1/injuredStatisticsCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<InjuredStatistics>>(){}).getBody();
            if(injuredStatisticses.size() > 0) {
                for (InjuredStatistics injuredStatistics : injuredStatisticses) {
                    injuredStatisticsMapper.save(injuredStatistics);
                    restTemplate.delete(storageInformationUrl + "/v1/injuredStatistics/" + injuredStatistics.getId());
                }
            }

            List<MissingStatistics> missingStatisticses = restTemplate.exchange(storageInformationUrl+"/v1/missingStatisticsCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<MissingStatistics>>(){}).getBody();
            if(missingStatisticses.size() > 0) {
                for (MissingStatistics missingStatistics : missingStatisticses) {
                    missingStatisticsMapper.save(missingStatistics);
                    restTemplate.delete(storageInformationUrl + "/v1/missingStatistics/" + missingStatistics.getId());
                }
            }

            List<CivilStructure> civilStructures = restTemplate.exchange(storageInformationUrl+"/v1/civilStructureCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<CivilStructure>>(){}).getBody();
            if(civilStructures.size() > 0) {
                for (CivilStructure civilStructure : civilStructures) {
                    civilStructureMapper.save(civilStructure);
                    restTemplate.delete(storageInformationUrl + "/v1/civilStructure/" + civilStructure.getId());
                }
            }

            List<MasonryStructure> masonryStructures = restTemplate.exchange(storageInformationUrl+"/v1/masonryStructureCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<MasonryStructure>>(){}).getBody();
            if(masonryStructures.size() > 0) {
                for (MasonryStructure masonryStructure : masonryStructures) {
                    masonryStructureMapper.save(masonryStructure);
                    restTemplate.delete(storageInformationUrl + "/v1/masonryStructure/" + masonryStructure.getId());
                }
            }

            List<BrickwoodStructure> brickwoodStructures = restTemplate.exchange(storageInformationUrl+"/v1/brickwoodStructureCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<BrickwoodStructure>>(){}).getBody();
            if(brickwoodStructures.size() > 0) {
                for (BrickwoodStructure brickwoodStructure : brickwoodStructures) {
                    brickwoodStructureMapper.save(brickwoodStructure);
                    restTemplate.delete(storageInformationUrl + "/v1/brickwoodStructure/" + brickwoodStructure.getId());
                }
            }

            List<FrameworkStructure> frameworkStructures= restTemplate.exchange(storageInformationUrl+"/v1/frameworkStructureCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<FrameworkStructure>>(){}).getBody();
            if(frameworkStructures.size() > 0) {
                for (FrameworkStructure frameworkStructure : frameworkStructures) {
                    frameworkStructureMapper.save(frameworkStructure);
                    restTemplate.delete(storageInformationUrl + "/v1/frameworkStructure/" + frameworkStructure.getId());
                }
            }

            List<OtherStructure> otherStructures= restTemplate.exchange(storageInformationUrl+"/v1/otherStructureCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<OtherStructure>>(){}).getBody();
            if(otherStructures.size() > 0) {
                for (OtherStructure otherStructure : otherStructures) {
                    otherStructureMapper.save(otherStructure);
                    restTemplate.delete(storageInformationUrl + "/v1/otherStructure/" + otherStructure.getId());
                }
            }

            List<TrafficDisaster> trafficDisasters= restTemplate.exchange(storageInformationUrl+"/v1/trafficDisasterCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<TrafficDisaster>>(){}).getBody();
            if(trafficDisasters.size() > 0) {
                for (TrafficDisaster trafficDisaster : trafficDisasters) {
                    trafficDisasterMapper.save(trafficDisaster);
                    restTemplate.delete(storageInformationUrl + "/v1/trafficDisaster/" + trafficDisaster.getId());
                }
            }

            List<WaterDisaster> waterDisasters= restTemplate.exchange(storageInformationUrl+"/v1/waterDisasterCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<WaterDisaster>>(){}).getBody();
            if(waterDisasters.size() > 0) {
                for (WaterDisaster waterDisaster : waterDisasters) {
                    waterDisasterMapper.save(waterDisaster);
                    restTemplate.delete(storageInformationUrl + "/v1/waterDisaster/" + waterDisaster.getId());
                }
            }

            List<OilDisaster> oilDisasters= restTemplate.exchange(storageInformationUrl+"/v1/oilDisasterCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<OilDisaster>>(){}).getBody();
            if(oilDisasters.size() > 0) {
                for (OilDisaster oilDisaster : oilDisasters) {
                    oilDisasterMapper.save(oilDisaster);
                    restTemplate.delete(storageInformationUrl + "/v1/oilDisaster/" + oilDisaster.getId());
                }
            }

            List<GasDisaster> gasDisasters= restTemplate.exchange(storageInformationUrl+"/v1/gasDisasterCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<GasDisaster>>(){}).getBody();
            if(gasDisasters.size() > 0) {
                for (GasDisaster gasDisaster : gasDisasters) {
                    gasDisasterMapper.save(gasDisaster);
                    restTemplate.delete(storageInformationUrl + "/v1/gasDisaster/" + gasDisaster.getId());
                }
            }

            List<PowerDisaster> powerDisasters= restTemplate.exchange(storageInformationUrl+"/v1/powerDisasterCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<PowerDisaster>>(){}).getBody();
            if(powerDisasters.size() > 0) {
                for (PowerDisaster powerDisaster : powerDisasters) {
                    powerDisasterMapper.save(powerDisaster);
                    restTemplate.delete(storageInformationUrl + "/v1/powerDisaster/" + powerDisaster.getId());
                }
            }

            List<CommDisaster> commDisasters= restTemplate.exchange(storageInformationUrl+"/v1/commDisasterCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<CommDisaster>>(){}).getBody();
            if(commDisasters.size() > 0) {
                for (CommDisaster commDisaster : commDisasters) {
                    commDisasterMapper.save(commDisaster);
                    restTemplate.delete(storageInformationUrl + "/v1/commDisaster/" + commDisaster.getId());
                }
            }

            List<IrrigationDisaster> irrigationDisasters= restTemplate.exchange(storageInformationUrl+"/v1/irrigationDisasterCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<IrrigationDisaster>>(){}).getBody();
            if(irrigationDisasters.size() > 0) {
                for (IrrigationDisaster irrigationDisaster : irrigationDisasters) {
                    irrigationDisasterMapper.save(irrigationDisaster);
                    restTemplate.delete(storageInformationUrl + "/v1/irrigationDisaster/" + irrigationDisaster.getId());
                }
            }

            List<CollapseRecord> collapseRecords= restTemplate.exchange(storageInformationUrl+"/v1/collapseRecordCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<CollapseRecord>>(){}).getBody();
            if(collapseRecords.size() > 0) {
                for (CollapseRecord collapseRecord : collapseRecords) {
                    collapseRecordMapper.save(collapseRecord);
                    restTemplate.delete(storageInformationUrl + "/v1/collapseRecord/" + collapseRecord.getId());
                }
            }

            List<LandslideRecord> landslideRecords = restTemplate.exchange(storageInformationUrl+"/v1/landslideRecordCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<LandslideRecord>>(){}).getBody();
            if(landslideRecords.size() > 0) {
                for (LandslideRecord landslideRecord : landslideRecords) {
                    landslideRecordMapper.save(landslideRecord);
                    restTemplate.delete(storageInformationUrl + "/v1/landslideRecord/" + landslideRecord.getId());
                }
            }

            List<DebrisRecord> debrisRecords = restTemplate.exchange(storageInformationUrl+"/v1/debrisRecordCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<DebrisRecord>>(){}).getBody();
            if(debrisRecords.size() > 0) {
                for (DebrisRecord debrisRecord : debrisRecords) {
                    debrisRecordMapper.save(debrisRecord);
                    restTemplate.delete(storageInformationUrl + "/v1/debrisRecord/" + debrisRecord.getId());
                }
            }

            List<KarstRecord> karstRecords = restTemplate.exchange(storageInformationUrl+"/v1/karstRecordCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<KarstRecord>>(){}).getBody();
            if(karstRecords.size() > 0) {
                for (KarstRecord karstRecord : karstRecords) {
                    karstRecordMapper.save(karstRecord);
                    restTemplate.delete(storageInformationUrl + "/v1/karstRecord/" + karstRecord.getId());
                }
            }

            List<CrackRecord> crackRecords = restTemplate.exchange(storageInformationUrl+"/v1/crackRecordCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<CrackRecord>>(){}).getBody();
            if(crackRecords.size() > 0) {
                for (CrackRecord crackRecord : crackRecords) {
                    crackRecordMapper.save(crackRecord);
                    restTemplate.delete(storageInformationUrl + "/v1/crackRecord/" + crackRecord.getId());
                }
            }

            List<SettlementRecord> settlementRecords = restTemplate.exchange(storageInformationUrl+"/v1/settlementRecordCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<SettlementRecord>>(){}).getBody();
            if(settlementRecords.size() > 0) {
                for (SettlementRecord settlementRecord : settlementRecords) {
                    settlementRecordMapper.save(settlementRecord);
                    restTemplate.delete(storageInformationUrl + "/v1/settlementRecord/" + settlementRecord.getId());
                }
            }

            List<OtherRecord> otherRecords = restTemplate.exchange(storageInformationUrl+"/v1/otherRecordCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<OtherRecord>>(){}).getBody();
            if(otherRecords.size() > 0) {
                for (OtherRecord otherRecord : otherRecords) {
                    otherRecordMapper.save(otherRecord);
                    restTemplate.delete(storageInformationUrl + "/v1/otherRecord/" + otherRecord.getId());
                }
            }

            List<DisasterPrediction> disasterPredictions = restTemplate.exchange(storageInformationUrl+"/v1/disasterPredictionCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<DisasterPrediction>>(){}).getBody();
            if(disasterPredictions.size() > 0) {
                for (DisasterPrediction disasterPrediction : disasterPredictions) {
                    disasterPredictionMapper.save(disasterPrediction);
                    restTemplate.delete(storageInformationUrl + "/v1/disasterPrediction/" + disasterPrediction.getD_ID()+'/'+disasterPrediction.getS_ID());
                }
            }

            List<BasicEarthquakeInfo> basicEarthquakeInfos = restTemplate.exchange(storageInformationUrl+"/v1/disasterInfoCopy/"+time,HttpMethod.GET, null, new ParameterizedTypeReference<List<BasicEarthquakeInfo>>(){}).getBody();
            if(basicEarthquakeInfos.size() > 0) {
                for (BasicEarthquakeInfo basicEarthquakeInfo : basicEarthquakeInfos) {
                    basicEarthquakeInfoMapper.save(basicEarthquakeInfo);
                    restTemplate.delete(storageInformationUrl + "/v1/disasterInfo/" + basicEarthquakeInfo.getId());
                }
            }

        return true;
        }
        catch (Exception e){
            return false;
        }


    }

}
