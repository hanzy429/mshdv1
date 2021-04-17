package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CrackRecordMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CrackRecordResource {
    @Resource
    CrackRecordMapper crackRecordMapper;
    @GetMapping("/v1/crackRecord")
    public GetVo crackRecordAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = crackRecordMapper.getAllCrackRecord().size();
        List<CrackRecord> crackRecord = crackRecordMapper.getCrackRecordByPage((page-1)*limit,limit);
        GetVo<CrackRecord> getVo = new GetVo<>(0,"获取数据成功！",size,crackRecord);
        return getVo;
    }

    @GetMapping("/v1/crackRecord/{time}")
    public GetVo crackRecordByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = crackRecordMapper.getRecentCrackRecord(timestamp).size();
        List<CrackRecord> crackRecords = crackRecordMapper.getRecentCrackRecordByPage((page-1)*limit,limit,timestamp);
        GetVo<CrackRecord> getVo = new GetVo<>(0,"获取数据成功！",size,crackRecords);
        return getVo;
    }

    @GetMapping("/v1/lastCrackRecordStatistics")
    public GetVo getLastCrackRecordStatistics(){
        List<SecondaryDisasterStatistics> secondaryDisasterStatistics = crackRecordMapper.getCrackStatistics();
        GetVo<SecondaryDisasterStatistics> getVo = new GetVo<>(0,"获取数据成功！",secondaryDisasterStatistics .size(),secondaryDisasterStatistics );
        return getVo;
    }

    @PutMapping("/v1/crackRecord/{id}")
    public PostVo editCrackRecord(HttpServletRequest request, @PathVariable("id") String id){
        CrackRecord crackRecord = new CrackRecord();
        crackRecord.setId(id);
        crackRecord.setDate(request.getParameter("date"));
        crackRecord.setLocation(request.getParameter("location"));
        crackRecord.setType(request.getParameter("type"));
        crackRecord.setStatus(request.getParameter("status"));
        crackRecord.setPicture(request.getParameter("picture"));
        crackRecord.setNote(request.getParameter("note"));
        crackRecord.setReportingUnit(request.getParameter("reportingUnit"));
        crackRecord.setEarthquakeId(request.getParameter("earthquakeId"));
        crackRecordMapper.update(crackRecord);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/crackRecord/{id}")
    public PostVo delCrackRecord(@PathVariable("id")String id){
        crackRecordMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/crackRecordCopy/{time}")
    public List<CrackRecord> crackRecordCopy(@PathVariable("time") int time){
        List<CrackRecord> crackRecords = crackRecordMapper.getCopyCrackRecord(time*24);
        return crackRecords;
    }
}
