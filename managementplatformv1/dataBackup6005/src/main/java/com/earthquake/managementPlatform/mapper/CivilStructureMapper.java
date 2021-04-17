package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CivilStructureMapper {

    @Insert("INSERT INTO `earthquake`.`civilstructure` (`ID`, `date`, `location`, `basically_intact_square`, `damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{damagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CivilStructure civilStructure);

    @Update("UPDATE `earthquake`.`civilstructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `damaged_square`=#{damagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CivilStructure civilStructure);

}
