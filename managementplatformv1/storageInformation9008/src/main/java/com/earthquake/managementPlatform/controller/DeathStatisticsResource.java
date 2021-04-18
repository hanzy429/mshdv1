package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.DeathStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DeathStatisticsResource {
    @Resource
    DeathStatisticsMapper deathStatisticsMapper;
    @GetMapping("/v1/deathStatistics")
    public GetVo deathStatisticsAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = deathStatisticsMapper.getAllDeathStatistics().size();
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getDeathStatisticsByPage((page-1)*limit,limit);
        GetVo<DeathStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,deathStatistics);
        return getVo;
    }

    @GetMapping("/v1/deathStatistics/{time}")
    public GetVo deathStatisticsByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = deathStatisticsMapper.getRecentDeathStatistics(timestamp).size();
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getRecentDeathStatisticsByPage((page-1)*limit,limit,timestamp);
        GetVo<DeathStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,deathStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastDeathStatisticsByTime")
    public GetVo getLastDeathStatisticsByTime(){
        List<PersonStatistics> personStatistics = deathStatisticsMapper.getLastDeathStatisticsByTime();
        GetVo<PersonStatistics> getVo = new GetVo<>(0,"获取数据成功！",personStatistics.size(),personStatistics);
        return getVo;
    }

    @PutMapping("/v1/deathStatistics/{id}")
    public PostVo editDeathStatistics(HttpServletRequest request, @PathVariable("id") String id){
        DeathStatistics deathStatistics = new DeathStatistics();
        deathStatistics.setId(id);
        deathStatistics.setDate(request.getParameter("date"));
        deathStatistics.setLocation(request.getParameter("location"));
        deathStatistics.setNumber(Integer.valueOf(request.getParameter("number")));
        deathStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        deathStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        deathStatisticsMapper.update(deathStatistics);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/deathStatistics/{id}")
    public PostVo delDeathStatistics(@PathVariable("id")String id){
        deathStatisticsMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/deathStatisticsCopy/{time}")
    public List<DeathStatistics> deathStatisticsCopy(@PathVariable("time") int time){
        List<DeathStatistics> deathStatistics = deathStatisticsMapper.getCopyDeathStatistics(time*24);
        return deathStatistics;
    }
}
