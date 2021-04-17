package com.earthquake.managementPlatform.entities;

import com.earthquake.managementPlatform.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Slf4j
@Repository
public class SequenceCode {
    @Resource
    DistributionCodeMapper distributionCodeMapper;
    @Resource
    DisasterInfoMapper disasterInfoMapper;
    @Resource
    BrickwoodStructureMapper brickwoodStructureMapper;
    @Resource
    CivilStructureMapper civilStructureMapper;
    @Resource
    CollapseRecordMapper collapseRecordMapper;
    @Resource
    CommDisasterMapper commDisasterMapper;
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
    OtherRecordMapper otherRecordMapper;
    @Resource
    OtherStructureMapper otherStructureMapper;
    @Resource
    PowerDisasterMapper powerDisasterMapper;
    @Resource
    SettlementRecordMapper settlementRecordMapper;
    @Resource
    TrafficDisasterMapper trafficDisasterMapper;
    @Resource
    WaterDisasterMapper waterDisasterMapper;

    public String CodeForSequence(String administrativeRegionCode,String categoryCode){
        String lastCode = "";
        log.info(administrativeRegionCode+"..."+categoryCode);
        DistributionCode distributionCode = distributionCodeMapper.getDistributionCodeById(administrativeRegionCode+categoryCode);
        if(distributionCode !=null){
            distributionCode.setNumber(distributionCode.getNumber()+1);
            distributionCodeMapper.update(distributionCode);
        }
        else {
            if (categoryCode.equals("111")) {
                lastCode = deathStatisticsMapper.getSomeDeathStatisticsByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("112")) {
                lastCode = injuredStatisticsMapper.getSomeInjuredStatisticsByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("113")) {
                lastCode = missingStatisticsMapper.getSomeMissingStatisticsByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("221")) {
                lastCode = civilStructureMapper.getSomeCivilStructureByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("222")) {
                lastCode = brickwoodStructureMapper.getSomeBrickwoodStructureByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("223")) {
                lastCode = masonryStructureMapper.getSomeMasonryStructureByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("224")) {
                lastCode = frameworkStructureMapper.getSomeFrameworkStructureByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("225")) {
                lastCode = otherStructureMapper.getSomeOtherStructureByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("331")) {
                lastCode = trafficDisasterMapper.getSomeTrafficDisasterByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("332")) {
                lastCode = waterDisasterMapper.getSomeWaterDisasterByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("333")) {
                lastCode = oilDisasterMapper.getSomeOilDisasterByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("334")) {
                lastCode = gasDisasterMapper.getSomeGasDisasterByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("335")) {
                lastCode = powerDisasterMapper.getSomePowerDisasterByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("336")) {
                lastCode = commDisasterMapper.getSomeCommDisasterByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("337")) {
                lastCode = irrigationDisasterMapper.getSomeIrrigationDisasterByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("441")) {
                lastCode = collapseRecordMapper.getSomeCollapseRecordByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("442")) {
                lastCode = landslideRecordMapper.getSomeLandslideRecordByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("443")) {
                lastCode = debrisRecordMapper.getSomeDebrisRecordByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("444")) {
                lastCode = karstRecordMapper.getSomeKarstRecordByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("445")) {
                lastCode = crackRecordMapper.getSomeCrackRecordByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("446")) {
                lastCode = settlementRecordMapper.getSomeSettlementRecordByACId(administrativeRegionCode + categoryCode);
            } else if (categoryCode.equals("447")) {
                lastCode = otherRecordMapper.getSomeOtherRecordByACId(administrativeRegionCode + categoryCode);
            }

            if (lastCode != null && lastCode != "") {
                int codeCount = Integer.valueOf(lastCode.substring(15, 18)) + 1;
                distributionCode = new DistributionCode(administrativeRegionCode + categoryCode, codeCount);
                distributionCodeMapper.save(distributionCode);
            } else {
                distributionCode = new DistributionCode(administrativeRegionCode + categoryCode, 1);
                distributionCodeMapper.save(distributionCode);
            }
        }

        String sequenceCode = String.format("%3d",distributionCode.getNumber()).replace(" ","0");
        return sequenceCode;
    }
}
