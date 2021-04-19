package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.DisasterRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface DisasterRequestMapper {
    @Select("SELECT * FROM earthquake.disasterrequest;")
    @Results(id="disasterRequestMap", value={
            @Result(column="ID", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="date", property="date", jdbcType= JdbcType.VARCHAR),
            @Result(column="disaster_type", property="disasterType", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.VARCHAR),
            @Result(column="o_url", property="oURL", jdbcType= JdbcType.VARCHAR),
            @Result(column="request_unit", property="requestUnit", jdbcType= JdbcType.VARCHAR),
    })
    List<DisasterRequest> getAllDisasterRequest();

    @Select("select * from earthquake.disasterrequest order by date desc limit #{pageNum}, #{limit};")
    @ResultMap(value = "disasterRequestMap")
    List<DisasterRequest> getDisasterRequestByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);

    @Select("SELECT * FROM earthquake.disasterrequest WHERE ID = #{id} FOR UPDATE")
    @ResultMap(value = "disasterRequestMap")
    public DisasterRequest getDisasterRequestById(int id);

//    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
//    public String getSomeDisasterInfoByACId(String adminCateId);

    @Insert("INSERT INTO `earthquake`.`disasterrequest` (`date`, `disaster_type`, `status`, `o_url`, `request_unit`) VALUES (#{date},#{disasterType},#{status},#{oURL},#{requestUnit})")
    int save(DisasterRequest disasterRequest);

    @Update("UPDATE `earthquake`.`disasterrequest` SET `ID`= #{id}, `date`=#{date}, `disaster_type`=#{disasterType}, `status`=#{status},`o_url`=#{oURL},`request_unit`=#{requestUnit} WHERE `ID`=#{id}")
    int update(DisasterRequest disasterRequest);

    @Update("UPDATE `earthquake`.`disasterrequest` SET `status`='1' WHERE `ID`=#{id}")
    int updateById(int id);

    @Delete("DELETE FROM `earthquake`.`disasterrequest` WHERE ID = #{id}")
    int deleteById(int id);

    @Select("SELECT * FROM earthquake.disasterrequest WHERE status = '1' FOR UPDATE")
    @ResultMap(value = "disasterRequestMap")
    List<DisasterRequest> getProcessedDisasterRequest();

    @Select("SELECT * FROM earthquake.disasterrequest WHERE status = '1' order by date desc limit #{pageNum}, #{limit} FOR UPDATE;")
    @ResultMap(value = "disasterRequestMap")
    List<DisasterRequest> getProcessedDisasterRequestByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);

    @Select("SELECT * FROM earthquake.disasterrequest WHERE status = '0' FOR UPDATE")
    @ResultMap(value = "disasterRequestMap")
    List<DisasterRequest> getNoProcessedDisasterRequest();

    @Select("SELECT * FROM earthquake.disasterrequest WHERE status = '0' order by date desc limit #{pageNum}, #{limit} FOR UPDATE")
    @ResultMap(value = "disasterRequestMap")
    List<DisasterRequest> getNoProcessedDisasterRequestByPage(@Param("pageNum") int pageNum,@Param("limit")int limit);
}

