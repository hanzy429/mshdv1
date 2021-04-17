package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CollapseRecordMapper {
    @Select("SELECT max(ID) FROM earthquake.collapserecord WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeCollapseRecordByACId(String adminCateId);
}
