package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.Schedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {
    String getSchedule();
    int updateSchedule(Schedule schedule);
}
