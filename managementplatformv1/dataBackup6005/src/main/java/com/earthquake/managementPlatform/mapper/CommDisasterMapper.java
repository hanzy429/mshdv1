package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.CollapseRecord;
import com.earthquake.managementPlatform.entities.CommDisaster;
import com.earthquake.managementPlatform.entities.OtherStructure;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CommDisasterMapper {

    @Insert("INSERT INTO `earthquake`.`commdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CommDisaster commDisaster);

    @Update("UPDATE `earthquake`.`commdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CommDisaster commDisaster);


}
