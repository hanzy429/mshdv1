package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.TrafficDisaster;
import com.earthquake.managementPlatform.entities.WaterDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface WaterDisasterMapper {

    @Insert("INSERT INTO `earthquake`.`waterdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(WaterDisaster waterDisaster);

    @Update("UPDATE `earthquake`.`waterdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(WaterDisaster waterDisaster);

}
