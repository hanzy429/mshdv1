package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FrameworkStructureMapper {
    @Select("SELECT max(ID) FROM earthquake.frameworkstructure WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeFrameworkStructureByACId(String adminCateId);
}
