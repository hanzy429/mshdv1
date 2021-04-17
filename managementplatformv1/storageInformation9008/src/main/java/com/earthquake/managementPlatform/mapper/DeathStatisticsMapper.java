package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.CrackRecord;
import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.entities.PersonStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface DeathStatisticsMapper {
    @Select("SELECT * FROM earthquake.deathstatistics;")
    @Results(id="deathStatisticsMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<DeathStatistics> getAllDeathStatistics();

    @Select("select * from earthquake.deathstatistics order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "deathStatisticsMap")
    List<DeathStatistics> getDeathStatisticsByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);

    @Select("select * from earthquake.deathstatistics where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "deathStatisticsMap")
    List<DeathStatistics> getRecentDeathStatistics(@Param("time") int time );

    @Select("select * from earthquake.deathstatistics where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "deathStatisticsMap")
    List<DeathStatistics> getRecentDeathStatisticsByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.deathstatistics WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "deathStatisticsMap")
    public DeathStatistics getDeathStatisticsById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`deathstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(DeathStatistics deathStatistics);

    @Update("UPDATE `earthquake`.`deathstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(DeathStatistics deathStatistics);

    @Delete("DELETE FROM `earthquake`.`deathstatistics` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.deathstatistics order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.deathstatistics order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "deathStatisticsMap")
    DeathStatistics getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastdeathstatisticsbytime;")
    @Results(id="lastDeathStatisticsByTime", value={
            @Result(column="date", property="date", jdbcType= JdbcType.CHAR),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER)
    })
    List<PersonStatistics> getLastDeathStatisticsByTime();

    @Select("select * from earthquake.deathstatistics where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "deathStatisticsMap")
    public List<DeathStatistics> getCopyDeathStatistics(@Param("time") int time);
}
