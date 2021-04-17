package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.FrameworkStructureMapper;
import com.earthquake.managementPlatform.mapper.MasonryStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FrameworkStructureResource {
    @Resource
    FrameworkStructureMapper frameworkStructureMapper;
    @GetMapping("/v1/frameworkStructure")
    public GetVo frameworkStructureAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = frameworkStructureMapper.getAllFrameworkStructure().size();
        List<FrameworkStructure> frameworkStructure = frameworkStructureMapper.getFrameworkStructureByPage((page-1)*limit,limit);
        GetVo<FrameworkStructure> getVo = new GetVo<>(0,"获取数据成功！",size,frameworkStructure);
        return getVo;
    }

    @GetMapping("/v1/frameworkStructure/{time}")
    public GetVo frameworkStructureByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = frameworkStructureMapper.getRecentFrameworkStructure(timestamp).size();
        List<FrameworkStructure> frameworkStructures = frameworkStructureMapper.getRecentFrameworkStructureByPage((page-1)*limit,limit,timestamp);
        GetVo<FrameworkStructure> getVo = new GetVo<>(0,"获取数据成功！",size,frameworkStructures);
        return getVo;
    }

    @GetMapping("/v1/lastFrameworkStructureByTime")
    public GetVo getLastFrameworkStructureByTime(){
        List<SquareStatistics> squareStatistics = frameworkStructureMapper.getLastFrameworkStructureByTime();
        GetVo<SquareStatistics> getVo = new GetVo<>(0,"获取数据成功！",squareStatistics.size(),squareStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastFrameworkStructure")
    public GetVo getLastFrameworkStructure(){
        List<FrameworkStructure> frameworkStructure = frameworkStructureMapper.getLastFrameworkStructure();
        GetVo<FrameworkStructure> getVo = new GetVo<>(0,"获取数据成功！",frameworkStructure.size(),frameworkStructure);
        return getVo;
    }

    @PutMapping("/v1/frameworkStructure/{id}")
    public PostVo editFrameworkStructure(HttpServletRequest request, @PathVariable("id") String id){
        FrameworkStructure frameworkStructure = new FrameworkStructure();
        frameworkStructure.setId(id);
        frameworkStructure.setDate(request.getParameter("date"));
        frameworkStructure.setLocation(request.getParameter("location"));
        frameworkStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        frameworkStructure.setSlightDamagedSquare(Double.valueOf(request.getParameter("slightDamagedSquare")));
        frameworkStructure.setModerateDamagedSquare(Double.valueOf(request.getParameter("moderateDamagedSquare")));
        frameworkStructure.setSeriousDamagedSquare(Double.valueOf(request.getParameter("seriousDamagedSquare")));
        frameworkStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        frameworkStructure.setNote(request.getParameter("note"));
        frameworkStructure.setReportingUnit(request.getParameter("reportingUnit"));
        frameworkStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        frameworkStructureMapper.update(frameworkStructure);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/frameworkStructure/{id}")
    public PostVo delFrameworkStructure(@PathVariable("id")String id){
        frameworkStructureMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/frameworkStructureCopy/{time}")
    public List<FrameworkStructure> frameworkStructureCopy(@PathVariable("time") int time){
        List<FrameworkStructure> frameworkStructures = frameworkStructureMapper.getCopyFrameworkStructure(time*24);
        return frameworkStructures;
    }
}
