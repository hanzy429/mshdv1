package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface DisasterPredictionMapper {
    @Select("SELECT * FROM earthquake.disasterprediction;")
    @Results(id="disasterPredictionMap", value={
            @Result(column="D_ID", property="D_ID", jdbcType= JdbcType.CHAR),
            @Result(column="S_ID", property="S_ID", jdbcType= JdbcType.VARCHAR),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="grade", property="grade", jdbcType= JdbcType.VARCHAR),
            @Result(column="intensity", property="intensity", jdbcType= JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType= JdbcType.VARCHAR),
            @Result(column="picture", property="picture", jdbcType= JdbcType.VARCHAR)
    })
    List<DisasterPrediction> getAllDisasterPrediction();

    @Select("select * from earthquake.disasterprediction order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "disasterPredictionMap")
    List<DisasterPrediction> getDisasterPredictionByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);

    @Select("select * from earthquake.disasterprediction where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "disasterPredictionMap")
    List<DisasterPrediction> getRecentDisasterPrediction(@Param("time") int time );

    @Select("select * from earthquake.disasterprediction where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "disasterPredictionMap")
    List<DisasterPrediction> getRecentDisasterPredictionByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.disasterprediction WHERE D_ID = #{D_ID} AND S_ID = #{S_ID} FOR UPDATE")
    @ResultMap(value = "disasterPredictionMap")
    public DisasterPrediction getDisasterPredictionById(@Param("D_ID")String D_ID,@Param("S_ID")String S_ID);

    @Insert("INSERT INTO `earthquake`.`disasterprediction` (`D_ID`, `S_ID`, `date`, `grade`, `intensity`, `type`, `picture`) VALUES (#{D_ID}, #{S_ID}, #{date}, #{grade}, #{intensity}, #{type}, #{picture});\n")
    int save(DisasterPrediction disasterPrediction);

    @Update("UPDATE `earthquake`.`disasterprediction` SET `date`=#{date}, `grade`=#{grade}, `intensity`=#{intensity}, `type`=#{type}, `picture`=#{picture} WHERE `D_ID`=#{D_ID} AND `S_ID`=#{S_ID}")
    int update(DisasterPrediction disasterPrediction);

    @Delete("DELETE FROM `earthquake`.`disasterprediction` WHERE D_ID = #{D_ID} AND S_ID = #{S_ID}")
    int deleteById(@Param("D_ID")String D_ID,@Param("S_ID")String S_ID);

    @Select("select * from earthquake.disasterprediction where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "disasterPredictionMap")
    public List<DisasterPrediction> getCopyDisasterPrediction(@Param("time") int time);

}
