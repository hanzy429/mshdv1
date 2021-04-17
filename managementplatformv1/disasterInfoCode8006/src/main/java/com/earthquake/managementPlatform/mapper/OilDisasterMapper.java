package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OilDisasterMapper {
    @Select("SELECT max(ID) FROM earthquake.oildisaster WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeOilDisasterByACId(String adminCateId);
}
