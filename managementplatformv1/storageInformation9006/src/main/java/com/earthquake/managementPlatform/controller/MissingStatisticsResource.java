package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.InjuredStatisticsMapper;
import com.earthquake.managementPlatform.mapper.MissingStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MissingStatisticsResource {
    @Resource
    MissingStatisticsMapper missingStatisticsMapper;
    @GetMapping("/v1/missingStatistics")
    public GetVo missingStatisticsAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = missingStatisticsMapper.getAllMissingStatistics().size();
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getMissingStatisticsByPage((page-1)*limit,limit);
        GetVo<MissingStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,missingStatistics);
        return getVo;
    }

    @GetMapping("/v1/missingStatistics/{time}")
    public GetVo missingStatisticsByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = missingStatisticsMapper.getRecentMissingStatistics(timestamp).size();
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getRecentMissingStatisticsByPage((page-1)*limit,limit,timestamp);
        GetVo<MissingStatistics> getVo = new GetVo<>(0,"获取数据成功！",size,missingStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastMissingStatisticsByTime")
    public GetVo getLastMissingStatisticsByTime(){
        List<PersonStatistics> personStatistics = missingStatisticsMapper.getLastMissingStatisticsByTime();
        GetVo<PersonStatistics> getVo = new GetVo<>(0,"获取数据成功！",personStatistics.size(),personStatistics);
        return getVo;
    }

    @PutMapping("/v1/missingStatistics/{id}")
    public PostVo editMissingStatistics(HttpServletRequest request, @PathVariable("id") String id){
        MissingStatistics missingStatistics = new MissingStatistics();
        missingStatistics.setId(id);
        missingStatistics.setDate(request.getParameter("date"));
        missingStatistics.setLocation(request.getParameter("location"));
        missingStatistics.setNumber(Integer.valueOf(request.getParameter("number")));
        missingStatistics.setReportingUnit(request.getParameter("reportingUnit"));
        missingStatistics.setEarthquakeId(request.getParameter("earthquakeId"));
        missingStatisticsMapper.update(missingStatistics);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/missingStatistics/{id}")
    public PostVo delMissingStatistics(@PathVariable("id")String id){
        missingStatisticsMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/missingStatisticsCopy/{time}")
    public List<MissingStatistics> missingStatisticsCopy(@PathVariable("time") int time){
        List<MissingStatistics> missingStatistics = missingStatisticsMapper.getCopyMissingStatistics(time*24);
        return missingStatistics;
    }




}
