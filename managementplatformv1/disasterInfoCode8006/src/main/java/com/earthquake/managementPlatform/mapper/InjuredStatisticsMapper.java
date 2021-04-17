package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InjuredStatisticsMapper {
    @Select("SELECT max(ID) FROM earthquake.injuredstatistics WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeInjuredStatisticsByACId(String adminCateId);
}
