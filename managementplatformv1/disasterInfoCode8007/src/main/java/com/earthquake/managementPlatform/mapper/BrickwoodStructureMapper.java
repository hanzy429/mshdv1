package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BrickwoodStructureMapper {
    @Select("SELECT max(ID) FROM earthquake.brickwoodstructure WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeBrickwoodStructureByACId(String adminCateId);
}
