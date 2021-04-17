package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Map;

@Slf4j
public abstract class FTPOutPutMethod {
    /**
     * 该目录不存在
     */
    public static final String DIR_NOT_EXIST = "该目录不存在";

    /**
     * "该目录下没有文件
     */
    public static final String DIR_CONTAINS_NO_FILE = "该目录下没有文件";

    /**
     * 本地字符编码
     **/
    public static String localCharset = "GBK";

    /**
     * FTP协议里面，规定文件名编码为iso-8859-1
     **/
    public static String serverCharset = "ISO-8859-1";

    /**
     * UTF-8字符编码
     **/
    public static final String CHARSET_UTF8 = "UTF-8";

    /**
     * OPTS UTF8字符串常量
     **/
    public static final String OPTS_UTF8 = "OPTS UTF8";

    /**
     * 设置缓冲区大小4M
     **/
    public static final int BUFFER_SIZE = 1024 * 1024 * 4;

    /**
     * FTPClient对象
     **/
    public static FTPClient ftpClient = null;

    @Value("${ftp.address}")
    public String address;

    @Value("${ftp.port}")
    public int port;

    @Value("${ftp.username}")
    public String username;

    @Value("${ftp.password}")
    public String password;

    @Value("${ftp.base-path}")
    public String basePath;

    //    /**
//     * 连接FTP服务器
//     *
//     * @param address  地址，如：127.0.0.1
//     * @param port     端口，如：21
//     * @param username 用户名，如：root
//     * @param password 密码，如：root
//     */
    public void login() {
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(address, port);
            ftpClient.login(username, password);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //限制缓冲区大小
            ftpClient.setBufferSize(BUFFER_SIZE);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                closeConnect();
                log.error("FTP服务器连接失败");
            }
        } catch (Exception e) {
            log.error("FTP登录失败", e);
        }
    }

    /**
     * 关闭FTP连接
     */
    public void closeConnect() {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error("关闭FTP连接失败", e);
            }
        }
    }

    /**
     * FTP服务器路径编码转换
     *
     * @param ftpPath FTP服务器路径
     * @return String
     */
    public static String changeEncoding(String ftpPath) {
        String directory = null;
        try {
            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
                localCharset = CHARSET_UTF8;
            }
            directory = new String(ftpPath.getBytes(localCharset), serverCharset);
        } catch (Exception e) {
            log.error("路径编码转换失败", e);
        }
        return directory;
    }
}
