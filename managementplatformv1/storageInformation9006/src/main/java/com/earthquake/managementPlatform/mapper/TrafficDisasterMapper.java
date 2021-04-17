package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.CommDisaster;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.SettlementRecord;
import com.earthquake.managementPlatform.entities.TrafficDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface TrafficDisasterMapper {
    @Select("SELECT * FROM earthquake.trafficdisaster;")
    @Results(id="trafficDisasterMap", value={
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
    List<TrafficDisaster> getAllTrafficDisaster();

    @Select("select * from earthquake.trafficdisaster order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "trafficDisasterMap")
    List<TrafficDisaster> getTrafficDisasterByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.trafficdisaster where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "trafficDisasterMap")
    List<TrafficDisaster> getRecentTrafficDisaster(@Param("time") int time );

    @Select("select * from earthquake.trafficdisaster where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "trafficDisasterMap")
    List<TrafficDisaster> getRecentTrafficDisasterByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.trafficdisaster WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "trafficDisasterMap")
    public TrafficDisaster getTrafficDisasterById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`trafficdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(TrafficDisaster trafficDisaster);

    @Update("UPDATE `earthquake`.`trafficdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(TrafficDisaster trafficDisaster);

    @Delete("DELETE FROM `earthquake`.`trafficdisaster` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.trafficdisaster order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.trafficdisaster order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "trafficDisasterMap")
    TrafficDisaster getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lasttrafficdisaster;")
    @Results(id="lifeLineStatisticsMap", value={
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<LifeLineStatistics> getTrafficStatistics();

    @Select("select * from earthquake.trafficdisaster where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "trafficDisasterMap")
    public List<TrafficDisaster> getCopyTrafficDisaster(@Param("time") int time);

}
