package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MissingStatisticsMapper {
    @Select("SELECT max(ID) FROM earthquake.missingstatistics WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeMissingStatisticsByACId(String adminCateId);
}
