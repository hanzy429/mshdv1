package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GasDisasterMapper {
    @Select("SELECT max(ID) FROM earthquake.gasdisaster WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeGasDisasterByACId(String adminCateId);
}
