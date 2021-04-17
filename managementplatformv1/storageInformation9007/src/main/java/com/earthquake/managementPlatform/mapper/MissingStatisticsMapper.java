package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.InjuredStatistics;
import com.earthquake.managementPlatform.entities.MasonryStructure;
import com.earthquake.managementPlatform.entities.MissingStatistics;
import com.earthquake.managementPlatform.entities.PersonStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface MissingStatisticsMapper {
    @Select("SELECT * FROM earthquake.missingstatistics;")
    @Results(id="missingStatisticsMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<MissingStatistics> getAllMissingStatistics();

    @Select("select * from earthquake.missingstatistics order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "missingStatisticsMap")
    List<MissingStatistics> getMissingStatisticsByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.missingstatistics where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "missingStatisticsMap")
    List<MissingStatistics> getRecentMissingStatistics(@Param("time") int time );

    @Select("select * from earthquake.missingstatistics where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "missingStatisticsMap")
    List<MissingStatistics> getRecentMissingStatisticsByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.missingstatistics WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "missingStatisticsMap")
    public MissingStatistics getMissingStatisticsById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`missingstatistics` (`ID`, `location`, `date`, `number`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{location},#{date},#{number},#{reportingUnit},#{earthquakeId})")
    int save(MissingStatistics missingStatistics);

    @Update("UPDATE `earthquake`.`missingstatistics` SET `date`=#{date}, `location`=#{location}, `number`=#{number},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(MissingStatistics missingStatistics);

    @Delete("DELETE FROM `earthquake`.`missingstatistics` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.missingstatistics order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.missingstatistics order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "missingStatisticsMap")
    MissingStatistics getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastmissingstatisticsbytime;")
    @Results(id="lastMissingStatisticsByTime", value={
            @Result(column="date", property="date", jdbcType= JdbcType.CHAR),
            @Result(column="number", property="number", jdbcType= JdbcType.INTEGER)
    })
    List<PersonStatistics> getLastMissingStatisticsByTime();

    @Select("select * from earthquake.missingstatistics where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "missingStatisticsMap")
    public List<MissingStatistics> getCopyMissingStatistics(@Param("time") int time);
}
