package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@Service
public class MultiMediaMethodService {
    @Resource
    MultiMediaTypeFactory multiMediaTypeFactory;

    public String uploadMultiMedia(MultipartFile uploadFile){
        MultiMediaMethod multiMediaMethod = multiMediaTypeFactory.createMultiMedia(uploadFile.getOriginalFilename());
        return multiMediaMethod.uploadMultiMedia(uploadFile);
    }
}
