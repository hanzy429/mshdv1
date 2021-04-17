package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.*;
import com.earthquake.managementPlatform.mapper.BrickwoodStructureMapper;
import com.earthquake.managementPlatform.mapper.MasonryStructureMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MasonryStructureResource {
    @Resource
    MasonryStructureMapper masonryStructureMapper;
    @GetMapping("/v1/masonryStructure")
    public GetVo masonryStructureAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = masonryStructureMapper.getAllMasonryStructure().size();
        List<MasonryStructure> masonryStructure = masonryStructureMapper.getMasonryStructureByPage((page-1)*limit,limit);
        GetVo<MasonryStructure> getVo = new GetVo<>(0,"获取数据成功！",size,masonryStructure);
        return getVo;
    }

    @GetMapping("/v1/masonryStructure/{time}")
    public GetVo masonryStructureByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = masonryStructureMapper.getRecentMasonryStructure(timestamp).size();
        List<MasonryStructure> masonryStructures = masonryStructureMapper.getRecentMasonryStructureByPage((page-1)*limit,limit,timestamp);
        GetVo<MasonryStructure> getVo = new GetVo<>(0,"获取数据成功！",size,masonryStructures);
        return getVo;
    }

    @GetMapping("/v1/lastMasonryStructureByTime")
    public GetVo getLastMasonryStructureByTime(){
        List<SquareStatistics> squareStatistics = masonryStructureMapper.getLastMasonryStructureByTime();
        GetVo<SquareStatistics> getVo = new GetVo<>(0,"获取数据成功！",squareStatistics.size(),squareStatistics);
        return getVo;
    }

    @GetMapping("/v1/lastMasonryStructure")
    public GetVo getLastMasonryStructure(){
        List<MasonryStructure> masonryStructure = masonryStructureMapper.getLastMasonryStructure();
        GetVo<MasonryStructure> getVo = new GetVo<>(0,"获取数据成功！",masonryStructure.size(),masonryStructure);
        return getVo;
    }

    @PutMapping("/v1/masonryStructure/{id}")
    public PostVo editMasonryStructure(HttpServletRequest request, @PathVariable("id") String id){
        MasonryStructure masonryStructure = new MasonryStructure();
        masonryStructure.setId(id);
        masonryStructure.setDate(request.getParameter("date"));
        masonryStructure.setLocation(request.getParameter("location"));
        masonryStructure.setBasicallyIntactSquare(Double.valueOf(request.getParameter("basicallyIntactSquare")));
        masonryStructure.setSlightDamagedSquare(Double.valueOf(request.getParameter("slightDamagedSquare")));
        masonryStructure.setModerateDamagedSquare(Double.valueOf(request.getParameter("moderateDamagedSquare")));
        masonryStructure.setSeriousDamagedSquare(Double.valueOf(request.getParameter("seriousDamagedSquare")));
        masonryStructure.setDestroyedSquare(Double.valueOf(request.getParameter("destroyedSquare")));
        masonryStructure.setNote(request.getParameter("note"));
        masonryStructure.setReportingUnit(request.getParameter("reportingUnit"));
        masonryStructure.setEarthquakeId(request.getParameter("earthquakeId"));
        masonryStructureMapper.update(masonryStructure);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/masonryStructure/{id}")
    public PostVo delMasonryStructure(@PathVariable("id")String id){
        masonryStructureMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/masonryStructureCopy/{time}")
    public List<MasonryStructure> masonryStructureCopy(@PathVariable("time") int time){
        List<MasonryStructure> masonryStructures = masonryStructureMapper.getCopyMasonryStructure(time*24);
        return masonryStructures;
    }
}
