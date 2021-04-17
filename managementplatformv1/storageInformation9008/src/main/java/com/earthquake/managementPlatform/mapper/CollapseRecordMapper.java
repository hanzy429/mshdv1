package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.CivilStructure;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.entities.CollapseRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CollapseRecordMapper {
    @Select("SELECT * FROM earthquake.collapserecord;")
    @Results(id="collapseRecordMap", value={
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
    List<CollapseRecord> getAllCollapseRecord();

    @Select("select * from earthquake.collapserecord order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "collapseRecordMap")
    List<CollapseRecord> getCollapseRecordByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.collapserecord where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "collapseRecordMap")
    List<CollapseRecord> getRecentCollapseRecord(@Param("time") int time );

    @Select("select * from earthquake.collapserecord where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "collapseRecordMap")
    List<CollapseRecord> getRecentCollapseRecordByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.collapserecord WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "collapseRecordMap")
    public CollapseRecord getCollapseRecordById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`collapserecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CollapseRecord collapseRecord);

    @Update("UPDATE `earthquake`.`collapserecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CollapseRecord collapseRecord);

    @Delete("DELETE FROM `earthquake`.`collapserecord` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.collapserecord order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.collapserecord order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "collapseRecordMap")
    CollapseRecord getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastcollapserecord")
    @Results(id="secondaryDisasterStatisticsMap", value={
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<SecondaryDisasterStatistics> getCollapseStatistics();

    @Select("select * from earthquake.collapserecord where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "collapseRecordMap")
    public List<CollapseRecord> getCopyCollapseRecord(@Param("time") int time);
}
