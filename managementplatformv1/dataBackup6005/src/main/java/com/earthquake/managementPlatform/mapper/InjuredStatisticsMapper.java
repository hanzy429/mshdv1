package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.InjuredStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface InjuredStatisticsMapper {

    @Insert("INSERT INTO `earthquake`.`injuredstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(InjuredStatistics injuredStatistics);

    @Update("UPDATE `earthquake`.`injuredstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(InjuredStatistics injuredStatistics);

}
