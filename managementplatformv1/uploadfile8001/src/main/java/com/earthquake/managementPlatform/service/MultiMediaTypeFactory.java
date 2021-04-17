package com.earthquake.managementPlatform.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MultiMediaTypeFactory {
    @Resource
    PicMethod picMethod;
    public MultiMediaMethod createMultiMedia(String filePath){
        MultiMediaMethod multiMediaMethod = null;
        String[] strArray = filePath.split("\\.");
        int suffixIndex = strArray.length-1;

        if(strArray[suffixIndex].equals("jpg")){
            multiMediaMethod = picMethod;
        }
        else if(strArray[suffixIndex].equals("png")){
            multiMediaMethod = picMethod;
        }
        else if(strArray[suffixIndex].equals("jpeg")){
            multiMediaMethod = picMethod;
        }
        return multiMediaMethod;
    }
}
