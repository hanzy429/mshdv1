package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CollapseRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CollapseRecordResource {
    @Resource
    CollapseRecordMapper collapseRecordMapper;
    @GetMapping("/v1/collapseRecord")
    public GetVo collapseRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = collapseRecordMapper.getAllCollapseRecord().size();
        List<CollapseRecord> collapseRecord = collapseRecordMapper.getCollapseRecordByPage((page-1)*limit,limit);
        GetVo<CollapseRecord> getVo = new GetVo<>(0,"获取数据成功！",size,collapseRecord);
        return getVo;
    }

    @GetMapping("/v1/collapseRecord/{time}")
    public GetVo collapseRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = collapseRecordMapper.getRecentCollapseRecord(timestamp).size();
        List<CollapseRecord> collapseRecords = collapseRecordMapper.getRecentCollapseRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<CollapseRecord> getVo = new GetVo<>(0,"获取数据成功！",size,collapseRecords);
        return getVo;
    }

    @GetMapping("/v1/lastCollapseRecordStatistics")
    public GetVo getLastCollapseRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = collapseRecordMapper.getCollapseStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/collapseRecord/{id}")
    public PostVo editCollapseRecord(HttpServletRequest request, @PathVariable("id") String id){
        CollapseRecord collapseRecord = new CollapseRecord();
        collapseRecord.setId(id);
        collapseRecord.setDate(request.getParameter("date"));
        collapseRecord.setLocation(request.getParameter("location"));
        collapseRecord.setType(request.getParameter("type"));
        collapseRecord.setStatus(request.getParameter("status"));
        collapseRecord.setPicture(request.getParameter("picture"));
        collapseRecord.setNote(request.getParameter("note"));
        collapseRecord.setReportingUnit(request.getParameter("reportingUnit"));
        collapseRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        collapseRecordMapper.update(collapseRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/collapseRecord/{id}")
    public PostVo delCollapseRecord(@PathVariable("id")String id){
        collapseRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/collapseRecordCopy/{time}")
    public List<CollapseRecord> collapseRecordCopy(@PathVariable("time") int time){
        List<CollapseRecord> collapseRecords = collapseRecordMapper.getCopyCollapseRecord(time*24);
        return collapseRecords;
    }
}
