package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.SettlementRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SettlementRecordResource {
    @Resource
    SettlementRecordMapper settlementRecordMapper;
    @GetMapping("/v1/settlementRecord")
    public GetVo settlementRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = settlementRecordMapper.getAllSettlementRecord().size();
        List<SettlementRecord> settlementRecords = settlementRecordMapper.getSettlementRecordByPage((page-1)*limit,limit);
        GetVo<SettlementRecord> getVo = new GetVo<>(0,"获取数据成功！",size,settlementRecords);
        return getVo;
    }

    @GetMapping("/v1/settlementRecord/{time}")
    public GetVo settlementRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = settlementRecordMapper.getRecentSettlementRecord(timestamp).size();
        List<SettlementRecord> settlementRecords = settlementRecordMapper.getRecentSettlementRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<SettlementRecord> getVo = new GetVo<>(0,"获取数据成功！",size,settlementRecords);
        return getVo;
    }

    @GetMapping("/v1/lastSettlementRecordStatistics")
    public GetVo getLastSettlementRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = settlementRecordMapper.getSettlementStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/settlementRecord/{id}")
    public PostVo editSettlementRecord(HttpServletRequest request, @PathVariable("id") String id){
        SettlementRecord settlementRecord = new SettlementRecord();
        settlementRecord.setId(id);
        settlementRecord.setDate(request.getParameter("date"));
        settlementRecord.setLocation(request.getParameter("location"));
        settlementRecord.setType(request.getParameter("type"));
        settlementRecord.setStatus(request.getParameter("status"));
        settlementRecord.setPicture(request.getParameter("picture"));
        settlementRecord.setNote(request.getParameter("note"));
        settlementRecord.setReportingUnit(request.getParameter("reportingUnit"));
        settlementRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        settlementRecordMapper.update(settlementRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/settlementRecord/{id}")
    public PostVo delSettlementRecord(@PathVariable("id")String id){
        settlementRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/settlementRecordCopy/{time}")
    public List<SettlementRecord> settlementRecordCopy(@PathVariable("time") int time){
        List<SettlementRecord> settlementRecords = settlementRecordMapper.getCopySettlementRecord(time*24);
        return settlementRecords;
    }
}
