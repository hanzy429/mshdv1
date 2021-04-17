package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.OilDisaster;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.entities.OtherRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OtherRecordMapper {
    @Select("SELECT * FROM earthquake.otherrecord;")
    @Results(id="otherRecordMap", value={
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
    List<OtherRecord> getAllOtherRecord();

    @Select("select * from earthquake.otherrecord order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "otherRecordMap")
    List<OtherRecord> getOtherRecordByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.otherrecord where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "otherRecordMap")
    List<OtherRecord> getRecentOtherRecord(@Param("time") int time );

    @Select("select * from earthquake.otherrecord where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "otherRecordMap")
    List<OtherRecord> getRecentOtherRecordByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.otherrecord WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "otherRecordMap")
    public OtherRecord getOtherRecordById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`otherrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(OtherRecord otherRecord);

    @Update("UPDATE `earthquake`.`otherrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(OtherRecord otherRecord);

    @Delete("DELETE FROM `earthquake`.`otherrecord` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.otherrecord order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.otherrecord order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "otherRecordMap")
    OtherRecord getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastotherrecord")
    @Results(id="secondaryDisasterStatisticsMap", value={
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<SecondaryDisasterStatistics> getOtherStatistics();

    @Select("select * from earthquake.otherrecord where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "otherRecordMap")
    public List<OtherRecord> getCopyOtherRecord(@Param("time") int time);
}
