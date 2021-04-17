package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CivilStructureMapper {
    @Select("SELECT * FROM earthquake.civilstructure;")
    @Results(id="civilStructureMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="basically_intact_square", property="basicallyIntactSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="damaged_square", property="damagedSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="destroyed_square", property="destroyedSquare", jdbcType= JdbcType.DOUBLE),
            @Result(column="note", property="note", jdbcType= JdbcType.VARCHAR),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<CivilStructure> getAllCivilStructure();

    @Select("select * from earthquake.civilstructure order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "civilStructureMap")
    List<CivilStructure> getCivilStructureByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.civilstructure where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "civilStructureMap")
    List<CivilStructure> getRecentCivilStructure(@Param("time") int time );

    @Select("select * from earthquake.civilstructure where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "civilStructureMap")
    List<CivilStructure> getRecentCivilStructureByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.civilstructure WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "civilStructureMap")
    public CivilStructure getCivilStructureById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`civilstructure` (`ID`, `date`, `location`, `basically_intact_square`, `damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{damagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(CivilStructure civilStructure);

    @Update("UPDATE `earthquake`.`civilstructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `damaged_square`=#{damagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(CivilStructure civilStructure);

    @Delete("DELETE FROM `earthquake`.`civilstructure` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.civilstructure order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.civilstructure order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "civilStructureMap")
    CivilStructure getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastcivilstructurebytime;")
    @Results(id="lastCivilStructureByTime", value={
            @Result(column="date", property="date", jdbcType= JdbcType.CHAR),
            @Result(column="square", property="square", jdbcType= JdbcType.DOUBLE)
    })
    List<SquareStatistics> getLastCivilStructureByTime();

    @Select("SELECT * FROM earthquake.lastcivilstructure;")
    @ResultMap(value = "civilStructureMap")
    List<CivilStructure> getLastCivilStructure();

    @Select("select * from earthquake.civilstructure where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "civilStructureMap")
    public List<CivilStructure> getCopyCivilStructure(@Param("time") int time);
}
