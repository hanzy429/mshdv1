package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.MissingStatistics;
import com.earthquake.managementPlatform.entities.OilDisaster;
import com.earthquake.managementPlatform.entities.WaterDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OilDisasterMapper {

    @Insert("INSERT INTO `earthquake`.`oildisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(OilDisaster oilDisaster);

    @Update("UPDATE `earthquake`.`oildisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(OilDisaster oilDisaster);

}
