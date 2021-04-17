package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class FtpEarthquakeInfoReading extends FtpMethod{
    @Value("${data-source.earthquake-info}")
    private String dataSourceEarthquakeInfo;
    @Override
    public Map<String, String> downloadFiles() throws Exception {
        Map<String,String> map = new HashMap<>();
        DayDate dayDate = new DayDate();
        String date = dayDate.getYear()+dayDate.getMonth()+dayDate.getDay();
        //登录
        super.login();
        if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
            closeConnect();
            return null;
        }
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        // 设置被动模式，开通一个端口来传输数据
        ftpClient.enterLocalPassiveMode();
        if (ftpClient != null) {
                try {
                    String ftpPath = dataSourceEarthquakeInfo;
                    // 对路径进行编码
                    String path = changeEncoding(basePath + ftpPath);
                    // 判断是否存在该目录
                    if (!ftpClient.changeWorkingDirectory(path)) {
                        log.error(basePath + ftpPath + DIR_NOT_EXIST);
                        return null;
                    }
                    log.info(basePath + ftpPath);
                    String[] fs = ftpClient.listNames();
                    // 判断该目录下是否有文件
                    if (fs == null || fs.length == 0) {
                        log.error(basePath + ftpPath + DIR_CONTAINS_NO_FILE);
                        return null;
                    }
                    if(fs.length == 1)
                    {
                        return null;
                    }
                    for (String ff : fs) {
                        String ftpName = new String(ff.getBytes(localCharset), serverCharset);
//                        log.info(ftpName);
                        if (!ftpName.equals(ftpRelativePicPath)) {
                            log.info(fileSavePath + File.separator + date+File.separator +ftpName);
                            File dir = new File(fileSavePath + File.separator + date);
                            if(!dir.exists()){
                                dir.mkdirs();
                                log.info("基本震情目录创建完毕");
                            }
                            else{
                                log.info("基本震情目录已存在！");
                            }
                            log.info(ftpName);
                            File file = new File(fileSavePath + File.separator + date+File.separator+ftpName);
                            try (OutputStream os = new FileOutputStream(file)) {
                                os.flush();
                                ftpClient.retrieveFile(ftpName, os);
//                                removeFile(basePath + ftpPath, ff);
                                log.info(fileSavePath + File.separator + date+File.separator+ftpName + "下载成功！");
                                map.put(fileSavePath + File.separator + date + File.separator + ftpName, ftpPath);
                            } catch (ConnectException e) {
                                log.error(fileSavePath + File.separator + date+ File.separator +ftpName + "下载失败！");
                                return null;
                            }
                            removeFile(basePath + ftpPath, ff);
                        }
                    }
                } catch (IOException e) {
                    log.error("下载文件失败", e);
                    return null;
                } finally {
                }

        }
        closeConnect();
        return map;
    }
}
