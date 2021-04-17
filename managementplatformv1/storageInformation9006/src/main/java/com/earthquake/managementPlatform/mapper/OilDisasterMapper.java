package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.MissingStatistics;
import com.earthquake.managementPlatform.entities.OilDisaster;
import com.earthquake.managementPlatform.entities.WaterDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OilDisasterMapper {
    @Select("SELECT * FROM earthquake.oildisaster;")
    @Results(id="oilDisasterMap", value={
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
    List<OilDisaster> getAllOilDisaster();

    @Select("select * from earthquake.oildisaster order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "oilDisasterMap")
    List<OilDisaster> getOilDisasterByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.oildisaster where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "oilDisasterMap")
    List<OilDisaster> getRecentOilDisaster(@Param("time") int time );

    @Select("select * from earthquake.oildisaster where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "oilDisasterMap")
    List<OilDisaster> getRecentOilDisasterByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.oildisaster WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "oilDisasterMap")
    public OilDisaster getOilDisasterById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`oildisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(OilDisaster oilDisaster);

    @Update("UPDATE `earthquake`.`oildisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(OilDisaster oilDisaster);

    @Delete("DELETE FROM `earthquake`.`oildisaster` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.oildisaster order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.oildisaster order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "oilDisasterMap")
    OilDisaster getNewCodeDescription();

    @Select("SELECT *  FROM earthquake.lastoildisaster;")
    @Results(id="lifeLineStatisticsMap", value={
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<LifeLineStatistics> getOilStatistics();

    @Select("select * from earthquake.oildisaster where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "oilDisasterMap")
    public List<OilDisaster> getCopyOilDisaster(@Param("time") int time);
}
