package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class PicMethod implements MultiMediaMethod{

    @Value("${file.save-path}")
    private String fileSavePath;

    @Value("${file.pic-save-path}")
    private String filePicSavePath;

    @Value("${nginx.ip}")
    private String nginxIp;

    @Value("${nginx.pic-path}")
    private String nginxPicPath;

    @Override
    public String uploadMultiMedia(MultipartFile uploadFile) {
        try{
            DayDate dayDate = new DayDate();
            String date = dayDate.getYear()+dayDate.getMonth()+dayDate.getDay();
            String picPath = fileSavePath + File.separator + date+ File.separator +filePicSavePath;
            String showPicPath = nginxIp + File.separator + nginxPicPath + File.separator + date+ File.separator +filePicSavePath;
            File dir = new File(picPath);
            if(!dir.exists()){
                dir.mkdirs();
                log.info("图片目录创建完毕！");
            }
            else{
                log.info("图片目录已存在！");
            }
            String fileName=uploadFile.getOriginalFilename();    //得到文件名
            String localSavePath = picPath+'/'+fileName;
            File file=new File(localSavePath);    //得到文件路径
            uploadFile.transferTo(file);
            return "http://"+showPicPath+File.separator+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
}
