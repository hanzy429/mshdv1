package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.IrrigationDisaster;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.entities.KarstRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface KarstRecordMapper {
    @Select("SELECT * FROM earthquake.karstrecord;")
    @Results(id="karstRecordMap", value={
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
    List<KarstRecord> getAllKarstRecord();

    @Select("select * from earthquake.karstrecord order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "karstRecordMap")
    List<KarstRecord> getKarstRecordByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.karstrecord where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "karstRecordMap")
    List<KarstRecord> getRecentKarstRecord(@Param("time") int time );

    @Select("select * from earthquake.karstrecord where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "karstRecordMap")
    List<KarstRecord> getRecentKarstRecordByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.karstrecord WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "karstRecordMap")
    public KarstRecord getKarstRecordById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`karstrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(KarstRecord karstRecord);

    @Update("UPDATE `earthquake`.`karstrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(KarstRecord karstRecord);

    @Delete("DELETE FROM `earthquake`.`karstrecord` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.karstrecord order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.karstrecord order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "karstRecordMap")
    KarstRecord getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastkarstrecord")
    @Results(id="secondaryDisasterStatisticsMap", value={
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<SecondaryDisasterStatistics> getKarstStatistics();

    @Select("select * from earthquake.karstrecord where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "karstRecordMap")
    public List<KarstRecord> getCopyKarstRecord(@Param("time") int time);
}
