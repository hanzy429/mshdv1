package com.earthquake.managementPlatform.service;

import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FtpFileMethodTest {
    @Resource
    FtpFileMethod ftpFileMethod;

    @Test
    public void downloadFilesTest() throws Exception {
        FtpMethod ftpMethod = ftpFileMethod;
        ftpMethod.downloadFiles();

    }


}