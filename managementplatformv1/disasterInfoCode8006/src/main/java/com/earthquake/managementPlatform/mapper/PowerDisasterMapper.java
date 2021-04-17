package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PowerDisasterMapper {
    @Select("SELECT max(ID) FROM earthquake.powerdisaster WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomePowerDisasterByACId(String adminCateId);
}
