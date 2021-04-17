package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.CommDisaster;
import com.earthquake.managementPlatform.entities.CrackRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CrackRecordMapper {

    @Insert("INSERT INTO `earthquake`.`crackrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CrackRecord crackRecord);

    @Update("UPDATE `earthquake`.`crackrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CrackRecord crackRecord);

}
