package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.CollapseRecord;
import com.earthquake.managementPlatform.entities.CommDisaster;
import com.earthquake.managementPlatform.entities.LifeLineStatistics;
import com.earthquake.managementPlatform.entities.OtherStructure;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CommDisasterMapper {
    @Select("SELECT * FROM earthquake.commdisaster;")
    @Results(id="commDisasterMap", value={
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
    List<CommDisaster> getAllCommDisaster();

    @Select("select * from earthquake.commdisaster order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "commDisasterMap")
    List<CommDisaster> getCommDisasterByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.commdisaster where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "commDisasterMap")
    List<CommDisaster> getRecentCommDisaster(@Param("time") int time );

    @Select("select * from earthquake.commdisaster where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "commDisasterMap")
    List<CommDisaster> getRecentCommDisasterByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.commdisaster WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "commDisasterMap")
    public CommDisaster getCommDisasterById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`commdisaster` (`ID`, `date`, `location`, `type`, `grade`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{grade},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CommDisaster commDisaster);

    @Update("UPDATE `earthquake`.`commdisaster` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `grade`=#{grade}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CommDisaster commDisaster);

    @Delete("DELETE FROM `earthquake`.`commdisaster` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.commdisaster order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.commdisaster order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "commDisasterMap")
    CommDisaster getNewCodeDescription();

    @Select("SELECT *  FROM earthquake.lastcommdisasterstatistics;")
    @Results(id="lifeLineStatisticsMap", value={
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<LifeLineStatistics> getCommDisasterStatistics();

    @Select("select * from earthquake.commdisaster where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "commDisasterMap")
    public List<CommDisaster> getCopyCommDisaster(@Param("time") int time);


}
