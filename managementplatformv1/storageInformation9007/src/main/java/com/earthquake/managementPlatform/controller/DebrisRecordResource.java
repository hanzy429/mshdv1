package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.DebrisRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DebrisRecordResource {
    @Resource
    DebrisRecordMapper debrisRecordMapper;
    @GetMapping("/v1/debrisRecord")
    public GetVo debrisRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = debrisRecordMapper.getAllDebrisRecord().size();
        List<DebrisRecord> debrisRecord = debrisRecordMapper.getDebrisRecordByPage((page-1)*limit,limit);
        GetVo<DebrisRecord> getVo = new GetVo<>(0,"获取数据成功！",size,debrisRecord);
        return getVo;
    }

    @GetMapping("/v1/debrisRecord/{time}")
    public GetVo debrisRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = debrisRecordMapper.getRecentDebrisRecord(timestamp).size();
        List<DebrisRecord> debrisRecords = debrisRecordMapper.getRecentDebrisRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<DebrisRecord> getVo = new GetVo<>(0,"获取数据成功！",size,debrisRecords);
        return getVo;
    }

    @GetMapping("/v1/lastDebrisRecordStatistics")
    public GetVo getLastDebrisRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = debrisRecordMapper.getDebrisStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/debrisRecord/{id}")
    public PostVo editDebrisRecord(HttpServletRequest request, @PathVariable("id") String id){
        DebrisRecord debrisRecord = new DebrisRecord();
        debrisRecord.setId(id);
        debrisRecord.setDate(request.getParameter("date"));
        debrisRecord.setLocation(request.getParameter("location"));
        debrisRecord.setType(request.getParameter("type"));
        debrisRecord.setStatus(request.getParameter("status"));
        debrisRecord.setPicture(request.getParameter("picture"));
        debrisRecord.setNote(request.getParameter("note"));
        debrisRecord.setReportingUnit(request.getParameter("reportingUnit"));
        debrisRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        debrisRecordMapper.update(debrisRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/debrisRecord/{id}")
    public PostVo delDebrisRecord(@PathVariable("id")String id){
        debrisRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/debrisRecordCopy/{time}")
    public List<DebrisRecord> debrisRecordCopy(@PathVariable("time") int time){
        List<DebrisRecord> debrisRecords = debrisRecordMapper.getCopyDebrisRecord(time*24);
        return debrisRecords;
    }
}
