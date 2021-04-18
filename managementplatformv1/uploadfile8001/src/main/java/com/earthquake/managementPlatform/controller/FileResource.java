package com.earthquake.managementPlatform.controller;

import com.earthquake.managementPlatform.entities.PostVo;
import com.earthquake.managementPlatform.entities.PostVoForOne;
import com.earthquake.managementPlatform.entities.Schedule;
import com.earthquake.managementPlatform.mapper.OnOffMapper;
import com.earthquake.managementPlatform.mapper.ScheduleMapper;
import com.earthquake.managementPlatform.service.AddRecordService;
import com.earthquake.managementPlatform.service.MultiMediaMethodService;
import com.earthquake.managementPlatform.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class FileResource {
    @Resource
    ScheduleMapper scheduleMapper;
    @Resource
    MultiMediaMethodService multiMediaMethodService;
    @Resource
    AddRecordService addRecordService;
    @Resource
    UploadFileService uploadFileService;
    @Resource
    RestTemplate restTemplate;
    @Value("${disasterInfoCode.url}")
    private String disasterInfoCodeUrl;
    @Resource
    OnOffMapper onOffMapper;


    @PostMapping("/v1/changeSchedule")
    public PostVo changeSchedule(HttpServletRequest httpServletRequest){
        String time = httpServletRequest.getParameter("timingTime");
        int result = scheduleMapper.updateSchedule(new Schedule(1,time));
        if(result == 1)
            return new PostVo(result,"修改成功！",null);
        else
            return  new PostVo(result,"修改失败！",null);
    }

    @PostMapping("/v1/picUpload")
    public PostVo uploadRecordPic(@RequestParam("file") MultipartFile uploadFile) throws IOException {
        List<String> result = new ArrayList<>();
        result.add(multiMediaMethodService.uploadMultiMedia(uploadFile));
        return new PostVo(0,"上传成功！",result);
    }

    @PostMapping("/v1/addRecode")
    public PostVo addRecode(HttpServletRequest request) throws IOException {
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        return addRecordService.AddRecord(streamReader);
    }

    @PostMapping("/v1/filePic/{source}/{picName}")
    public String uploadFilePic(@PathVariable String source,@PathVariable String picName){
        if(source!=null && picName!=null){
            return uploadFileService.uploadPic(source,picName);
        }
        else return null;
    }

    @PostMapping("/v1/realTimeFile/{sourcePath}")
    public PostVoForOne<String> uploadRealTimeFile(@PathVariable String sourcePath) throws Exception {
        if(sourcePath !=null)
        {
            Map<JSONArray,String> map = uploadFileService.uploadRealTimeFiles(sourcePath);
            String result;
            if(map!=null)
                result = restTemplate.postForObject(disasterInfoCodeUrl+"/v1/disasterInfoCode",map, PostVo.class).getData().toString();
            else
            result = "目前无文件";
            return new PostVoForOne<String>(0,"即时文件执行成功！",result);
        }
        return new PostVoForOne<String>(-1,"即时文件执行失败！",null);
    }

    @PostMapping("/v1/on_off/readingFiles/{status}")
    public PostVoForOne<String> SetReadingFiles(@PathVariable boolean status)
    {
        boolean flag = onOffMapper.getReadingFiles();
        int result = -1;
        PostVoForOne<String> postVoForOne = null;
        if(flag == status)
        {
            if(status == true)
                postVoForOne = new PostVoForOne<String>(-1,"读取文件开关已经开启！","读取文件开关已经开启！");
            if(status == false)
                postVoForOne =  new PostVoForOne<String>(-1,"读取文件开关已经关闭！","读取文件开关已经关闭！");
        }
        else
            {
                if(status == true)
                {
                    result = onOffMapper.StartReadingFiles();
                }
                if(status == false)
                {
                    result = onOffMapper.StopReadingFiles();
                }
                if(result == 1)
                {
                    postVoForOne = new PostVoForOne<String>(0,"设置成功！","设置成功！");
                }
                else
                {
                    postVoForOne = new PostVoForOne<String>(-1,"设置失败！","设置失败！");
                }
            }
        return postVoForOne;
    }
}
