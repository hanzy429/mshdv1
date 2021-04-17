package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.BackupTime;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.Schedule;
import com.earthquake.managementPlatform.mapper.BackupTimeMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BackupResource {

    @Resource
    BackupTimeMapper backupTimeMapper;

    @PostMapping("/v1/changeBackupTime/{time}")
    public PostVo changeSchedule(@PathVariable("time") int time){
        int result = backupTimeMapper.update(new BackupTime(1,time));
        if(result == 1)
            return new PostVo(0,"修改成功！",null);
        else
            return  new PostVo(1,"修改失败！",null);
    }
}
