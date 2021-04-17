package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.KarstRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class KarstRecordResource {
    @Resource
    KarstRecordMapper karstRecordMapper;
    @GetMapping("/v1/karstRecord")
    public GetVo karstRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = karstRecordMapper.getAllKarstRecord().size();
        List<KarstRecord> karstRecord = karstRecordMapper.getKarstRecordByPage((page-1)*limit,limit);
        GetVo<KarstRecord> getVo = new GetVo<>(0,"获取数据成功！",size,karstRecord);
        return getVo;
    }

    @GetMapping("/v1/karstRecord/{time}")
    public GetVo karstRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = karstRecordMapper.getRecentKarstRecord(timestamp).size();
        List<KarstRecord> karstRecords = karstRecordMapper.getRecentKarstRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<KarstRecord> getVo = new GetVo<>(0,"获取数据成功！",size,karstRecords);
        return getVo;
    }

    @GetMapping("/v1/lastKarstRecordStatistics")
    public GetVo getLastKarstRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = karstRecordMapper.getKarstStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/karstRecord/{id}")
    public PostVo editKarstRecord(HttpServletRequest request, @PathVariable("id") String id){
        KarstRecord karstRecord = new KarstRecord();
        karstRecord.setId(id);
        karstRecord.setDate(request.getParameter("date"));
        karstRecord.setLocation(request.getParameter("location"));
        karstRecord.setType(request.getParameter("type"));
        karstRecord.setStatus(request.getParameter("status"));
        karstRecord.setPicture(request.getParameter("picture"));
        karstRecord.setNote(request.getParameter("note"));
        karstRecord.setReportingUnit(request.getParameter("reportingUnit"));
        karstRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        karstRecordMapper.update(karstRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/karstRecord/{id}")
    public PostVo delKarstRecord(@PathVariable("id")String id){
        karstRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/karstRecordCopy/{time}")
    public List<KarstRecord> karstRecordCopy(@PathVariable("time") int time){
        List<KarstRecord> karstRecords = karstRecordMapper.getCopyKarstRecord(time*24);
        return karstRecords;
    }
}
