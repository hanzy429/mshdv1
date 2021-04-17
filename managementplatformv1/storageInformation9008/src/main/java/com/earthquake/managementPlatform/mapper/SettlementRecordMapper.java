package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.PowerDisaster;
import com.earthquake.managementPlatform.entities.SecondaryDisasterStatistics;
import com.earthquake.managementPlatform.entities.SettlementRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SettlementRecordMapper {
    @Select("SELECT * FROM earthquake.settlementrecord;")
    @Results(id="settlementRecordMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.CHAR, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="location", property="location", jdbcType= JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="picture", property="picture", jdbcType= JdbcType.VARCHAR),
            @Result(column="note", property="note", jdbcType= JdbcType.VARCHAR),
            @Result(column="reporting_unit", property="reportingUnit", jdbcType= JdbcType.VARCHAR),
            @Result(column="earthquake_id", property="earthquakeId", jdbcType= JdbcType.CHAR)
    })
    List<SettlementRecord> getAllSettlementRecord();

    @Select("select * from earthquake.settlementrecord order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "settlementRecordMap")
    List<SettlementRecord> getSettlementRecordByPage(@Param("pageNum") int pageNum, @Param("limit")int limit);

    @Select("select * from earthquake.settlementrecord where date >=  NOW() - interval #{time} hour order by date desc;")
    @ResultMap(value = "settlementRecordMap")
    List<SettlementRecord> getRecentSettlementRecord(@Param("time") int time );

    @Select("select * from earthquake.settlementrecord where date >=  NOW() - interval #{time} hour order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "settlementRecordMap")
    List<SettlementRecord> getRecentSettlementRecordByPage(@Param("pageNum") int pageNum,@Param("limit")int limit,@Param("time") int time );

    @Select("SELECT * FROM earthquake.settlementrecord WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "settlementRecordMap")
    public SettlementRecord getSettlementRecordById(String id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`settlementrecord` (`ID`, `date`, `location`, `type`, `status`, `picture`, `note`, `reporting_unit`, `earthquake_id`) VALUES (#{id},#{date},#{location},#{type},#{status},#{picture},#{note},#{reportingUnit},#{earthquakeId})")
    int save(SettlementRecord settlementRecord);

    @Update("UPDATE `earthquake`.`settlementrecord` SET `date`=#{date}, `location`=#{location}, `type`=#{type}, `status`=#{status}, `picture`=#{picture}, `note`=#{note},`reporting_unit`=#{reportingUnit},`earthquake_id`=#{earthquakeId} WHERE `ID`=#{id}")
    int update(SettlementRecord settlementRecord);

    @Delete("DELETE FROM `earthquake`.`settlementrecord` WHERE ID = #{id}")
    int deleteById(String id);

    @Select("SELECT ID FROM earthquake.settlementrecord order by date desc limit 1 FOR UPDATE")
    String getNewCode();

    @Select("SELECT * FROM earthquake.settlementrecord order by date desc limit 1 FOR UPDATE")
    @ResultMap(value = "settlementRecordMap")
    SettlementRecord getNewCodeDescription();

    @Select("SELECT * FROM earthquake.lastsettlementrecord")
    @Results(id="secondaryDisasterStatisticsMap", value={
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType= JdbcType.INTEGER),
    })
    List<SecondaryDisasterStatistics> getSettlementStatistics();

    @Select("select * from earthquake.settlementrecord where date <  NOW() - interval #{time} hour;")
    @ResultMap(value = "settlementRecordMap")
    public List<SettlementRecord> getCopySettlementRecord(@Param("time") int time);
}
