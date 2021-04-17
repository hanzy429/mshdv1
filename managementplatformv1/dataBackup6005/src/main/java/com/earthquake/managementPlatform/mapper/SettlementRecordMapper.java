package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.PowerDisaster;
import com.earthquake.managementPlatform.entities.SettlementRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SettlementRecordMapper {

    @Insert("INSERT INTO `earthquake`.`settlementrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(SettlementRecord settlementRecord);

    @Update("UPDATE `earthquake`.`settlementrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(SettlementRecord settlementRecord);

}
