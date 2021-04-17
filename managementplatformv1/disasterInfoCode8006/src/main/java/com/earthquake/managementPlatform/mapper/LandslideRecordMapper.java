package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LandslideRecordMapper {
    @Select("SELECT max(ID) FROM earthquake.landsliderecord WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeLandslideRecordByACId(String adminCateId);
}
