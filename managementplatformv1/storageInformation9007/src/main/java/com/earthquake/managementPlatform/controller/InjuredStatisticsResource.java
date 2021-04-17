package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.InjuredStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class InjuredStatisticsResource {
    @Resource
    InjuredStatisticsMapper injuredStatisticsMapper;
    @GetMapping("/v1/injuredStatistics")
    public GetVo injuredStatisticsAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = injuredStatisticsMapper.getAllInjuredStatistics().size();
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getInjuredStatisticsByPage((page-1)*limit,limit);
        GetVo<InjuredStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,injuredStatistics);
        return getVo;
    }

    @GetMapping("/v1/injuredStatistics/{time}")
    public GetVo injuredStatisticsByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = injuredStatisticsMapper.getRecentInjuredStatistics(timestamp).size();
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getRecentInjuredStatisticsByPage((page-1)*limit,limit,timestamp);
        GetVo<InjuredStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,injuredStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastInjuredStatisticsByTime")
    public GetVo getLastInjuredStatisticsByTime(){
        List<PersonStatistics> personStatistics = injuredStatisticsMapper.getLastInjuredStatisticsByTime();
        GetVo<PersonStatistics> getVo = new GetVo<>(0,"获取数据成功！",personStatistics.size(),personStatistics);
        return getVo;
    }

    @PutMapping("/v1/injuredStatistics/{id}")
    public PostVo editInjuredStatistics(HttpServletRequest request, @PathVariable("id") String id){
        InjuredStatistics injuredStatistics = new InjuredStatistics();
        injuredStatistics.setId(id);
        injuredStatistics.setDate(request.getParameter("date"));
        injuredStatistics.setLocation(request.getParameter("location"));
        injuredStatistics.setNumber(Integer.valueOf(request.getParameter("number")));
        injuredStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        injuredStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        injuredStatisticsMapper.update(injuredStatistics);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/injuredStatistics/{id}")
    public PostVo delInjuredStatistics(@PathVariable("id")String id){
        injuredStatisticsMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/injuredStatisticsCopy/{time}")
    public List<InjuredStatistics> injuredStatisticsCopy(@PathVariable("time") int time){
        List<InjuredStatistics> injuredStatistics = injuredStatisticsMapper.getCopyInjuredStatistics(time*24);
        return injuredStatistics;
    }
}
