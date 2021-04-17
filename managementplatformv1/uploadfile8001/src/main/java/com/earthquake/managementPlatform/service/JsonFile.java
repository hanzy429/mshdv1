package com.earthquake.managementPlatform.service;

import org.json.JSONArray;

public class JsonFile extends DisasterFile{
    public JsonFile() {
        this.fileBehavior = new JsonBehavior();
    }

    public JsonFile(String filePath){
        this.fileBehavior = new JsonBehavior();
        this.filePath = filePath;
    }
}
