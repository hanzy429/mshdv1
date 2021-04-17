package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.FrameworkStructure;
import com.earthquake.managementPlatform.entities.GasDisaster;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.OilDisaster;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface GasDisasterMapper {
    @Select("SELECT * FROM earthquake.gasdisaster;")
    @Results(id="gasDisasterMap", value={
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
    List<GasDisaster> getAllGasDisaster();

    @Select("select * from earthquake.gasdisaster order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "gasDisasterMap")
    List<GasDisaster> getGasDisasterByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.gasdisaster where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "gasDisasterMap")
    List<GasDisaster> getRecentGasDisaster(@Param("time") int time );

    @Select("select * from earthquake.gasdisaster where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "gasDisasterMap")
    List<GasDisaster> getRecentGasDisasterByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.gasdisaster WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "gasDisasterMap")
    public GasDisaster getGasDisasterById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`gasdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(GasDisaster gasDisaster);

    @Update("UPDATE `earthquake`.`gasdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(GasDisaster gasDisaster);

    @Delete("DELETE FROM `earthquake`.`gasdisaster` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.gasdisaster order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.gasdisaster order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "gasDisasterMap")
    GasDisaster getNewCodeDescription();

    @Select("SELECT * FROM lastgasdisaster;")
    @Results(id="lifeLineStatisticsMap", value={
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<LifeLineStatistics> getGasStatistics();

    @Select("select * from earthquake.gasdisaster where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "gasDisasterMap")
    public List<GasDisaster> getCopyGasDisaster(@Param("time") int time);
}
