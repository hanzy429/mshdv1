package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.OtherStructure;
import com.earthquake.managementPlatform.entities.PowerDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface PowerDisasterMapper {
    @Select("SELECT * FROM earthquake.powerdisaster;")
    @Results(id="powerDisasterMap", value={
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
    List<PowerDisaster> getAllPowerDisaster();

    @Select("select * from earthquake.powerdisaster order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "powerDisasterMap")
    List<PowerDisaster> getPowerDisasterByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.powerdisaster where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "powerDisasterMap")
    List<PowerDisaster> getRecentPowerDisaster(@Param("time") int time );

    @Select("select * from earthquake.powerdisaster where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "powerDisasterMap")
    List<PowerDisaster> getRecentPowerDisasterByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.powerdisaster WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "powerDisasterMap")
    public PowerDisaster getPowerDisasterById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`powerdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(PowerDisaster powerDisaster);

    @Update("UPDATE `earthquake`.`powerdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(PowerDisaster powerDisaster);

    @Delete("DELETE FROM `earthquake`.`powerdisaster` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.powerdisaster order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.powerdisaster order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "powerDisasterMap")
    PowerDisaster getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastpowerdisaster")
    @Results(id="lifeLineStatisticsMap", value={
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<LifeLineStatistics> getPowerStatistics();

    @Select("select * from earthquake.powerdisaster where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "powerDisasterMap")
    public List<PowerDisaster> getCopyPowerDisaster(@Param("time") int time);
}
