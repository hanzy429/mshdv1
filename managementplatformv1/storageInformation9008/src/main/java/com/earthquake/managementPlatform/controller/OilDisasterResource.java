package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.OilDisasterMapper;
import com.earthquake.managementPlatform.mapper.WaterDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OilDisasterResource {
    @Resource
    OilDisasterMapper oilDisasterMapper;
    @GetMapping("/v1/oilDisaster")
    public GetVo oilDisasterAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = oilDisasterMapper.getAllOilDisaster().size();
        List<OilDisaster> oilDisaster = oilDisasterMapper.getOilDisasterByPage((page-1)*limit,limit);
        GetVo<OilDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,oilDisaster);
        return getVo;
    }

    @GetMapping("/v1/oilDisaster/{time}")
    public GetVo oilDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = oilDisasterMapper.getRecentOilDisaster(timestamp).size();
        List<OilDisaster> oilDisasters = oilDisasterMapper.getRecentOilDisasterByPage((page-1)*limit,limit,timestamp);
        GetVo<OilDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,oilDisasters);
        return getVo;
    }

    @GetMapping("/v1/lastOilDisasterStatistics")
    public GetVo getLastOilDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = oilDisasterMapper.getOilStatistics();
        GetVo<LifeLineStatistics> getVo = new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
        return getVo;
    }

    @PutMapping("/v1/oilDisaster/{id}")
    public PostVo editOilDisaster(HttpServletRequest request, @PathVariable("id") String id){
        OilDisaster oilDisaster= new OilDisaster();
        oilDisaster.setId(id);
        oilDisaster.setDate(request.getParameter("date"));
        oilDisaster.setLocation(request.getParameter("location"));
        oilDisaster.setType(request.getParameter("type"));
        oilDisaster.setGrade(request.getParameter("grade"));
        oilDisaster.setPicture(request.getParameter("picture"));
        oilDisaster.setNote(request.getParameter("note"));
        oilDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        oilDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        oilDisasterMapper.update(oilDisaster);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/oilDisaster/{id}")
    public PostVo delOilDisaster(@PathVariable("id")String id){
        oilDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/oilDisasterCopy/{time}")
    public List<OilDisaster> oilDisasterCopy(@PathVariable("time") int time){
        List<OilDisaster> oilDisasters = oilDisasterMapper.getCopyOilDisaster(time*24);
        return oilDisasters;
    }
}
