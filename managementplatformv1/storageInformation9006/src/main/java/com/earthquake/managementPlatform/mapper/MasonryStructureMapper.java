package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface MasonryStructureMapper {
    @Select("SELECT * FROM earthquake.masonrystructure;")
    @Results(id="masonryStructureMap", value={
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
    List<MasonryStructure> getAllMasonryStructure();

    @Select("select * from earthquake.masonrystructure order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "masonryStructureMap")
    List<MasonryStructure> getMasonryStructureByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.masonrystructure where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "masonryStructureMap")
    List<MasonryStructure> getRecentMasonryStructure(@Param("time") int time );

    @Select("select * from earthquake.masonrystructure where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "masonryStructureMap")
    List<MasonryStructure> getRecentMasonryStructureByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.masonrystructure WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "masonryStructureMap")
    public MasonryStructure getMasonryStructureById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`masonrystructure` (`ID`, `date`, `location`, `basically_intact_square`, `slight_damaged_square`, `moderate_damaged_square`, `serious_damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{slightDamagedSquare},#{moderateDamagedSquare},#{seriousDamagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(MasonryStructure masonryStructure);

    @Update("UPDATE `earthquake`.`masonrystructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `slight_damaged_square`=#{slightDamagedSquare}, `moderate_damaged_square`=#{moderateDamagedSquare}, `serious_damaged_square`=#{seriousDamagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(MasonryStructure masonryStructure);

    @Delete("DELETE FROM `earthquake`.`masonrystructure` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.masonrystructure order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.masonrystructure order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "masonryStructureMap")
    MasonryStructure getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastmasonrystructurebytime;")
    @Results(id="lastMasonryStructureByTime", value={
            @Result(column="date", property="date", jdbcType= JdbcType.CHAR),
            @Result(column="square", property="square", jdbcType= JdbcType.DOUBLE)
    })
    List<SquareStatistics> getLastMasonryStructureByTime();

    @Select("SELECT * FROM earthquake.lastmasonrystructure;")
    @ResultMap(value = "masonryStructureMap")
    List<MasonryStructure> getLastMasonryStructure();

    @Select("select * from earthquake.masonrystructure where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "masonryStructureMap")
    public List<MasonryStructure> getCopyMasonryStructure(@Param("time") int time);
}
