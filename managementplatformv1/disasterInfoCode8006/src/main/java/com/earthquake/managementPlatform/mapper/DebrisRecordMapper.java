package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DebrisRecordMapper {
    @Select("SELECT max(ID) FROM earthquake.debrisrecord WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeDebrisRecordByACId(String adminCateId);
}
