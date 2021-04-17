package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.OilDisaster;
import com.earthquake.managementPlatform.entities.OtherRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OtherRecordMapper {

    @Insert("INSERT INTO `earthquake`.`otherrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(OtherRecord otherRecord);

    @Update("UPDATE `earthquake`.`otherrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(OtherRecord otherRecord);

}
