package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.CivilStructureMapper;
import com.earthquake.managementPlatform.mapper.MissingStatisticsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CivilStructureResource {
    @Resource
    CivilStructureMapper civilStructureMapper;
    @GetMapping("/v1/civilStructure")
    public GetVo civilStructureAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = civilStructureMapper.getAllCivilStructure().size();
        List<CivilStructure> civilStructure = civilStructureMapper.getCivilStructureByPage((page-1)*limit,limit);
        GetVo<CivilStructure> getVo = new GetVo<>(0,"获取数据成功！",size,civilStructure);
        return getVo;
    }

    @GetMapping("/v1/civilStructure/{time}")
    public GetVo civilStructureByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = civilStructureMapper.getRecentCivilStructure(timestamp).size();
        List<CivilStructure> civilStructures = civilStructureMapper.getRecentCivilStructureByPage((page-1)*limit,limit,timestamp);
        GetVo<CivilStructure> getVo = new GetVo<>(0,"获取数据成功！",size,civilStructures);
        return getVo;

    }

    @GetMapping("/v1/lastCivilStructureByTime")
    public GetVo getLastCivilStructureByTime(){
        List<SquareStatistics> squareStatistics = civilStructureMapper.getLastCivilStructureByTime();
        GetVo<SquareStatistics> getVo = new GetVo<>(0,"获取数据成功！",squareStatistics.size(),squareStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastCivilStructure")
    public GetVo getLastCivilStructure(){
        List<CivilStructure> civilStructure = civilStructureMapper.getLastCivilStructure();
        GetVo<CivilStructure> getVo = new GetVo<>(0,"获取数据成功！",civilStructure.size(),civilStructure);
        return getVo;
    }

    @PutMapping("/v1/civilStructure/{id}")
    public PostVo editCivilStructure(HttpServletRequest request, @PathVariable("id") String id){
        CivilStructure civilStructure = new CivilStructure();
        civilStructure.setId(id);
        civilStructure.setDate(request.getParameter("date"));
        civilStructure.setLocation(request.getParameter("location"));
        civilStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        civilStructure.setDamagedSquare(Double.valueOf(request.getParameter("damagedSquare")));
        civilStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        civilStructure.setNote(request.getParameter("note"));
        civilStructure.setReportingUnit(request.getParameter("reportingUnit"));
        civilStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        civilStructureMapper.update(civilStructure);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/civilStructure/{id}")
    public PostVo delCivilStructure(@PathVariable("id")String id){
        civilStructureMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/civilStructureCopy/{time}")
    public List<CivilStructure> civilStructureCopy(@PathVariable("time") int time){
        List<CivilStructure> civilStructures = civilStructureMapper.getCopyCivilStructure(time*24);
        return civilStructures;
    }
}
