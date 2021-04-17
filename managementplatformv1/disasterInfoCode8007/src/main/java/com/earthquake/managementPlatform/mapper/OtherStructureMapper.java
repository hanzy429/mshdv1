package com.earthquake.managementPlatform.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OtherStructureMapper {
    @Select("SELECT max(ID) FROM earthquake.otherstructure WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeOtherStructureByACId(String adminCateId);
}
