package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.DebrisRecord;
import com.earthquake.managementPlatform.entities.FrameworkStructure;
import com.earthquake.managementPlatform.entities.MasonryStructure;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FrameworkStructureMapper {

    @Insert("INSERT INTO `earthquake`.`frameworkstructure` (`ID`, `date`, `location`, `basically_intact_square`, `slight_damaged_square`, `moderate_damaged_square`, `serious_damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{slightDamagedSquare},#{moderateDamagedSquare},#{seriousDamagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(FrameworkStructure frameworkStructure);

    @Update("UPDATE `earthquake`.`frameworkstructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `slight_damaged_square`=#{slightDamagedSquare}, `moderate_damaged_square`=#{moderateDamagedSquare}, `serious_damaged_square`=#{seriousDamagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(FrameworkStructure frameworkStructure);

}
