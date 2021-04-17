package com.earthquake.managementPlatform.service;

import com.alibaba.druid.util.StringUtils;
//import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.OnOffMapper;
import com.earthquake.managementPlatform.mapper.ScheduleMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@EnableScheduling
public class UploadFileScheduleService implements SchedulingConfigurer {
    @Resource
    ScheduleMapper scheduleMapper;

    @Resource
    OnOffMapper onOffMapper;

    @Resource
    private RestTemplate restTemplate;

    private  static String cron = "* * * * * *";

    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;

    @Value("${informationStorage.url}")
    private String informationStorageUrl;


    @Resource
    UploadFileService uploadFileService;

    @Resource
    FtpFileMethod ftpFileMethod;

    @Resource
    FtpPredictionFileMethod ftpPredictionFileMethod;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar){
        scheduledTaskRegistrar.addTriggerTask(doTask(),getTrigger());
    }

    private Runnable doTask(){
        return new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Map<JSONArray,String> map = uploadFileService.uploadEarthquakeInfoFiles();
                if(map!=null)
                    log.info(restTemplate.postForObject(disasterInfoCodeUrl+"/v1/disasterInfoCode",map, PostVo.class).getData().toString());
                else
                    log.info("目前无基本震情文件");
                boolean status = onOffMapper.getReadingFiles();
                if(status) {
                    map = uploadFileService.uploadFiles(ftpFileMethod);
                    if (map != null)
                        log.info(restTemplate.postForObject(disasterInfoCodeUrl + "/v1/disasterInfoCode", map, PostVo.class).getData().toString());
                    else
                        log.info("目前无灾情文件");
                    map = uploadFileService.uploadFiles(ftpPredictionFileMethod);
                    if (map != null)
                        log.info(restTemplate.postForObject(informationStorageUrl + "/v1/informationPredictionStorage", map, PostVo.class).getData().toString());
                    else
                        log.info("目前无灾情预测文件");
                }
                else
                {log.info("读取文件开关未开启！");}
            }
        };
    }

    private Trigger getTrigger(){
        return new Trigger() {
            @SneakyThrows
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(getCron());
                return trigger.nextExecutionTime(triggerContext);
            }
        };
    }

    public String getCron() throws Exception {
        String newCron = scheduleMapper.getSchedule();
        if (StringUtils.isEmpty(newCron)) {
            throw new Exception("The config cron expression is empty");
        }
        if (!newCron.equals(cron)) {
            log.info(new StringBuffer("Cron has been changed to:'").append(newCron).append("'. Old cron was:'").append(cron).append("'").toString());
            cron = newCron;
        }
        return cron;
    }
}
