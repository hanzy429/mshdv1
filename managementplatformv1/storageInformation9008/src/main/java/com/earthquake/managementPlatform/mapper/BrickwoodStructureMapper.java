package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface BrickwoodStructureMapper {
    @Select("SELECT * FROM earthquake.brickwoodstructure;")
    @Results(id="brickwoodStructureMap", value={
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
    List<BrickwoodStructure> getAllBrickwoodStructure();

    @Select("select * from earthquake.brickwoodstructure order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "brickwoodStructureMap")
    List<BrickwoodStructure> getBrickwoodStructureByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);

    @Select("select * from earthquake.brickwoodstructure where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "brickwoodStructureMap")
    List<BrickwoodStructure> getRecentBrickwoodStructure(@Param("time") int time );

    @Select("select * from earthquake.brickwoodstructure where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "brickwoodStructureMap")
    List<BrickwoodStructure> getRecentBrickwoodStructureByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.brickwoodstructure WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "brickwoodStructureMap")
    public BrickwoodStructure getBrickwoodStructureById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`brickwoodstructure` (`ID`, `date`, `location`, `basically_intact_square`, `damaged_square`, `destroyed_square`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{basicallyIntactSquare},#{damagedSquare},#{destroyedSquare},#{note},#{reportingUnit},#{earthquakeId})")
    int save(BrickwoodStructure brickwoodStructure);

    @Update("UPDATE `earthquake`.`brickwoodstructure` SET `date`=#{date}, `location`=#{location}, `basically_intact_square`=#{basicallyIntactSquare}, `damaged_square`=#{damagedSquare}, `destroyed_square`=#{destroyedSquare}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(BrickwoodStructure brickwoodStructure);

    @Delete("DELETE FROM `earthquake`.`brickwoodstructure` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.brickwoodstructure order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.brickwoodstructure order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "brickwoodStructureMap")
    BrickwoodStructure getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastbrickwoodstructurebytime;")
    @Results(id="lastBrickwoodStructureByTime", value={
            @Result(column="date", property="date", jdbcType= JdbcType.CHAR),
            @Result(column="square", property="square", jdbcType= JdbcType.DOUBLE)
    })
    List<SquareStatistics> getLastBrickwoodStructureByTime();

    @Select("SELECT * FROM earthquake.lastbrickwoodstructure;")
    @ResultMap(value = "brickwoodStructureMap")
    List<BrickwoodStructure> getLastBrickwoodStructure();

    @Select("select * from earthquake.brickwoodstructure where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "brickwoodStructureMap")
    public List<BrickwoodStructure> getCopyBrickwoodStructure(@Param("time") int time);


}
