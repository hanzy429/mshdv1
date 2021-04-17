package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.entities.DebrisRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface DebrisRecordMapper {
    @Select("SELECT * FROM earthquake.debrisrecord;")
    @Results(id="debrisRecordMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="picture", property="picture", jdbcType= JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType= JdbcType.VARCHAR),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<DebrisRecord> getAllDebrisRecord();

    @Select("select * from earthquake.debrisrecord order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "debrisRecordMap")
    List<DebrisRecord> getDebrisRecordByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.debrisrecord where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "debrisRecordMap")
    List<DebrisRecord> getRecentDebrisRecord(@Param("time") int time );

    @Select("select * from earthquake.debrisrecord where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "debrisRecordMap")
    List<DebrisRecord> getRecentDebrisRecordByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.debrisrecord WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "debrisRecordMap")
    public DebrisRecord getDebrisRecordById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`debrisrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(DebrisRecord debrisRecord);

    @Update("UPDATE `earthquake`.`debrisrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(DebrisRecord debrisRecord);

    @Delete("DELETE FROM `earthquake`.`debrisrecord` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.debrisrecord order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.debrisrecord order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "debrisRecordMap")
    DebrisRecord getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastdebrisrecord")
    @Results(id="secondaryDisasterStatisticsMap", value={
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<SecondaryDisasterStatistics> getDebrisStatistics();

    @Select("select * from earthquake.debrisrecord where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "debrisRecordMap")
    public List<DebrisRecord> getCopyDebrisRecord(@Param("time") int time);
}
