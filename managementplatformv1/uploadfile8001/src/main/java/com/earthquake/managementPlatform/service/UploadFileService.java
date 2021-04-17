package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UploadFileService {
    @Resource
    FileTypeFactory fileTypeFactory;
    @Resource
    FtpPicMethod ftpPicMethod;
    @Resource
    FtpRealTimeFileMethod ftpRealTimeFileMethod;
    @Resource
    FtpEarthquakeInfoReading ftpEarthquakeInfoReading;

    @Transactional
    public Map<JSONArray,String> uploadEarthquakeInfoFiles() throws Exception {
        FtpMethod ftpMethod = ftpEarthquakeInfoReading;
        Map<String,String> dataMap = ftpMethod.downloadFiles();
        if(dataMap == null)
            return null;
        Map<JSONArray,String> dataSource = new HashMap<>();
        for(Map.Entry<String, String> entry : dataMap.entrySet()){
            String filePath = entry.getKey();
            DisasterFile disasterFile = fileTypeFactory.createFile(filePath);
            if(disasterFile == null)
            {
                return null;
            }
            JSONArray jsonArray = disasterFile.fileToJson();
            dataSource.put(jsonArray,entry.getValue());
        }
        if(dataSource.size() == 0) return null;
        else return dataSource;
    }


    @Transactional
    public Map<JSONArray,String> uploadFiles(FtpMethod ftpMethod) throws Exception {
      Map<String,String> dataMap = ftpMethod.downloadFiles();
      if(dataMap == null)
          return null;
      Map<JSONArray,String> dataSource = new HashMap<>();
      for(Map.Entry<String, String> entry : dataMap.entrySet()){
          String filePath = entry.getKey();
          DisasterFile disasterFile = fileTypeFactory.createFile(filePath);
          if(disasterFile == null)
          {
              log.info(filePath+"该文件格式错误！请重新提交！");
              continue;
          }
          JSONArray jsonArray = disasterFile.fileToJson();
          dataSource.put(jsonArray,entry.getValue());
      }
      if(dataSource.size() == 0) return null;
      else return dataSource;
  }

  @Transactional
    public String uploadPic(String source, String picName){
        FtpMethod ftpMethod = ftpPicMethod;
        return ftpMethod.downloadPic(source,picName);
  }

  @Transactional
    public Map<JSONArray,String> uploadRealTimeFiles(String sourcePath) throws Exception {
      ftpRealTimeFileMethod.setSourcePath(sourcePath);
      FtpMethod ftpMethod = ftpRealTimeFileMethod;
      Map<String,String> dataMap = ftpMethod.downloadFiles();
      if(dataMap == null)
          return null;
      Map<JSONArray,String> dataSource = new HashMap<>();
      for(Map.Entry<String, String> entry : dataMap.entrySet()){
          String filePath = entry.getKey();
          DisasterFile disasterFile = fileTypeFactory.createFile(filePath);
          JSONArray jsonArray = disasterFile.fileToJson();
          dataSource.put(jsonArray,entry.getValue());
      }
      if(dataSource.size() == 0) return null;
      else return dataSource;
  }
}
