package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.DebrisRecord;
import com.earthquake.managementPlatform.entities.DisasterPrediction;
import com.earthquake.managementPlatform.entities.FrameworkStructure;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface DisasterPredictionMapper {

    @Insert("INSERT INTO `earthquake`.`disasterprediction` (`D_ID`, `S_ID`, `date`, `grade`, `intensity`, `type`, `picture`) VALUES (#{D_ID}, #{S_ID}, #{date}, #{grade}, #{intensity}, #{type}, #{picture});\n")
    int save(DisasterPrediction disasterPrediction);

    @Update("UPDATE `earthquake`.`disasterprediction` SET `date`=#{date}, `grade`=#{grade}, `intensity`=#{intensity}, `type`=#{type}, `picture`=#{picture} WHERE `D_ID`=#{D_ID} AND `S_ID`=#{S_ID}")
    int update(DisasterPrediction disasterPrediction);


}
