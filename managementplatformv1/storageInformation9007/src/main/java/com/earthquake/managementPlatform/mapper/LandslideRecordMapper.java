package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.KarstRecord;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.entities.LandslideRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface LandslideRecordMapper {
    @Select("SELECT * FROM earthquake.landsliderecord;")
    @Results(id="landslideRecordMap", value={
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
    List<LandslideRecord> getAllLandslideRecord();

    @Select("select * from earthquake.landsliderecord order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "landslideRecordMap")
    List<LandslideRecord> getLandslideRecordByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.landsliderecord where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "landslideRecordMap")
    List<LandslideRecord> getRecentLandslideRecord(@Param("time") int time );

    @Select("select * from earthquake.landsliderecord where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "landslideRecordMap")
    List<LandslideRecord> getRecentLandslideRecordByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.landsliderecord WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "landslideRecordMap")
    public LandslideRecord getLandslideRecordById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`landsliderecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(LandslideRecord landslideRecord);

    @Update("UPDATE `earthquake`.`landsliderecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(LandslideRecord landslideRecord);

    @Delete("DELETE FROM `earthquake`.`landsliderecord` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.landsliderecord order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.landsliderecord order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "landslideRecordMap")
    LandslideRecord getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastlandsliderecord")
    @Results(id="secondaryDisasterStatisticsMap", value={
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<SecondaryDisasterStatistics> getLandslideStatistics();

    @Select("select * from earthquake.landsliderecord where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "landslideRecordMap")
    public List<LandslideRecord> getCopyLandslideRecord(@Param("time") int time);
}
