package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.DeathStatistics;
import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.InjuredStatistics;
import com.earthquake.managementPlatform.entities.PersonStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface InjuredStatisticsMapper {
    @Select("SELECT * FROM earthquake.injuredstatistics;")
    @Results(id="injuredStatisticsMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<InjuredStatistics> getAllInjuredStatistics();

    @Select("select * from earthquake.injuredstatistics order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "injuredStatisticsMap")
    List<InjuredStatistics> getInjuredStatisticsByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.injuredstatistics where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "injuredStatisticsMap")
    List<InjuredStatistics> getRecentInjuredStatistics(@Param("time") int time );

    @Select("select * from earthquake.injuredstatistics where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "injuredStatisticsMap")
    List<InjuredStatistics> getRecentInjuredStatisticsByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.injuredstatistics WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "injuredStatisticsMap")
    public InjuredStatistics getInjuredStatisticsById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`injuredstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(InjuredStatistics injuredStatistics);

    @Update("UPDATE `earthquake`.`injuredstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(InjuredStatistics injuredStatistics);

    @Delete("DELETE FROM `earthquake`.`injuredstatistics` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.injuredstatistics order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.injuredstatistics order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "injuredStatisticsMap")
    InjuredStatistics getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastinjuredstatisticsbytime;")
    @Results(id="lastInjuredStatisticsByTime", value={
            @Result(column="date", property="date", jdbcType= JdbcType.CHAR),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER)
    })
    List<PersonStatistics> getLastInjuredStatisticsByTime();

    @Select("select * from earthquake.injuredstatistics where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "injuredStatisticsMap")
    public List<InjuredStatistics> getCopyInjuredStatistics(@Param("time") int time);
}
