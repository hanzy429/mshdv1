package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MasonryStructureMapper {
    @Select("SELECT max(ID) FROM earthquake.masonrystructure WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeMasonryStructureByACId(String adminCateId);
}
