package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.InjuredStatistics;
import com.earthquake.managementPlatform.entities.MasonryStructure;
import com.earthquake.managementPlatform.entities.MissingStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface MissingStatisticsMapper {

    @Insert("INSERT INTO `earthquake`.`missingstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(MissingStatistics missingStatistics);

    @Update("UPDATE `earthquake`.`missingstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(MissingStatistics missingStatistics);

}
