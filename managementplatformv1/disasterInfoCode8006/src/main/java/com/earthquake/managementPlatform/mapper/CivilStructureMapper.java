package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CivilStructureMapper {
    @Select("SELECT max(ID) FROM earthquake.civilstructure WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeCivilStructureByACId(String adminCateId);
}
