package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.BasicEarthquakeInfo;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.ProvEarthquakeFrequency;
import com.earthquake.managementPlatform.mapper.BasicEarthquakeInfoMapper;
import com.earthquake.managementPlatform.service.CodeDescriptionOfResourceService;
import com.earthquake.managementPlatform.service.CodeOfResourceService;
import com.earthquake.managementPlatform.service.DisasterInformationStorageService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class InformationResource {

    @Resource
    DisasterInformationStorageService disasterInformationStorageService;

    @Resource
    BasicEarthquakeInfoMapper basicEarthquakeInfoMapper;

    @Resource
    CodeOfResourceService codeOfResourceService;

    @Resource
    CodeDescriptionOfResourceService codeDescriptionOfResourceService;

    @PostMapping("/v1/informationStorage")
    public String informationStorage(@RequestBody Map<String,String> map){

        if(map!=null) {
//            log.info(disasterInfoCodeWithStorageInformation.getJsonObject().toString());
            disasterInformationStorageService.disasterInformationStorage(map.get("code"), map.get("source"), new JSONObject(map.get("data")));
            return map.get("code")+"入库成功";
        }
        else
        {
            return map.get("code")+"入库失败";
        }

    }

    @PostMapping("/v1/informationPredictionStorage")
    public PostVo informationPredictionStorage(@RequestBody Map<JSONArray,String> map)
    {

        if(map!=null){
            for (Map.Entry<JSONArray,String> entry : map.entrySet()) {
                for(int i = 0;i<entry.getKey().length();i++){
                    JSONObject jsonObject = entry.getKey().getJSONObject(i);
                    disasterInformationStorageService.disasterPredictionInformationStorage(entry.getValue(),jsonObject);
                }
            }
            return new PostVo(0,"预测信息录入成功",null);
        }

        return new PostVo(0,"预测信息录入失败",null);
    }

    @PostMapping("/v1/informationPredictionRecord")
    public PostVo informationPredictionStorgeRecord(HttpServletRequest request)
    {
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("date",request.getParameter("date"));
        jsonObject.put("grade",request.getParameter("grade"));
        jsonObject.put("intensity",request.getParameter("intensity"));
        jsonObject.put("type",request.getParameter("type"));
        jsonObject.put("picture",request.getParameter("picture"));
        log.info(jsonObject.toString());
        disasterInformationStorageService.disasterPredictionInformationStorage(request.getParameter("S_ID"),jsonObject);
        return new PostVo(0,"预测信息录入成功",null);
    }

    @GetMapping("/v1/resource/{categoryId}")
    public String codeOfResource(@PathVariable("categoryId") String categoryId)
    {
        return codeOfResourceService.getCode(categoryId);
    }

    @GetMapping("/v1/resourceDescription/{categoryId}")
    public String codeDescriptionOfResource(@PathVariable("categoryId") String categoryId)
    {
        return codeDescriptionOfResourceService.getCodeDescription(categoryId);
    }

    @GetMapping("/v1/hello")
    public String helloWorld(){
        return "helloWorld";
    }

    @GetMapping("/v1/disasterInfo")
    public GetVo disasterInfoAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = basicEarthquakeInfoMapper.getAllDisasterInfo().size();
        List<BasicEarthquakeInfo> disasterInfos = basicEarthquakeInfoMapper.getDisasterInfoByPage((page-1)*limit,limit);
        GetVo<BasicEarthquakeInfo> getVo = new GetVo<>(0,"获取基本灾情数据成功！",size,disasterInfos);
        return getVo;
    }

    @GetMapping("/v1/disasterInfoCopy/{time}")
    public List<BasicEarthquakeInfo> disasterInfoCopy(@PathVariable("time") int time){
        List<BasicEarthquakeInfo> disasterInfos = basicEarthquakeInfoMapper.getCopyDisasterInfo(time*24);
        return disasterInfos;
    }

    @GetMapping("/v1/disasterInfo/{time}")
    public GetVo disasterInfoByTime(@PathVariable("time")int time, HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int timestamp = time*24;
        int size = basicEarthquakeInfoMapper.getRecentDisasterInfo(timestamp).size();
        List<BasicEarthquakeInfo> disasterInfos = basicEarthquakeInfoMapper.getRecentDisasterInfoByPage((page-1)*limit,limit,timestamp);
        GetVo<BasicEarthquakeInfo> getVo = new GetVo<>(0,"获取基本灾情数据成功！",size,disasterInfos);
        return getVo;

    }

    @PutMapping("/v1/disasterInfo/{id}")
    public PostVo editDisasterInfo(HttpServletRequest request, @PathVariable("id") String id){
        BasicEarthquakeInfo disasterInfo = new BasicEarthquakeInfo();
        disasterInfo.setId(id);
        disasterInfo.setDate(request.getParameter("date"));
        disasterInfo.setLocation(request.getParameter("location"));
        disasterInfo.setLongitude(Float.valueOf(request.getParameter("longitude")));
        disasterInfo.setLatitude(Float.valueOf(request.getParameter("latitude")));
        disasterInfo.setDepth(Float.valueOf(request.getParameter("depth")));
        disasterInfo.setMagnitude(Float.valueOf(request.getParameter("magnitude")));
        disasterInfo.setPicture(request.getParameter("picture"));
        disasterInfo.setReportingUnit(request.getParameter("reportingUnit"));
        basicEarthquakeInfoMapper.update(disasterInfo);
        PostVo postVo = new PostVo(0,"编辑成功！",null);
        return postVo;
    }

    @DeleteMapping("/v1/disasterInfo/{id}")
    public PostVo delDisasterInfo(@PathVariable("id")String id){
        basicEarthquakeInfoMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

    @GetMapping("/v1/provEarthquakeFrequency")
    public GetVo getProvEarthquakeFrequency(){
        List<ProvEarthquakeFrequency> provEarthquakeFrequencies = basicEarthquakeInfoMapper.getProvEarthquakeFrequency();
        GetVo<ProvEarthquakeFrequency> getVo = new GetVo<>(0,"获取数据成功！",provEarthquakeFrequencies.size(),provEarthquakeFrequencies);
        return getVo;
    }

    @GetMapping("/v1/lastDisasterInfo")
    public GetVo getLastDisasterInfo(){
        List disasterInfos = new ArrayList();
        BasicEarthquakeInfo disasterInfo = basicEarthquakeInfoMapper.getLastDisasterInfo();
        disasterInfos.add(disasterInfo);
        GetVo<BasicEarthquakeInfo> getVo = new GetVo<>(0,"获取数据成功！",disasterInfos.size(),disasterInfos);
        return getVo;
    }






}
