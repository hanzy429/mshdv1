package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.TrafficDisasterMapper;
import com.earthquake.managementPlatform.mapper.WaterDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class WaterDisasterResource {
    @Resource
    WaterDisasterMapper waterDisasterMapper;
    @GetMapping("/v1/waterDisaster")
    public GetVo waterDisasterAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = waterDisasterMapper.getAllWaterDisaster().size();
        List<WaterDisaster> waterDisaster = waterDisasterMapper.getWaterDisasterByPage((page-1)*limit,limit);
        GetVo<WaterDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,waterDisaster);
        return getVo;
    }

    @GetMapping("/v1/waterDisaster/{time}")
    public GetVo waterDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = waterDisasterMapper.getRecentWaterDisaster(timestamp).size();
        List<WaterDisaster> waterDisasters = waterDisasterMapper.getRecentWaterDisasterByPage((page-1)*limit,limit,timestamp);
        GetVo<WaterDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,waterDisasters);
        return getVo;
    }

    @GetMapping("/v1/lastWaterDisasterStatistics")
    public GetVo getLastWaterDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = waterDisasterMapper.getWaterStatistics();
        GetVo<LifeLineStatistics> getVo = new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
        return getVo;
    }

    @PutMapping("/v1/waterDisaster/{id}")
    public PostVo editWaterDisaster(HttpServletRequest request, @PathVariable("id") String id){
        WaterDisaster waterDisaster= new WaterDisaster();
        waterDisaster.setId(id);
        waterDisaster.setDate(request.getParameter("date"));
        waterDisaster.setLocation(request.getParameter("location"));
        waterDisaster.setType(request.getParameter("type"));
        waterDisaster.setGrade(request.getParameter("grade"));
        waterDisaster.setPicture(request.getParameter("picture"));
        waterDisaster.setNote(request.getParameter("note"));
        waterDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        waterDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        waterDisasterMapper.update(waterDisaster);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/waterDisaster/{id}")
    public PostVo delWaterDisaster(@PathVariable("id")String id){
        waterDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/waterDisasterCopy/{time}")
    public List<WaterDisaster> waterDisasterCopy(@PathVariable("time") int time){
        List<WaterDisaster> waterDisasters = waterDisasterMapper.getCopyWaterDisaster(time*24);
        return waterDisasters;
    }
}
