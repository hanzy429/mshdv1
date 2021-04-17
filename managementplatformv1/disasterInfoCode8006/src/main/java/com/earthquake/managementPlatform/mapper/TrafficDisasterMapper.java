package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TrafficDisasterMapper {
    @Select("SELECT max(ID) FROM earthquake.trafficdisaster WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeTrafficDisasterByACId(String adminCateId);
}
