package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeathStatisticsMapper {
    @Select("SELECT max(ID) FROM earthquake.deathstatistics WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeDeathStatisticsByACId(String adminCateId);
}
