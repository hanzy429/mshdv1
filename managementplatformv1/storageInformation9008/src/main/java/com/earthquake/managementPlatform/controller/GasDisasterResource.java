package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.GasDisasterMapper;
import com.earthquake.managementPlatform.mapper.OilDisasterMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class GasDisasterResource {
    @Resource
    GasDisasterMapper gasDisasterMapper;
    @GetMapping("/v1/gasDisaster")
    public GetVo gasDisasterAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = gasDisasterMapper.getAllGasDisaster().size();
        List<GasDisaster> gasDisaster = gasDisasterMapper.getGasDisasterByPage((page-1)*limit,limit);
        GetVo<GasDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,gasDisaster);
        return getVo;
    }

    @GetMapping("/v1/gasDisaster/{time}")
    public GetVo gasDisasterByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = gasDisasterMapper.getRecentGasDisaster(timestamp).size();
        List<GasDisaster> gasDisasters = gasDisasterMapper.getRecentGasDisasterByPage((page-1)*limit,limit,timestamp);
        GetVo<GasDisaster> getVo = new GetVo<>(0,"获取数据成功！",size,gasDisasters);
        return getVo;
    }

    @GetMapping("/v1/lastGasDisasterStatistics")
    public GetVo getLastGasDisasterStatistics(){
        List<LifeLineStatistics> lifeLineStatistics = gasDisasterMapper.getGasStatistics();
        GetVo<LifeLineStatistics> getVo = new GetVo<>(0,"获取数据成功！",lifeLineStatistics.size(),lifeLineStatistics);
        return getVo;
    }

    @PutMapping("/v1/gasDisaster/{id}")
    public PostVo editGasDisaster(HttpServletRequest request, @PathVariable("id") String id){
        GasDisaster gasDisaster= new GasDisaster();
        gasDisaster.setId(id);
        gasDisaster.setDate(request.getParameter("date"));
        gasDisaster.setLocation(request.getParameter("location"));
        gasDisaster.setType(request.getParameter("type"));
        gasDisaster.setGrade(request.getParameter("grade"));
        gasDisaster.setPicture(request.getParameter("picture"));
        gasDisaster.setNote(request.getParameter("note"));
        gasDisaster.setReportingUnit(request.getParameter("reportingUnit"));
        gasDisaster.setEarthquakeId(request.getParameter("earthquakeId"));
        gasDisasterMapper.update(gasDisaster);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/gasDisaster/{id}")
    public PostVo delGasDisaster(@PathVariable("id")String id){
        gasDisasterMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/gasDisasterCopy/{time}")
    public List<GasDisaster>  gasDisasterCopy(@PathVariable("time") int time){
        List<GasDisaster> gasDisasters = gasDisasterMapper.getCopyGasDisaster(time*24);
        return gasDisasters;
    }
}
