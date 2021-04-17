package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.DisasterRequest;
import com.earthquake.managementPlatform.entities.GetVo;
import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.mapper.DisasterRequestMapper;
import com.earthquake.managementPlatform.service.OutPutService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class DisasterRequestResource {
    @Resource
    DisasterRequestMapper disasterRequestMapper;
    @Resource
    RestTemplate restTemplate;
    @Value("${storageInformation.url}")
    private String storageInformationUrl;

    @Resource
    OutPutService outPutService;

    @GetMapping("/v1/disasterRequest")
    public GetVo disasterInfoAll(HttpServletRequest request){
        int limit = Integer.valueOf(request.getParameter("limit"));
        int page = Integer.valueOf(request.getParameter("page"));
        int size = disasterRequestMapper.getAllDisasterRequest().size();
        List<DisasterRequest> disasterInfos = disasterRequestMapper.getDisasterRequestByPage((page-1)*limit,limit);
        GetVo<DisasterRequest> getVo = new GetVo<>(0,"获取数据成功！",size,disasterInfos);
        return getVo;
    }

    @PutMapping("/v1/disasterRequest/{id}/{categoryId}")
    public PostVo editDisasterRequest(@PathVariable("id") int id, @PathVariable("categoryId") String categoryId) throws IOException {
        String code = restTemplate.getForObject(storageInformationUrl+"/v1/resource/"+categoryId,String.class);
        boolean flag1 = outPutService.output(categoryId,code);

        String codeDescription = restTemplate.getForObject(storageInformationUrl+"/v1/resourceDescription/"+categoryId,String.class);
        boolean flag2 = outPutService.output(categoryId+"Description",codeDescription);

        PostVo postVo;
        if(flag1&&flag2)
        {
            disasterRequestMapper.updateById(id);
            postVo = new PostVo(1,"发送成功！",null);
            return postVo;
        }
        else
            postVo = new PostVo(0,"发送失败！",null);

        return postVo;
    }

    @PostMapping("/v1/disasterRequest")
    public PostVo addDisasterRequest(HttpServletRequest request)
    {
        PostVo postVo;
        String date = request.getParameter("date");
        String disasterType = request.getParameter("disasterType");
        String oURL = request.getParameter("oURL");
        String requestUnit = request.getParameter("requestUnit");
        DisasterRequest disasterRequest = new DisasterRequest(date,disasterType,"0",oURL,requestUnit);
        int result = disasterRequestMapper.save(disasterRequest);
        if(result == 1)
            postVo = new PostVo(1,"请求录入成功",null);
        else
            postVo = new PostVo(0,"请求录入失败",null);
        return postVo;
    }

    @GetMapping("/v1/processedDisasterRequest")
    public GetVo getProcessedDisasterRequest()
    {
        List<DisasterRequest> disasterRequest = disasterRequestMapper.getProcessedDisasterRequest();
        GetVo<DisasterRequest> getVo = new GetVo<>(0,"获取数据成功！",disasterRequest.size(),disasterRequest);
        return getVo;
    }

    @GetMapping("/v1/noProcessedDisasterRequest")
    public GetVo getNoProcessedDisasterRequest()
    {
        List<DisasterRequest> disasterRequest = disasterRequestMapper.getNoProcessedDisasterRequest();
        GetVo<DisasterRequest> getVo = new GetVo<>(0,"获取数据成功！",disasterRequest.size(),disasterRequest);
        return getVo;
    }

    @DeleteMapping("/v1/disasterRequest/{id}")
    public PostVo delDisasterRequest(@PathVariable("id")int id){
        disasterRequestMapper.deleteById(id);
        PostVo postVo = new PostVo(0,"删除成功!",null);
        return postVo;
    }

}
