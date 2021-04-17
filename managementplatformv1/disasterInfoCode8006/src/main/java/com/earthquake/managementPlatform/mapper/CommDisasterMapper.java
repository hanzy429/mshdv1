package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommDisasterMapper {
    @Select("SELECT max(ID) FROM earthquake.commdisaster WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeCommDisasterByACId(String adminCateId);
}
