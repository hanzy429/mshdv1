package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.OtherStructureMapper;
import com.earthquake.managementPlatform.mapper.TrafficDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TrafficDisasterResource {
    @Resource
    TrafficDisasterMapper trafficDisasterMapper;
    @GetMapping("/v1/trafficDisaster")
    public GetVo trafficDisasterAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = trafficDisasterMapper.getAllTrafficDisaster().size();
        List<TrafficDisaster> trafficDisaster = trafficDisasterMapper.getTrafficDisasterByPage((page-1)*limit,limit);
        GetVo<TrafficDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,trafficDisaster);
        return getVo;
    }

    @GetMapping("/v1/trafficDisaster/{time}")
    public GetVo trafficDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = trafficDisasterMapper.getRecentTrafficDisaster(timestamp).size();
        List<TrafficDisaster> trafficDisasters = trafficDisasterMapper.getRecentTrafficDisasterByPage((page-1)*limit,limit,timestamp);
        GetVo<TrafficDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,trafficDisasters);
        return getVo;
    }

    @GetMapping("/v1/lastTrafficDisasterStatistics")
    public GetVo getLastTrafficDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = trafficDisasterMapper.getTrafficStatistics();
        GetVo<LifeLineStatistics> getVo = new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
        return getVo;
    }

    @PutMapping("/v1/trafficDisaster/{id}")
    public PostVo editTrafficDisaster(HttpServletRequest request, @PathVariable("id") String id){
        TrafficDisaster trafficDisaster= new TrafficDisaster();
        trafficDisaster.setId(id);
        trafficDisaster.setDate(request.getParameter("date"));
        trafficDisaster.setLocation(request.getParameter("location"));
        trafficDisaster.setType(request.getParameter("type"));
        trafficDisaster.setGrade(request.getParameter("grade"));
        trafficDisaster.setPicture(request.getParameter("picture"));
        trafficDisaster.setNote(request.getParameter("note"));
        trafficDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        trafficDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        trafficDisasterMapper.update(trafficDisaster);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/trafficDisaster/{id}")
    public PostVo delTrafficDisaster(@PathVariable("id")String id){
        trafficDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/trafficDisasterCopy/{time}")
    public List<TrafficDisaster> trafficDisasterCopy(@PathVariable("time") int time){
        List<TrafficDisaster> trafficDisasters = trafficDisasterMapper.getCopyTrafficDisaster(time*24);
        return trafficDisasters;
    }
}
