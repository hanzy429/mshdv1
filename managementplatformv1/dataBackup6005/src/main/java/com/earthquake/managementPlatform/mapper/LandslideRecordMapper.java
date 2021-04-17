package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.KarstRecord;
import com.earthquake.managementPlatform.entities.LandslideRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface LandslideRecordMapper {

    @Insert("INSERT INTO `earthquake`.`landsliderecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(LandslideRecord landslideRecord);

    @Update("UPDATE `earthquake`.`landsliderecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(LandslideRecord landslideRecord);

}
