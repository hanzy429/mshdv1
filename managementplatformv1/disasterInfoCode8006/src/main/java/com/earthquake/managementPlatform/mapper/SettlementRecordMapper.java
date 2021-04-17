package com.earthquake.managementPlatform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SettlementRecordMapper {
    @Select("SELECT max(ID) FROM earthquake.settlementrecord WHERE ID like concat(#{adminCateId},'%') FOR UPDATE")
    public String getSomeSettlementRecordByACId(String adminCateId);
}
