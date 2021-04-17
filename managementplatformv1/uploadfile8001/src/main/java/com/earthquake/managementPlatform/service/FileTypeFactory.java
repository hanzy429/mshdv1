package com.earthquake.managementPlatform.service;

import org.springframework.stereotype.Component;

@Component
public class FileTypeFactory {
    public DisasterFile createFile(String filePath){
        DisasterFile disasterFile = null;
        String[] strArray = filePath.split("\\.");
        int suffixIndex = strArray.length-1;

        if(strArray[suffixIndex].equals("xml")){
            disasterFile = new XmlFile(filePath);
        }
        else if(strArray[suffixIndex].equals("json")){
            disasterFile = new JsonFile(filePath);
        }
        return disasterFile;
    }
}
