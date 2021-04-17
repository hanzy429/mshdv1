package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.IrrigationDisaster;
import com.earthquake.managementPlatform.entities.KarstRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface KarstRecordMapper {

    @Insert("INSERT INTO `earthquake`.`karstrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(KarstRecord karstRecord);

    @Update("UPDATE `earthquake`.`karstrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(KarstRecord karstRecord);

}
