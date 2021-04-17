package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.FrameworkStructure;
import com.earthquake.managementPlatform.entities.OtherRecord;
import com.earthquake.managementPlatform.entities.OtherStructure;
import com.earthquake.managementPlatform.entities.SquareStatistics;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface OtherStructureMapper {
    @Select("SELECT * FROM earthquake.otherstructure;")
    @Results(id="otherStructureMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="basically_intact_square", property="basicallyIntactSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="slight_damaged_square", property="slightDamagedSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="moderate_damaged_square", property="moderateDamagedSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="serious_damaged_square", property="seriousDamagedSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="destroyed_square", property="destroyedSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="note", property="note", jdbcType= JdbcType.VARCHAR),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<OtherStructure> getAllOtherStructure();

    @Select("select * from earthquake.otherstructure order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "otherStructureMap")
    List<OtherStructure> getOtherStructureByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.otherstructure where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "otherStructureMap")
    List<OtherStructure> getRecentOtherStructure(@Param("time") int time );

    @Select("select * from earthquake.otherstructure where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "otherStructureMap")
    List<OtherStructure> getRecentOtherStructureByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.otherstructure WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "otherStructureMap")
    public OtherStructure getOtherStructureById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`otherstructure` (`ID`, `date`, `location`, `basically_intact_square`, `slight_damaged_square`, `moderate_damaged_square`, `serious_damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{slightDamagedSquare},#{moderateDamagedSquare},#{seriousDamagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(OtherStructure otherStructure);

    @Update("UPDATE `earthquake`.`otherstructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `slight_damaged_square`=#{slightDamagedSquare}, `moderate_damaged_square`=#{moderateDamagedSquare}, `serious_damaged_square`=#{seriousDamagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(OtherStructure otherStructure);

    @Delete("DELETE FROM `earthquake`.`otherstructure` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.otherstructure order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.otherstructure order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "otherStructureMap")
    OtherStructure getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastotherstructurebytime;")
    @Results(id="lastOtherStructureByTime", value={
            @Result(column="date", property="date", jdbcType= JdbcType.CHAR),
            @Result(column="square", property="square", jdbcType= JdbcType.DOUBLE)
    })
    List<SquareStatistics> getLastOtherStructureByTime();

    @Select("SELECT * FROM earthquake.lastotherstructure;")
    @ResultMap(value = "otherStructureMap")
    List<OtherStructure> getLastOtherStructure();

    @Select("select * from earthquake.otherstructure where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "otherStructureMap")
    public List<OtherStructure> getCopyOtherStructure(@Param("time") int time);
}
