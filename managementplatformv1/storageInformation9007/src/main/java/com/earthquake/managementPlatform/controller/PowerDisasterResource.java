package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.GasDisasterMapper;
import com.earthquake.managementPlatform.mapper.PowerDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class PowerDisasterResource {
    @Resource
    PowerDisasterMapper powerDisasterMapper;
    @GetMapping("/v1/powerDisaster")
    public GetVo powerDisasterAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = powerDisasterMapper.getAllPowerDisaster().size();
        List<PowerDisaster> powerDisaster = powerDisasterMapper.getPowerDisasterByPage((page-1)*limit,limit);
        GetVo<PowerDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,powerDisaster);
        return getVo;
    }

    @GetMapping("/v1/powerDisaster/{time}")
    public GetVo powerDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = powerDisasterMapper.getRecentPowerDisaster(timestamp).size();
        List<PowerDisaster> powerDisasters = powerDisasterMapper.getRecentPowerDisasterByPage((page-1)*limit,limit,timestamp);
        GetVo<PowerDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,powerDisasters);
        return getVo;
    }

    @GetMapping("/v1/lastPowerDisasterStatistics")
    public GetVo getLastPowerDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = powerDisasterMapper.getPowerStatistics();
        GetVo<LifeLineStatistics> getVo = new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
        return getVo;
    }

    @PutMapping("/v1/powerDisaster/{id}")
    public PostVo editPowerDisaster(HttpServletRequest request, @PathVariable("id") String id){
        PowerDisaster powerDisaster = new PowerDisaster();
        powerDisaster.setId(id);
        powerDisaster.setDate(request.getParameter("date"));
        powerDisaster.setLocation(request.getParameter("location"));
        powerDisaster.setType(request.getParameter("type"));
        powerDisaster.setGrade(request.getParameter("grade"));
        powerDisaster.setPicture(request.getParameter("picture"));
        powerDisaster.setNote(request.getParameter("note"));
        powerDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        powerDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        powerDisasterMapper.update(powerDisaster);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/powerDisaster/{id}")
    public PostVo delPowerDisaster(@PathVariable("id")String id){
        powerDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/powerDisasterCopy/{time}")
    public List<PowerDisaster> powerDisasterCopy(@PathVariable("time") int time){
        List<PowerDisaster> powerDisasters = powerDisasterMapper.getCopyPowerDisaster(time*24);
        return powerDisasters;
    }
}
