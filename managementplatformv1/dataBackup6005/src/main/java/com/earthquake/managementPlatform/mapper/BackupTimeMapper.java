package com.earthquake.managementPlatform.mapper;

import com.earthquake.managementPlatform.entities.BackupTime;
import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.service.BackupService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BackupTimeMapper {

    @Select("SELECT time FROM earthquake.backuptime WHERE id = 1;")
    int getTime();

    @Update("UPDATE `earthquake`.`backuptime` SET `time`= #{time} WHERE `id`=#{id}")
    int update(BackupTime backupTime);
}
