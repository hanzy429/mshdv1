package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.CrackRecord;
import com.earthquake.managementPlatform.entities.DeathStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface DeathStatisticsMapper {

    @Insert("INSERT INTO `earthquake`.`deathstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(DeathStatistics deathStatistics);

    @Update("UPDATE `earthquake`.`deathstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(DeathStatistics deathStatistics);

}
