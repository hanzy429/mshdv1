package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.OtherStructure;
import com.earthquake.managementPlatform.entities.PowerDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface PowerDisasterMapper {

    @Insert("INSERT INTO `earthquake`.`powerdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(PowerDisaster powerDisaster);

    @Update("UPDATE `earthquake`.`powerdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(PowerDisaster powerDisaster);

}
