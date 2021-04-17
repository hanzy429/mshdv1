package com.earthquake.managementPlatform.mapper;


import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BasicEarthquakeInfoMapper{

    @Insert("INSERT INTO `earthquake`.`disasterinfo` (`D_ID`, `date`, `location`, `longitude`, `latitude`, `depth`, `magnitude`, `picture`, `reporting_unit`) VALUES (#{id},#{date},#{location},#{longitude},#{latitude},#{depth},#{magnitude},#{picture},#{reportingUnit})")
    int save(BasicEarthquakeInfo basicEarthquakeInfo);

    @Update("UPDATE `earthquake`.`disasterinfo` SET `D_ID`= #{id}, `date`=#{date}, `location`=#{location}, `longitude`=#{longitude},`latitude`=#{latitude},`depth`=#{depth},`magnitude`=#{magnitude},`picture`=#{picture},`reporting_unit`=#{reportingUnit} WHERE `D_ID`=#{id}")
    int update(BasicEarthquakeInfo basicEarthquakeInfo);


}
