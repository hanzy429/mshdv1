package com.earthquake.managementPlatform.service;

import org.json.JSONArray;

import java.io.IOException;

public abstract class DisasterFile {
    String filePath;
    FileBehavior fileBehavior;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public JSONArray fileToJson() throws IOException{
        return fileBehavior.transferToJson(filePath);
    };
}
