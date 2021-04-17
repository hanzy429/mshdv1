package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.LandslideRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LandslideRecordResource {
    @Resource
    LandslideRecordMapper landslideRecordMapper;
    @GetMapping("/v1/landslideRecord")
    public GetVo landslideRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = landslideRecordMapper.getAllLandslideRecord().size();
        List<LandslideRecord> landslideRecord = landslideRecordMapper.getLandslideRecordByPage((page-1)*limit,limit);
        GetVo<LandslideRecord> getVo = new GetVo<>(0,"获取数据成功！",size,landslideRecord);
        return getVo;
    }

    @GetMapping("/v1/landslideRecord/{time}")
    public GetVo landslideRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = landslideRecordMapper.getRecentLandslideRecord(timestamp).size();
        List<LandslideRecord> landslideRecords = landslideRecordMapper.getRecentLandslideRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<LandslideRecord> getVo = new GetVo<>(0,"获取数据成功！",size,landslideRecords);
        return getVo;
    }

    @GetMapping("/v1/lastLandslideRecordStatistics")
    public GetVo getLastLandslideRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = landslideRecordMapper.getLandslideStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/landslideRecord/{id}")
    public PostVo editLandslideRecord(HttpServletRequest request, @PathVariable("id") String id){
        LandslideRecord landslideRecord = new LandslideRecord();
        landslideRecord.setId(id);
        landslideRecord.setDate(request.getParameter("date"));
        landslideRecord.setLocation(request.getParameter("location"));
        landslideRecord.setType(request.getParameter("type"));
        landslideRecord.setStatus(request.getParameter("status"));
        landslideRecord.setPicture(request.getParameter("picture"));
        landslideRecord.setNote(request.getParameter("note"));
        landslideRecord.setReportingUnit(request.getParameter("reportingUnit"));
        landslideRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        landslideRecordMapper.update(landslideRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/landslideRecord/{id}")
    public PostVo delLandslideRecord(@PathVariable("id")String id){
        landslideRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }
    @GetMapping("/v1/landslideRecordCopy/{time}")
    public List<LandslideRecord> landslideRecordCopy(@PathVariable("time") int time){
        List<LandslideRecord> landslideRecords = landslideRecordMapper.getCopyLandslideRecord(time*24);
        return landslideRecords;
    }
}
