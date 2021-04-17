package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
public class FtpPicMethod extends FtpMethod{
    @Override
    public String downloadPic(String source, String picName) {
        String ftpPathPic = source+File.separator+ftpRelativePicPath;
        String picPathName = picName;
        DayDate dayDate = new DayDate();
        String date = dayDate.getYear()+dayDate.getMonth()+dayDate.getDay();
        String savePathPic = fileSavePath+File.separator+date+File.separator+filePicSavePath;
        String showPathPic = "http://"+nginxIp+File.separator+nginxPicPath+File.separator+date+File.separator+filePicSavePath;
        File dir = new File(savePathPic);
        if(!dir.exists()){
            dir.mkdirs();
            log.info("目录创建完毕");
        }
        else{
            log.info("目录已存在！");
        }
        //登录
        login();
        if (ftpClient != null) {
            try {
                log.info(basePath + ftpPathPic);
                String path = changeEncoding(basePath + ftpPathPic);
                // 判断是否存在该目录
                if (!ftpClient.changeWorkingDirectory(path)) {
                    log.error(basePath + ftpPathPic + DIR_NOT_EXIST);
                    return null;
                }
                // 设置被动模式，开通一个端口来传输数据
                ftpClient.enterLocalPassiveMode();
                String[] fs = ftpClient.listNames();
                // 判断该目录下是否有文件
                if (fs == null || fs.length == 0) {
                    log.error(basePath + ftpPathPic + DIR_CONTAINS_NO_FILE);
                    return null;
                }
                for (String ff : fs) {
                    String ftpName = new String(ff.getBytes(serverCharset), localCharset);
                    if(ftpName.equals(picPathName)) {
                        log.info(savePathPic + File.separator + ftpName);
                        File file = new File(savePathPic + File.separator + ftpName);
                        try (OutputStream os = new FileOutputStream(file)) {
                            ftpClient.retrieveFile(ftpName, os);
                            log.info(ftpName+"下载文件成功！");
                            return showPathPic + File.separator + ftpName;
                        } catch (Exception e) {
                            log.error(e.getMessage(), e);
                        }
                    }
                }
            } catch (IOException e) {
                log.error("下载文件失败", e);
            } finally {
                closeConnect();
            }
        }
        return null;
    }
}
