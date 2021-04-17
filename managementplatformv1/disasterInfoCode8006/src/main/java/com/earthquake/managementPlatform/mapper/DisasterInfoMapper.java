package com.earthquake.managementPlatform.mapper;


import org.apache.ibatis.annotations.*;

@Mapper
public interface DisasterInfoMapper {


    @Select("SELECT max(ID) FROM earthquake.disasterinfo WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeDisasterInfoByACId(String adminCateId);
}
