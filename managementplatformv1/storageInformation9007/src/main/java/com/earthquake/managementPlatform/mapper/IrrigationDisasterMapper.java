package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.InjuredStatistics;
import com.earthquake.managementPlatform.entities.IrrigationDisaster;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.PowerDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface IrrigationDisasterMapper {
    @Select("SELECT * FROM earthquake.irrigationdisaster;")
    @Results(id="irrigationDisasterMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType= JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="picture", property="picture", jdbcType= JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType= JdbcType.VARCHAR),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<IrrigationDisaster> getAllIrrigationDisaster();

    @Select("select * from earthquake.irrigationdisaster order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "irrigationDisasterMap")
    List<IrrigationDisaster> getIrrigationDisasterByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.irrigationdisaster where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "irrigationDisasterMap")
    List<IrrigationDisaster> getRecentIrrigationDisaster(@Param("time") int time );

    @Select("select * from earthquake.irrigationdisaster where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "irrigationDisasterMap")
    List<IrrigationDisaster> getRecentIrrigationDisasterByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.irrigationdisaster WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "irrigationDisasterMap")
    public IrrigationDisaster getIrrigationDisasterById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`irrigationdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(IrrigationDisaster IrrigationDisaster);

    @Update("UPDATE `earthquake`.`irrigationdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(IrrigationDisaster IrrigationDisaster);

    @Delete("DELETE FROM `earthquake`.`irrigationdisaster` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.irrigationdisaster order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.irrigationdisaster order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "irrigationDisasterMap")
    IrrigationDisaster getNewCodeDescription();

    @Select("SELECT *  FROM earthquake.lastirrigationdisaster")
    @Results(id="lifeLineStatisticsMap", value={
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<LifeLineStatistics> getIrrigationStatistics();

    @Select("select * from earthquake.irrigationdisaster where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "irrigationDisasterMap")
    public List<IrrigationDisaster> getCopyIrrigationDisaster(@Param("time") int time);
}
