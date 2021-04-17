package com.earthquake.managementPlatform.service.impl;

import com.earthquake.managementPlatform.service.OutPut;
import com.earthquake.managementPlatform.service.FTPOutPutMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public class OutPutImpl extends FTPOutPutMethod implements OutPut {
    @Override
    public boolean outPut(String categoryId,String code) throws IOException {
        return outPutForCode(categoryId,code);
    }

    private boolean outPutForCode(String categoryId,String code) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(categoryId + ".txt"));
        out.write(code);
        out.close();
        //登录
        super.login();
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            closeConnect();
            return false;
        }
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        // 设置被动模式，开通一个端口来传输数据
        ftpClient.enterLocalPassiveMode();
        if (ftpClient != null) {
            // 对路径进行编码
            String path = changeEncoding(basePath);
            // 判断是否存在该目录
            if (!ftpClient.changeWorkingDirectory(path)) {
                log.error(basePath + DIR_NOT_EXIST);
                return false;
            }
            try {
                FileInputStream in = new FileInputStream(new File(categoryId + ".txt"));
                ftpClient.storeFile(categoryId + ".txt", in);
                in.close();
                closeConnect();
                return true;
//                    boolean flag = uploadFile(url, port, username, password, path,filename, in);
//                    System.out.println(flag);
            } catch (Exception e) {
                return false;
            }

        }
        return false;
    }
}
