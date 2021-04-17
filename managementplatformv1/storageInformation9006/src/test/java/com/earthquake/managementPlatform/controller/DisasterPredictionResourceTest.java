package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.DisasterPrediction;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.mapper.DisasterPredictionMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class DisasterPredictionResourceTest {
    @Resource
    DisasterPredictionMapper disasterPredictionMapper;
    @Test
    public void getDisasterPredictionByPage()
    {
        int size = disasterPredictionMapper.getAllDisasterPrediction().size();
        log.info(String.valueOf(size));
        List<DisasterPrediction> disasterPrediction = disasterPredictionMapper.getDisasterPredictionByPage(0,10);
        GetVo<DisasterPrediction> getVo = new GetVo<>(0,"获取数据成功！",size,disasterPrediction);
        log.info(getVo.getMsg());
    }


}