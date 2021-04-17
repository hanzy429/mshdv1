package com.earthquake.managementPlatform.mapper;


import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.BrickwoodStructure;
import com.earthquake.managementPlatform.entities.ProvEarthquakeFrequency;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BasicEarthquakeInfoMapper{

    @Select("SELECT * FROM earthquake.disasterinfo;")
    @Results(id="disasterInfoMap", value={
            @Result(column="D_ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="longitude", property="longitude", jdbcType= JdbcType.FLOAT),
            @Result(column="latitude", property="latitude", jdbcType= JdbcType.FLOAT),
            @Result(column="depth", property="depth", jdbcType= JdbcType.FLOAT),
            @Result(column="magnitude", property="magnitude", jdbcType= JdbcType.FLOAT),
            @Result(column="picture", property="picture", jdbcType= JdbcType.VARCHAR),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR)
    })
    List<BasicEarthquakeInfo> getAllDisasterInfo();

    @Select("select * from earthquake.disasterinfo order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "disasterInfoMap")
    List<BasicEarthquakeInfo> getDisasterInfoByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);

    @Select("select * from earthquake.disasterinfo where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "disasterInfoMap")
    List<BasicEarthquakeInfo> getRecentDisasterInfo(@Param("time") int time );

    @Select("select * from earthquake.disasterinfo where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "disasterInfoMap")
    List<BasicEarthquakeInfo> getRecentDisasterInfoByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.disasterinfo WHERE D_ID = #{id} FOR UPDATE")
    @ResultMap(value = "disasterInfoMap")
    public BasicEarthquakeInfo getDisasterInfoById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`disasterinfo` (`D_ID`, `date`, `location`, `longitude`, `latitude`, `depth`, `magnitude`, `picture`, `reporting_unit`) VALUES (#{id},#{date},#{location},#{longitude},#{latitude},#{depth},#{magnitude},#{picture},#{reportingUnit})")
    int save(BasicEarthquakeInfo basicEarthquakeInfo);

    @Update("UPDATE `earthquake`.`disasterinfo` SET `D_ID`= #{id}, `date`=#{date}, `location`=#{location}, `longitude`=#{longitude},`latitude`=#{latitude},`depth`=#{depth},`magnitude`=#{magnitude},`picture`=#{picture},`reporting_unit`=#{reportingUnit} WHERE `D_ID`=#{id}")
    int update(BasicEarthquakeInfo basicEarthquakeInfo);

    @Delete("DELETE FROM `earthquake`.`disasterinfo` WHERE D_ID = #{id}")
    int deleteById(String id);

    @Select("SELECT D_ID FROM earthquake.disasterinfo order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.disasterinfo order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "disasterInfoMap")
    BasicEarthquakeInfo getNewCodeDescription();

    @Select("SELECT * FROM earthquake.provinceearthquakefrequency;")
    @Results(id="provEarthquakeFrequency", value={
            @Result(column="c", property="proCode", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<ProvEarthquakeFrequency> getProvEarthquakeFrequency();

    @Select("SELECT * FROM earthquake.disasterinfo order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "disasterInfoMap")
    public BasicEarthquakeInfo getLastDisasterInfo();

    @Select("select * from earthquake.disasterinfo where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "disasterInfoMap")
    public List<BasicEarthquakeInfo> getCopyDisasterInfo(@Param("time") int time);

}
