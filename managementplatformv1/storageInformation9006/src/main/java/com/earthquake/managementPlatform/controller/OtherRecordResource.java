package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.OtherRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OtherRecordResource {
    @Resource
    OtherRecordMapper otherRecordMapper;
    @GetMapping("/v1/otherRecord")
    public GetVo otherRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = otherRecordMapper.getAllOtherRecord().size();
        List<OtherRecord> otherRecord = otherRecordMapper.getOtherRecordByPage((page-1)*limit,limit);
        GetVo<OtherRecord> getVo = new GetVo<>(0,"获取数据成功！",size,otherRecord);
        return getVo;
    }

    @GetMapping("/v1/otherRecord/{time}")
    public GetVo otherRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = otherRecordMapper.getRecentOtherRecord(timestamp).size();
        List<OtherRecord> otherRecords = otherRecordMapper.getRecentOtherRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<OtherRecord> getVo = new GetVo<>(0,"获取数据成功！",size,otherRecords);
        return getVo;
    }

    @GetMapping("/v1/lastOtherRecordStatistics")
    public GetVo getLastOtherRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = otherRecordMapper.getOtherStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/otherRecord/{id}")
    public PostVo editOtherRecord(HttpServletRequest request, @PathVariable("id") String id){
        OtherRecord otherRecord = new OtherRecord();
        otherRecord.setId(id);
        otherRecord.setDate(request.getParameter("date"));
        otherRecord.setLocation(request.getParameter("location"));
        otherRecord.setType(request.getParameter("type"));
        otherRecord.setStatus(request.getParameter("status"));
        otherRecord.setPicture(request.getParameter("picture"));
        otherRecord.setNote(request.getParameter("note"));
        otherRecord.setReportingUnit(request.getParameter("reportingUnit"));
        otherRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        otherRecordMapper.update(otherRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/otherRecord/{id}")
    public PostVo delOtherRecord(@PathVariable("id")String id){
        otherRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/otherRecordCopy/{time}")
    public List<OtherRecord> otherRecordCopy(@PathVariable("time") int time){
        List<OtherRecord> otherRecords = otherRecordMapper.getCopyOtherRecord(time*24);
        return otherRecords;
    }


}
