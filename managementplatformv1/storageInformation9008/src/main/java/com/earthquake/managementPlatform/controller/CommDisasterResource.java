package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CommDisasterMapper;
import com.earthquake.managementPlatform.mapper.PowerDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommDisasterResource {
    @Resource
    CommDisasterMapper commDisasterMapper;
    @GetMapping("/v1/commDisaster")
    public GetVo commDisasterAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = commDisasterMapper.getAllCommDisaster().size();
        List<CommDisaster> commDisaster = commDisasterMapper.getCommDisasterByPage((page-1)*limit,limit);
        GetVo<CommDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,commDisaster);
        return getVo;
    }

    @GetMapping("/v1/commDisaster/{time}")
    public GetVo commDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = commDisasterMapper.getRecentCommDisaster(timestamp).size();
        List<CommDisaster> commDisasters = commDisasterMapper.getRecentCommDisasterByPage((page-1)*limit,limit,timestamp);
        GetVo<CommDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,commDisasters);
        return getVo;
    }

    @GetMapping("/v1/lastCommDisasterStatistics")
    public GetVo getLastCommDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = commDisasterMapper.getCommDisasterStatistics();
        GetVo<LifeLineStatistics> getVo = new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
        return getVo;
    }

    @PutMapping("/v1/commDisaster/{id}")
    public PostVo editCommDisaster(HttpServletRequest request, @PathVariable("id") String id){
        CommDisaster commDisaster = new CommDisaster();
        commDisaster.setId(id);
        commDisaster.setDate(request.getParameter("date"));
        commDisaster.setLocation(request.getParameter("location"));
        commDisaster.setType(request.getParameter("type"));
        commDisaster.setGrade(request.getParameter("grade"));
        commDisaster.setPicture(request.getParameter("picture"));
        commDisaster.setNote(request.getParameter("note"));
        commDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        commDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        commDisasterMapper.update(commDisaster);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/commDisaster/{id}")
    public PostVo delCommDisaster(@PathVariable("id")String id){
        commDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/commDisasterCopy/{time}")
    public List<CommDisaster> commDisasterCopy(@PathVariable("time") int time){
        List<CommDisaster> commDisasters = commDisasterMapper.getCopyCommDisaster(time*24);
        return commDisasters;
    }
}
