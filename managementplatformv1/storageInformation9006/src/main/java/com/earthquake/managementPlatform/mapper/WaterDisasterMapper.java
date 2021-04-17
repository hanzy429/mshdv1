package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.TrafficDisaster;
import com.earthquake.managementPlatform.entities.WaterDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface WaterDisasterMapper {
    @Select("SELECT * FROM earthquake.waterdisaster;")
    @Results(id="waterDisasterMap", value={
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
    List<WaterDisaster> getAllWaterDisaster();

    @Select("select * from earthquake.waterdisaster order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "waterDisasterMap")
    List<WaterDisaster> getWaterDisasterByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.waterdisaster where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "waterDisasterMap")
    List<WaterDisaster> getRecentWaterDisaster(@Param("time") int time );

    @Select("select * from earthquake.waterdisaster where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "waterDisasterMap")
    List<WaterDisaster> getRecentWaterDisasterByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.waterdisaster WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "waterDisasterMap")
    public WaterDisaster getWaterDisasterById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`waterdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(WaterDisaster waterDisaster);

    @Update("UPDATE `earthquake`.`waterdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(WaterDisaster waterDisaster);

    @Delete("DELETE FROM `earthquake`.`waterdisaster` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.waterdisaster order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.waterdisaster order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "waterDisasterMap")
    WaterDisaster getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastwaterdisaster;")
    @Results(id="lifeLineStatisticsMap", value={
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<LifeLineStatistics> getWaterStatistics();

    @Select("select * from earthquake.waterdisaster where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "waterDisasterMap")
    public List<WaterDisaster> getCopyWaterDisaster(@Param("time") int time);
}
