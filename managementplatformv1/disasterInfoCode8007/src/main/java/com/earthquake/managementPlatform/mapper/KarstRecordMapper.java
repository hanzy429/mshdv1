package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface KarstRecordMapper {
    @Select("SELECT max(ID) FROM earthquake.karstrecord WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeKarstRecordByACId(String adminCateId);
}
